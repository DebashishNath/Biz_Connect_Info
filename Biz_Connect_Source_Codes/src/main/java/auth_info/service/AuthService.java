package auth_info.service;

import auth_info.models.RefreshToken;
import auth_info.models.Role;
import auth_info.models.User;
import auth_info.models.request.LoginRequest;
import auth_info.models.request.OtpVerifyRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.Optional;

public interface AuthService {
    ResponseEntity<?> authenticateUser(LoginRequest loginRequest);
    ResponseEntity<?> verifyOtp(@Valid OtpVerifyRequest request);
    ResponseEntity<?> registerUser(User signUpRequest);
    Optional<RefreshToken> findByToken(String token);
    RefreshToken createRefreshToken(Long userId);
    RefreshToken verifyExpiration(RefreshToken token);
    List<User> findAllUsers();
    String encodePassword(String userPassword);
    Optional<User> findUserById(Long id);
    Optional<User> findUserByName(String userName);
    List<Role> findAllRoles();
}