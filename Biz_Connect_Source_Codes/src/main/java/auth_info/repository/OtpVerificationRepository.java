package auth_info.repository;

import auth_info.models.request.OtpVerification;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface OtpVerificationRepository
        extends JpaRepository<OtpVerification, Long> {

    Optional<OtpVerification>
            findTopByUser_IdAndOtpAndIsUsedFalseOrderByOtpVerificationIdDesc(
                    Long userId, String otp
    );
    @Modifying
    @Transactional
    @Query("""
        update OtpVerification o
        set o.isUsed = true
        where o.user.id = :userId
          and o.isUsed = false
    """)
    int invalidatePreviousOtps(@Param("userId") Long userId);
}