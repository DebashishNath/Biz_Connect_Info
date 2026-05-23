package auth_info.controllers;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import java.util.Optional;

import auth_info.models.request.OtpVerifyRequest;
import jakarta.validation.Valid;

import auth_info.models.Role;
import auth_info.security.jwt.JwtUtils;
import auth_info.models.request.TokenRefreshRequest;
import auth_info.models.response.TokenRefreshResponse;
import auth_info.service.AuthService;
import auth_info.models.TokenRefreshException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import auth_info.models.RefreshToken;
import auth_info.models.User;
import auth_info.models.request.LoginRequest;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private AuthService authService;

	@Autowired
	JwtUtils jwtUtils;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest)
			throws InvalidKeySpecException, NoSuchAlgorithmException {
		System.out.println("Inside authenticateUser()");
		return authService.authenticateUser(loginRequest);
	}

	@PostMapping("/verifyOtp")
	public ResponseEntity<?> verifyOtp(@Valid @RequestBody OtpVerifyRequest request)
	{
		System.out.println("Inside verifyOtp()");
		return authService.verifyOtp(request);
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody User signUpRequest) {
		return authService.registerUser(signUpRequest);
	}

	@PostMapping("/refresh-token")
	public ResponseEntity<?> generateAccessTokenFromRefreshToken(@Valid @RequestBody TokenRefreshRequest request) {
		String requestRefreshToken = request.getRefreshToken();

		return authService.findByToken(requestRefreshToken)
				.map(authService::verifyExpiration)
				.map(RefreshToken::getUser)
				.map(user -> {
					String token = jwtUtils.generateTokenFromUsername(user.getUsername());
					return ResponseEntity.ok(new TokenRefreshResponse(token, requestRefreshToken));
				})
				.orElseThrow(() -> new TokenRefreshException(requestRefreshToken,
						"Refresh token is not in database!"));
	}

	@GetMapping("/findUserById/{id}")
	Optional<User> findUserById(@PathVariable Long id){
		return authService.findUserById(id);
	}

	@GetMapping("/findUserByName/{userName}")
	public Optional<User> findUserByName(@PathVariable String userName) {
		return authService.findUserByName(userName);
	}

	@GetMapping("/allRoles")
	List<Role> findAllRoles(){ return authService.findAllRoles();}
}