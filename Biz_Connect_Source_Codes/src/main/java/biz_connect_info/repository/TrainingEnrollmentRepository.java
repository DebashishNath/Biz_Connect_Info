package biz_connect_info.repository;

//////////////////////////////////////////////////////////////////
// TRAINING ENROLLMENT REPOSITORY
//////////////////////////////////////////////////////////////////

import biz_connect_info.models.TrainingEnrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface TrainingEnrollmentRepository
        extends JpaRepository<TrainingEnrollment, Long> {

    List<TrainingEnrollment> findByClientClientId(Long clientId);

    List<TrainingEnrollment> findByCourseCourseId(Long courseId);

    List<TrainingEnrollment> findByPaymentStatus(String paymentStatus);

    List<TrainingEnrollment> findByTrainingStatus(String trainingStatus);

    List<TrainingEnrollment> findByBatchName(String batchName);

    List<TrainingEnrollment> findByEnrollmentDateBetween(
            LocalDate fromDate,
            LocalDate toDate
    );

    List<TrainingEnrollment> findTop10ByOrderByCreatedAtDesc();

    List<TrainingEnrollment> findByRemarksContainingIgnoreCase(String remarks);

    List<TrainingEnrollment> findByCreatedById(Long userId);

    List<TrainingEnrollment> findByEnrollmentDate(LocalDate enrollmentDate);

    List<TrainingEnrollment> findByBatchNameContainingIgnoreCase(String batchName);

    List<TrainingEnrollment> findByFeesAmountBetween(BigDecimal minFees,BigDecimal maxFees);
}