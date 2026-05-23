package auth_info.service;

import auth_info.models.request.OtpVerification;
import auth_info.models.User;
import auth_info.repository.OtpVerificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.time.LocalDateTime;

@Service
public class OtpServiceImpl implements OtpService {

    @Autowired
    private OtpVerificationRepository otpRepo;

    @Autowired
    private SmsService smsService;

    private static final int OTP_EXPIRY_MINUTES = 5;

    private static final int OTP_LENGTH = 6;

    @Autowired
    private OtpVerificationRepository otpVerificationRepository;

    @Transactional
    public void saveOtp(User user, String otp) {
        System.out.println("Inside saveOtp(): " + user.getUsername() + "," + otp);
        // Optional: invalidate previous unused OTPs
        try {
            otpVerificationRepository.invalidatePreviousOtps(user.getId());

            OtpVerification entity = new OtpVerification();
            entity.setUser(user);
            entity.setOtp(otp);
            entity.setExpiresAt(LocalDateTime.now().plusMinutes(OTP_EXPIRY_MINUTES));
            entity.setUsed(false);
            entity.setCreatedAt(LocalDateTime.now());
            System.out.println("Before Save");
            otpVerificationRepository.save(entity);
            System.out.println("After Save inside saveOtp()");
        }catch(Exception ex){
            System.out.println("Exception in saveOtp() :" + ex.getMessage());
        }
    }

    String generateOtp() {
        SecureRandom secureRandom = new SecureRandom();

        int otpNumber = secureRandom.nextInt((int) Math.pow(10, OTP_LENGTH));
        return String.format("%0" + OTP_LENGTH + "d", otpNumber);
    }

   @Override
    public void generateAndSendOtp(User user) {
        String otp = generateOtp();
        saveOtp(user, otp);
        smsService.sendOtp(user.getMobileNo(), otp);
    }

    @Override
    public boolean verifyOtp(Long userId, String otp) {

        return otpRepo
                .findTopByUser_IdAndOtpAndIsUsedFalseOrderByOtpVerificationIdDesc(
                        userId, otp
                )
                .filter(o -> o.getExpiresAt().isAfter(LocalDateTime.now()))
                .map(o -> {
                    o.setUsed(true);
                    otpRepo.save(o);
                    return true;
                })
                .orElse(false);
    }
}