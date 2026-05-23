package auth_info.service;

import auth_info.models.*;

import auth_info.models.request.LoginRequest;
import auth_info.models.request.OtpVerifyRequest;
import auth_info.repository.OtpVerificationRepository;
import org.springframework.security.authentication.BadCredentialsException;
import utils.CodeConstants;
import auth_info.repository.RefreshTokenRepository;
import auth_info.repository.RoleRepository;
import auth_info.repository.UserRepository;
import auth_info.security.jwt.JwtUtils;
import auth_info.models.response.JwtResponse;
import utils.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import jakarta.validation.Valid;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@Service
class AuthServiceDAL extends AuthServiceImpl {
    public AuthServiceDAL() {
    }

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtUtils;

    @Value("${app.jwtRefreshExpirationMs}")
    private Long refreshTokenDurationMs;

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    private UserDetailsServiceImpl userService;

    @Autowired
    private OtpVerificationRepository otpVerificationRepository;

    @Autowired
    private OtpServiceImpl otpService;

    @Override
    public ResponseEntity<?> authenticateUser(LoginRequest loginRequest) {

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()
                    )
            );

            UserDetailsImpl userDetails =
                    (UserDetailsImpl) authentication.getPrincipal();

            // ✅ Check if 2FA is enabled
            assert userDetails != null;
            if (userDetails.getIs2FAEnabled().equals("Y")) {

                System.out.println("Authentication done: " + userDetails.getId());
                User user = userRepository.findById(userDetails.getId())
                        .orElseThrow(() -> new RuntimeException("User not found"));
                otpService.generateAndSendOtp(user);

                // 🔹 Generate TEMP token (NOT JWT)
                String tempToken =
                        jwtUtils.generateTempToken(userDetails.getUsername());

                return ResponseEntity.ok(
                        new MessageResponse(
                                CodeConstants.SUCCESS.getID(),
                                "OTP sent to registered mobile number",
                                tempToken
                        )
                );
            }
            // 🔹 If 2FA NOT enabled → normal login
            System.out.println("2FA is not for this user");
            return ResponseEntity.ok(
                    generateJWTTokenForLogin(authentication,userDetails)
            );

        } catch (BadCredentialsException ex) {

            return ResponseEntity.ok(
                    new MessageResponse(
                            CodeConstants.FAILURE.getID(),
                            "Invalid username or password"
                    )
            );

        } catch (Exception ex) {
            System.out.println("Exception is: " + ex.getMessage());
            return ResponseEntity.internalServerError().body(
                    new MessageResponse(
                            CodeConstants.FAILURE.getID(),
                            "Login failed. Please contact administrator."
                    )
            );
        }
    }

    @Override
    public ResponseEntity<?> verifyOtp(@Valid OtpVerifyRequest request) {

        String username = jwtUtils.getUserNameFromTempToken(request.getTempToken());

        UserDetailsImpl userDetails = (UserDetailsImpl)userService.loadUserByUsername(username);

        if (!otpService.verifyOtp(userDetails.getId(),request.getOtp())) {
            return ResponseEntity.ok(
                    new MessageResponse(
                            CodeConstants.FAILURE.getID(),
                            "Invalid or expired OTP"
                    )
            );
        }

        Authentication authentication =
                new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities()
                );

        return ResponseEntity.ok(
                generateJWTTokenForLogin(authentication,userDetails)
        );
    }

    private JwtResponse generateJWTTokenForLogin(Authentication authentication,UserDetailsImpl userDetails)
    {
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtUtils.generateJwtToken(authentication);
        RefreshToken refreshToken = createRefreshToken(userDetails.getId());

        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        System.out.println("Inside generateJWTTokenForLogin(): " + jwt);

        try {
            return new JwtResponse(
                    jwt,
                    refreshToken.getToken(),
                    userDetails.getId(),
                    userDetails.getUsername(),
                    userDetails.getFullName(),
                    userDetails.getEmail(),
                    userDetails.getIs2FAEnabled(),
                    roles,
                    new MessageResponse(
                            CodeConstants.SUCCESS.getID(),
                            "Login successful"
                    ));
        }catch(Exception ex){
            System.out.println("Exception inside generateJWTTokenForLogin():" + ex.getMessage());
        }
        return null;
    }

    @Override
    public ResponseEntity<?> registerUser(@Valid @RequestBody User signUpRequest) {
        System.out.println("Inside registerUser() " + signUpRequest.getPassword());
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse(CodeConstants.DUPLICATE.getID(),"Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse(CodeConstants.DUPLICATE.getID(),"Email is already in use!"));
        }

        // Create new user's account
        User user = new User(signUpRequest.getUsername(),signUpRequest.getFullName(), signUpRequest.getEmail(),encodePassword(signUpRequest.getPassword()));

        System.out.println("Before Fetching Roles :Password -- " + encodePassword(signUpRequest.getPassword()));

        Set<Role> strRoles= new HashSet<>(signUpRequest.getRoles());

        Set<Role> roles = new HashSet<>();
        System.out.println("Roles " + strRoles.size());
        if (strRoles.isEmpty()) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {

                switch (role.getName().name()) {
                    case "ROLE_ADMIN":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "ROLE_MODERATOR":
                        Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse(CodeConstants.SUCCESS.getID(), "User registered successfully!"));
    }

    @Override
    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    @Override
    public RefreshToken createRefreshToken(Long userId) {
        return userRepository.findById(userId).map(user -> {
            // Check if refresh token already exists for the user
            Optional<RefreshToken> existingTokenOpt = refreshTokenRepository.findByUser(user);

            RefreshToken refreshToken = existingTokenOpt.orElseGet(RefreshToken::new);
            refreshToken.setUser(user);
            refreshToken.setExpiryDate(Instant.now().plusMillis(refreshTokenDurationMs));
            refreshToken.setToken(UUID.randomUUID().toString());

            return refreshTokenRepository.save(refreshToken);
        }).orElse(null);
    }


    @Override
    public RefreshToken verifyExpiration(RefreshToken token) {
        if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(token);
            throw new TokenRefreshException(token.getToken(), "Refresh token was expired. Please make a new sign in request");
        }
        return token;
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public String encodePassword(String userPassword){
        return encoder.encode(userPassword);
    }

    @Override
    public Optional<User> findUserById(Long id){ return userRepository.findById(id); }

    @Override
    public Optional<User> findUserByName(String userName){ return userRepository.findByUsername(userName); }

    @Override
    public List<Role> findAllRoles(){ return roleRepository.findAll();}
}