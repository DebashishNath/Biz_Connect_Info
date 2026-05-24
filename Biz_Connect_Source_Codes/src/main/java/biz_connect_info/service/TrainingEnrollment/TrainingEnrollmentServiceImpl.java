package biz_connect_info.service.TrainingEnrollment;

import biz_connect_info.models.TrainingEnrollment;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

abstract class TrainingEnrollmentServiceImpl
        implements TrainingEnrollmentService {

    @Override
    public TrainingEnrollment updateTrainingEnrollment(
            TrainingEnrollment trainingEnrollment
    ) {
        return new TrainingEnrollmentServiceDAL()
                .updateTrainingEnrollment(
                        trainingEnrollment
                );
    }

    @Override
    public void deleteTrainingEnrollment(
            Long enrollmentId
    ) {
        new TrainingEnrollmentServiceDAL()
                .deleteTrainingEnrollment(
                        enrollmentId
                );
    }

    @Override
    public TrainingEnrollment getTrainingEnrollmentById(
            Long enrollmentId
    ) {
        return new TrainingEnrollmentServiceDAL()
                .getTrainingEnrollmentById(
                        enrollmentId
                );
    }

    @Override
    public List<TrainingEnrollment>
    getTrainingEnrollmentsByClientId(
            Long clientId
    ) {
        return new TrainingEnrollmentServiceDAL()
                .getTrainingEnrollmentsByClientId(
                        clientId
                );
    }

    @Override
    public List<TrainingEnrollment>
    getTrainingEnrollmentsByCourseId(
            Long courseId
    ) {
        return new TrainingEnrollmentServiceDAL()
                .getTrainingEnrollmentsByCourseId(
                        courseId
                );
    }

    @Override
    public List<TrainingEnrollment>
    getTrainingEnrollmentsByCreatedBy(
            Long userId
    ) {
        return new TrainingEnrollmentServiceDAL()
                .getTrainingEnrollmentsByCreatedBy(
                        userId
                );
    }

    @Override
    public List<TrainingEnrollment>
    getTrainingEnrollmentsByEnrollmentDate(
            LocalDate enrollmentDate
    ) {
        return new TrainingEnrollmentServiceDAL()
                .getTrainingEnrollmentsByEnrollmentDate(
                        enrollmentDate
                );
    }

    @Override
    public List<TrainingEnrollment>
    getTrainingEnrollmentsBetweenEnrollmentDates(
            LocalDate fromDate,
            LocalDate toDate
    ) {
        return new TrainingEnrollmentServiceDAL()
                .getTrainingEnrollmentsBetweenEnrollmentDates(
                        fromDate,
                        toDate
                );
    }

    @Override
    public List<TrainingEnrollment>
    getTrainingEnrollmentsByBatchName(
            String batchName
    ) {
        return new TrainingEnrollmentServiceDAL()
                .getTrainingEnrollmentsByBatchName(
                        batchName
                );
    }

    @Override
    public List<TrainingEnrollment>
    getTrainingEnrollmentsByPaymentStatus(
            String paymentStatus
    ) {
        return new TrainingEnrollmentServiceDAL()
                .getTrainingEnrollmentsByPaymentStatus(
                        paymentStatus
                );
    }

    @Override
    public List<TrainingEnrollment>
    getTrainingEnrollmentsByTrainingStatus(
            String trainingStatus
    ) {
        return new TrainingEnrollmentServiceDAL()
                .getTrainingEnrollmentsByTrainingStatus(
                        trainingStatus
                );
    }

    @Override
    public List<TrainingEnrollment>
    getTrainingEnrollmentsByFeesAmountBetween(
            BigDecimal minFees,
            BigDecimal maxFees
    ) {
        return new TrainingEnrollmentServiceDAL()
                .getTrainingEnrollmentsByFeesAmountBetween(
                        minFees,
                        maxFees
                );
    }

    @Override
    public List<TrainingEnrollment>
    searchTrainingEnrollmentsByRemarks(
            String remarks
    ) {
        return new TrainingEnrollmentServiceDAL()
                .searchTrainingEnrollmentsByRemarks(
                        remarks
                );
    }

    @Override
    public List<TrainingEnrollment>
    getRecentTrainingEnrollments() {
        return new TrainingEnrollmentServiceDAL()
                .getRecentTrainingEnrollments();
    }

    @Override
    public List<TrainingEnrollment>
    getAllTrainingEnrollments() {
        return new TrainingEnrollmentServiceDAL()
                .getAllTrainingEnrollments();
    }
}