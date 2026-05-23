package auth_info.controllers;

import utils.ResponseCode;
import auth_info.models.request.ForgotPasswordRequest;
import auth_info.models.request.ResetPasswordRequest;
import utils.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import auth_info.service.ForgotPasswordService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class ForgotPasswordController {

    @Autowired
    private ForgotPasswordService forgotPasswordService;

    @PostMapping("/forgot-password")
    public ResponseEntity<ForgotPasswordRequest> forgotPassword(@RequestBody ForgotPasswordRequest forgotPasswordRequest) {
        System.out.println("Inside forgotPassword controller");
        return ResponseEntity.ok(forgotPasswordService.processForgotPassword(forgotPasswordRequest));
    }

    @PostMapping("/reset-password")
    public ResponseEntity<MessageResponse> resetPassword(@RequestBody ResetPasswordRequest resetPasswordRequest) {
        MessageResponse msp;
        try
        {
            if (forgotPasswordService.resetPassword(resetPasswordRequest)) {
                msp=new MessageResponse(ResponseCode.SUCCESS.getID(),"Password reset successful.");
            } else {
                msp=new MessageResponse(ResponseCode.INVALID.getID(),"Invalid Token.");
            }
        }catch(Exception ex)
        {
            msp=new MessageResponse(ResponseCode.FAILURE.getID(),ex.getMessage());
        }
        return ResponseEntity.ok(msp);
    }
}