package auth_info.models.request;

public class ResetPasswordRequest {
    private String tokenForNewPassword;
    private String newPassword;

    public ResetPasswordRequest(){}

    public String getTokenForNewPassword() {
        return tokenForNewPassword;
    }

    public void setTokenForNewPassword(String tokenForNewPassword) {
        this.tokenForNewPassword = tokenForNewPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}