package biz_connect_info.service.TrainingEnrollment;

import biz_connect_info.models.TrainingEnrollment;
import biz_connect_info.repository.TrainingEnrollmentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import utils.CodeConstants;
import utils.MessageResponse;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
class TrainingEnrollmentServiceDAL
        extends TrainingEnrollmentServiceImpl {

    @Autowired
    private TrainingEnrollmentRepository
            trainingEnrollmentRep;

    public TrainingEnrollmentServiceDAL() {
    }

    @Override
    public TrainingEnrollment updateTrainingEnrollment(
            TrainingEnrollment trainingEnrollment
    ) {

        MessageResponse msgResp;

        try {

            TrainingEnrollment
                    trainingEnrollmentToUpdate =
                    trainingEnrollmentRep.save(
                            trainingEnrollment
                    );

            msgResp = new MessageResponse(
                    CodeConstants.SUCCESS.getID(),
                    "Training enrollment updated successfully!"
            );

            trainingEnrollmentToUpdate
                    .setReturnMessage(msgResp);

            return trainingEnrollmentToUpdate;

        } catch (Exception ex) {

            System.out.println(
                    "Error Is: " + ex.getMessage()
            );

            msgResp = new MessageResponse(
                    CodeConstants.FAILURE.getID(),
                    "Failed to update training enrollment!"
            );

            trainingEnrollment
                    .setReturnMessage(msgResp);

            return trainingEnrollment;
        }
    }

    @Override
    public void deleteTrainingEnrollment(
            Long enrollmentId
    ) {

        try {

            trainingEnrollmentRep.deleteById(
                    enrollmentId
            );

        } catch (Exception ex) {

            System.out.println(
                    "Error Is: " + ex.getMessage()
            );
        }
    }

    @Override
    public TrainingEnrollment getTrainingEnrollmentById(
            Long enrollmentId
    ) {

        try {

            return trainingEnrollmentRep
                    .findById(enrollmentId)
                    .orElse(null);

        } catch (Exception ex) {

            System.out.println(
                    "Error Is: " + ex.getMessage()
            );

            return null;
        }
    }

    @Override
    public List<TrainingEnrollment>
    getTrainingEnrollmentsByClientId(
            Long clientId
    ) {

        try {

            return trainingEnrollmentRep
                    .findByClientClientId(
                            clientId
                    );

        } catch (Exception ex) {

            System.out.println(
                    "Error Is: " + ex.getMessage()
            );

            return null;
        }
    }

    @Override
    public List<TrainingEnrollment>
    getTrainingEnrollmentsByCourseId(
            Long courseId
    ) {

        try {

            return trainingEnrollmentRep
                    .findByCourseCourseId(
                            courseId
                    );

        } catch (Exception ex) {

            System.out.println(
                    "Error Is: " + ex.getMessage()
            );

            return null;
        }
    }

    @Override
    public List<TrainingEnrollment>
    getTrainingEnrollmentsByCreatedBy(
            Long userId
    ) {

        try {

            return trainingEnrollmentRep
                    .findByCreatedById(userId);

        } catch (Exception ex) {

            System.out.println(
                    "Error Is: " + ex.getMessage()
            );

            return null;
        }
    }

    @Override
    public List<TrainingEnrollment>
    getTrainingEnrollmentsByEnrollmentDate(
            LocalDate enrollmentDate
    ) {

        try {

            return trainingEnrollmentRep
                    .findByEnrollmentDate(
                            enrollmentDate
                    );

        } catch (Exception ex) {

            System.out.println(
                    "Error Is: " + ex.getMessage()
            );

            return null;
        }
    }

    @Override
    public List<TrainingEnrollment>
    getTrainingEnrollmentsBetweenEnrollmentDates(
            LocalDate fromDate,
            LocalDate toDate
    ) {

        try {

            return trainingEnrollmentRep
                    .findByEnrollmentDateBetween(
                            fromDate,
                            toDate
                    );

        } catch (Exception ex) {

            System.out.println(
                    "Error Is: " + ex.getMessage()
            );

            return null;
        }
    }

    @Override
    public List<TrainingEnrollment>
    getTrainingEnrollmentsByBatchName(
            String batchName
    ) {

        try {

            return trainingEnrollmentRep
                    .findByBatchNameContainingIgnoreCase(
                            batchName
                    );

        } catch (Exception ex) {

            System.out.println(
                    "Error Is: " + ex.getMessage()
            );

            return null;
        }
    }

    @Override
    public List<TrainingEnrollment>
    getTrainingEnrollmentsByPaymentStatus(
            String paymentStatus
    ) {

        try {

            return trainingEnrollmentRep
                    .findByPaymentStatus(
                            paymentStatus
                    );

        } catch (Exception ex) {

            System.out.println(
                    "Error Is: " + ex.getMessage()
            );

            return null;
        }
    }

    @Override
    public List<TrainingEnrollment>
    getTrainingEnrollmentsByTrainingStatus(
            String trainingStatus
    ) {

        try {

            return trainingEnrollmentRep
                    .findByTrainingStatus(
                            trainingStatus
                    );

        } catch (Exception ex) {

            System.out.println(
                    "Error Is: " + ex.getMessage()
            );

            return null;
        }
    }

    @Override
    public List<TrainingEnrollment>
    getTrainingEnrollmentsByFeesAmountBetween(
            BigDecimal minFees,
            BigDecimal maxFees
    ) {

        try {

            return trainingEnrollmentRep
                    .findByFeesAmountBetween(
                            minFees,
                            maxFees
                    );

        } catch (Exception ex) {

            System.out.println(
                    "Error Is: " + ex.getMessage()
            );

            return null;
        }
    }

    @Override
    public List<TrainingEnrollment>
    searchTrainingEnrollmentsByRemarks(
            String remarks
    ) {

        try {

            return trainingEnrollmentRep
                    .findByRemarksContainingIgnoreCase(
                            remarks
                    );

        } catch (Exception ex) {

            System.out.println(
                    "Error Is: " + ex.getMessage()
            );

            return null;
        }
    }

    @Override
    public List<TrainingEnrollment>
    getRecentTrainingEnrollments() {

        try {

            return trainingEnrollmentRep
                    .findTop10ByOrderByCreatedAtDesc();

        } catch (Exception ex) {

            System.out.println(
                    "Error Is: " + ex.getMessage()
            );

            return null;
        }
    }

    @Override
    public List<TrainingEnrollment>
    getAllTrainingEnrollments() {

        try {

            return trainingEnrollmentRep.findAll();

        } catch (Exception ex) {

            System.out.println(
                    "Error Is: " + ex.getMessage()
            );

            return null;
        }
    }
}