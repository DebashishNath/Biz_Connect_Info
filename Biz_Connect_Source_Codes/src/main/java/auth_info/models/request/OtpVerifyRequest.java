package auth_info.models.request;

import jakarta.validation.constraints.NotBlank;

public class OtpVerifyRequest {

    @NotBlank
    private String tempToken;

    @NotBlank
    private String otp;

    public @NotBlank String getTempToken() {
        return tempToken;
    }

    public void setTempToken(@NotBlank String tempToken) {
        this.tempToken = tempToken;
    }

    public @NotBlank String getOtp() {
        return otp;
    }

    public void setOtp(@NotBlank String otp) {
        this.otp = otp;
    }
}