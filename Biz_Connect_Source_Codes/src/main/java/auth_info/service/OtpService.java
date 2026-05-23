package auth_info.service;

import auth_info.models.User;

public interface OtpService {

    void generateAndSendOtp(User user);

    boolean verifyOtp(Long userId, String otp);
}