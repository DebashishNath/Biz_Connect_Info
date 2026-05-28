package biz_connect_info.service.TrainingCourse;

import biz_connect_info.models.TrainingCourse;
import biz_connect_info.repository.TrainingCourseRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import utils.CodeConstants;
import utils.MessageResponse;

import java.math.BigDecimal;
import java.util.List;

@Service
class TrainingCourseServiceDAL
        extends TrainingCourseServiceImpl {

    @Autowired
    private TrainingCourseRepository
            trainingCourseRep;

    public TrainingCourseServiceDAL() {
    }

    @Override
    public TrainingCourse updateTrainingCourse(
            TrainingCourse trainingCourse
    ) {

        MessageResponse msgResp;

        try {

            TrainingCourse trainingCourseToUpdate =
                    trainingCourseRep.save(
                            trainingCourse
                    );

            msgResp = new MessageResponse(
                    CodeConstants.SUCCESS.getID(),
                    "Training course updated successfully!"
            );

            trainingCourseToUpdate
                    .setReturnMessage(msgResp);

            return trainingCourseToUpdate;

        } catch (Exception ex) {

            System.out.println(
                    "Error Is: " + ex.getMessage()
            );

            msgResp = new MessageResponse(
                    CodeConstants.FAILURE.getID(),
                    "Failed to update training course!"
            );

            trainingCourse
                    .setReturnMessage(msgResp);

            return trainingCourse;
        }
    }

    @Override
    public MessageResponse deleteTrainingCourse(Long courseId) {
        MessageResponse msgResp = new MessageResponse();
        try
        {
            trainingCourseRep.deleteById(courseId);
            msgResp = new MessageResponse(CodeConstants.SUCCESS.getID(), "Course details deleted successfully!");
            return msgResp;
        }catch(Exception ex)
        {
            System.out.println(ex.getMessage());
            msgResp = new MessageResponse(CodeConstants.FAILURE.getID(),"Failed to delete course");
            return msgResp;
        }
    }

    @Override
    public TrainingCourse getTrainingCourseById(
            Long courseId
    ) {

        try {

            return trainingCourseRep
                    .findById(courseId)
                    .orElse(null);

        } catch (Exception ex) {

            System.out.println(
                    "Error Is: " + ex.getMessage()
            );

            return null;
        }
    }

    @Override
    public List<TrainingCourse>
    getTrainingCoursesByCourseName(
            String courseName
    ) {

        try {

            return trainingCourseRep
                    .findByCourseNameContainingIgnoreCase(
                            courseName
                    );

        } catch (Exception ex) {

            System.out.println(
                    "Error Is: " + ex.getMessage()
            );

            return null;
        }
    }

    @Override
    public List<TrainingCourse>
    getTrainingCoursesByTechnologyArea(
            String technologyArea
    ) {

        try {

            return trainingCourseRep
                    .findByTechnologyAreaContainingIgnoreCase(
                            technologyArea
                    );

        } catch (Exception ex) {

            System.out.println(
                    "Error Is: " + ex.getMessage()
            );

            return null;
        }
    }

    @Override
    public List<TrainingCourse>
    getTrainingCoursesByCreatedBy(
            Long userId
    ) {

        try {

            return trainingCourseRep
                    .findByCreatedById(userId);

        } catch (Exception ex) {

            System.out.println(
                    "Error Is: " + ex.getMessage()
            );

            return null;
        }
    }

    @Override
    public List<TrainingCourse>
    getTrainingCoursesByIsActive(
            String isActive
    ) {

        try {

            return trainingCourseRep
                    .findByIsActive(isActive);

        } catch (Exception ex) {

            System.out.println(
                    "Error Is: " + ex.getMessage()
            );

            return null;
        }
    }

    @Override
    public List<TrainingCourse>
    searchTrainingCoursesByDescription(
            String description
    ) {

        try {

            return trainingCourseRep
                    .findByDescriptionContainingIgnoreCase(
                            description
                    );

        } catch (Exception ex) {

            System.out.println(
                    "Error Is: " + ex.getMessage()
            );

            return null;
        }
    }

    @Override
    public List<TrainingCourse>
    getTrainingCoursesByDurationInDaysBetween(
            Integer minDays,
            Integer maxDays
    ) {

        try {

            return trainingCourseRep
                    .findByDurationInDaysBetween(
                            minDays,
                            maxDays
                    );

        } catch (Exception ex) {

            System.out.println(
                    "Error Is: " + ex.getMessage()
            );

            return null;
        }
    }

    @Override
    public List<TrainingCourse>
    getTrainingCoursesByFeesBetween(
            BigDecimal minFees,
            BigDecimal maxFees
    ) {

        try {

            return trainingCourseRep
                    .findByFeesBetween(
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
    public List<TrainingCourse>
    getRecentTrainingCourses() {

        try {

            return trainingCourseRep
                    .findTop10ByOrderByCreatedAtDesc();

        } catch (Exception ex) {

            System.out.println(
                    "Error Is: " + ex.getMessage()
            );

            return null;
        }
    }

    @Override
    public List<TrainingCourse>
    getAllTrainingCourses() {

        try {

            return trainingCourseRep.findAll();

        } catch (Exception ex) {

            System.out.println(
                    "Error Is: " + ex.getMessage()
            );

            return null;
        }
    }
}