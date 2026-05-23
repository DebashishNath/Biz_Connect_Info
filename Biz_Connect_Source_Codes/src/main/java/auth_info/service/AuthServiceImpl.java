package auth_info.service;

import auth_info.models.RefreshToken;
import auth_info.models.Role;
import auth_info.models.User;
import auth_info.models.request.LoginRequest;
import auth_info.models.request.OtpVerifyRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

abstract class AuthServiceImpl implements AuthService{

    @Override
    public ResponseEntity<?> authenticateUser(LoginRequest loginRequest) {
        return new AuthServiceDAL().authenticateUser(loginRequest);
    }

    @Override
    public ResponseEntity<?> verifyOtp(@Valid OtpVerifyRequest request){
        return new AuthServiceDAL().verifyOtp(request);
    }
    @Override
    public ResponseEntity<?> registerUser(@Valid @RequestBody User signUpRequest){
        return new AuthServiceDAL().registerUser(signUpRequest);
    }

    @Override
    public Optional<RefreshToken> findByToken(String token){
        return new AuthServiceDAL().findByToken(token);
    }

    @Override
    public RefreshToken createRefreshToken(Long userId){
        return new AuthServiceDAL().createRefreshToken(userId);
    }

    @Override
    public RefreshToken verifyExpiration(RefreshToken token){
        return new AuthServiceDAL().verifyExpiration(token);
    }

    @Override
    public List<User> findAllUsers() {
        return new AuthServiceDAL().findAllUsers();
    }

    @Override
    public String encodePassword(String userPassword){ return new AuthServiceDAL().encodePassword(userPassword); }

    @Override
    public Optional<User> findUserById(Long id){ return new AuthServiceDAL().findUserById(id); }

    @Override
    public Optional<User> findUserByName(String userName) {return new AuthServiceDAL().findUserByName(userName);}

    @Override
    public List<Role> findAllRoles(){ return new AuthServiceDAL().findAllRoles();}
}