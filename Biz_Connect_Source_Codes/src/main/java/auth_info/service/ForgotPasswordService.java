package auth_info.service;

import utils.ResponseCode;
import auth_info.models.request.ForgotPasswordRequest;
import auth_info.models.request.ResetPasswordRequest;
import utils.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import auth_info.repository.UserRepository;
import auth_info.models.User;
import java.util.Optional;

@Service
public class ForgotPasswordService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private AuthService authService;

    public ForgotPasswordRequest processForgotPassword(ForgotPasswordRequest forgotPasswordRequest)
    {
        MessageResponse msp;
        try {
            Optional<User> userOpt = userRepository.findByEmail(forgotPasswordRequest.getEmail());

            if (userOpt.isPresent())
            {
                User user = userOpt.get();
                user.generateResetToken();
                userRepository.save(user);
                System.out.println("Reset Token Done. Reset password url is " + forgotPasswordRequest.getResetPasswordUrl());
                emailService.sendResetEmail(user.getEmail(), user.getResetToken(),forgotPasswordRequest.getResetPasswordUrl());
                msp = new MessageResponse(ResponseCode.SUCCESS.getID(), "Password reset link sent to email");
            }
            else
            {
                msp=new MessageResponse(ResponseCode.INVALID.getID(),"Invalid email");
            }
        }catch(Exception ex)
        {
            System.out.println("The error is: " + ex.getMessage());
            msp = new MessageResponse(ResponseCode.FAILURE.getID(), ex.getMessage());
        }
        forgotPasswordRequest.setReturnMessage(msp);
        return forgotPasswordRequest;
    }

    public boolean resetPassword(ResetPasswordRequest resetPasswordRequest)
    {
        System.out.println("Token generated in Email: " + resetPasswordRequest.getTokenForNewPassword());
        Optional<User> userOpt = userRepository.findByResetToken(resetPasswordRequest.getTokenForNewPassword());

        if (userOpt.isPresent())
        {
            try
            {
                User user = userOpt.get();
                String userPwd = authService.encodePassword(resetPasswordRequest.getNewPassword());
                System.out.println("User Id: " + user.getId());
                user.setPassword(userPwd); // ⚠️ Hash password in production!
                user.setResetToken(null); // Clear the reset token
                userRepository.save(user);
                return true;
            } catch (Exception ex)
            {
                System.out.println("The exception at save of resetPassword: " + ex.getMessage());
                return false;
            }
        }
        else
        {
            return false;
        }
    }
}