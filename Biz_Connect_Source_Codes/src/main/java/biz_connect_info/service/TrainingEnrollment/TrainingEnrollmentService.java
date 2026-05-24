package biz_connect_info.service.TrainingEnrollment;

import biz_connect_info.models.TrainingEnrollment;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface TrainingEnrollmentService {

    TrainingEnrollment updateTrainingEnrollment(
            TrainingEnrollment trainingEnrollment
    );

    void deleteTrainingEnrollment(
            Long enrollmentId
    );

    TrainingEnrollment getTrainingEnrollmentById(
            Long enrollmentId
    );

    List<TrainingEnrollment>
    getTrainingEnrollmentsByClientId(
            Long clientId
    );

    List<TrainingEnrollment>
    getTrainingEnrollmentsByCourseId(
            Long courseId
    );

    List<TrainingEnrollment>
    getTrainingEnrollmentsByCreatedBy(
            Long userId
    );

    List<TrainingEnrollment>
    getTrainingEnrollmentsByEnrollmentDate(
            LocalDate enrollmentDate
    );

    List<TrainingEnrollment>
    getTrainingEnrollmentsBetweenEnrollmentDates(
            LocalDate fromDate,
            LocalDate toDate
    );

    List<TrainingEnrollment>
    getTrainingEnrollmentsByBatchName(
            String batchName
    );

    List<TrainingEnrollment>
    getTrainingEnrollmentsByPaymentStatus(
            String paymentStatus
    );

    List<TrainingEnrollment>
    getTrainingEnrollmentsByTrainingStatus(
            String trainingStatus
    );

    List<TrainingEnrollment>
    getTrainingEnrollmentsByFeesAmountBetween(
            BigDecimal minFees,
            BigDecimal maxFees
    );

    List<TrainingEnrollment>
    searchTrainingEnrollmentsByRemarks(
            String remarks
    );

    List<TrainingEnrollment>
    getRecentTrainingEnrollments();

    List<TrainingEnrollment>
    getAllTrainingEnrollments();
}