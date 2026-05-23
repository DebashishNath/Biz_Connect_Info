package auth_info.models.request;

import utils.MessageResponse;

public class ForgotPasswordRequest {
    private String email;
    private String resetPasswordUrl;
    private MessageResponse returnMessage;

    public ForgotPasswordRequest(){}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getResetPasswordUrl() {
        return resetPasswordUrl;
    }

    public void setResetPasswordUrl(String resetPasswordUrl) {
        this.resetPasswordUrl = resetPasswordUrl;
    }

    public MessageResponse getReturnMessage() {
        return returnMessage;
    }

    public void setReturnMessage(MessageResponse returnMessage) {
        this.returnMessage = returnMessage;
    }
}