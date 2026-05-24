package biz_connect_info.service.TrainingCourse;

import biz_connect_info.models.TrainingCourse;

import java.math.BigDecimal;
import java.util.List;

public interface TrainingCourseService {

    TrainingCourse updateTrainingCourse(
            TrainingCourse trainingCourse
    );

    void deleteTrainingCourse(Long courseId);

    TrainingCourse getTrainingCourseById(
            Long courseId
    );

    List<TrainingCourse> getTrainingCoursesByCourseName(
            String courseName
    );

    List<TrainingCourse> getTrainingCoursesByTechnologyArea(
            String technologyArea
    );

    List<TrainingCourse> getTrainingCoursesByCreatedBy(
            Long userId
    );

    List<TrainingCourse> getTrainingCoursesByIsActive(
            String isActive
    );

    List<TrainingCourse> searchTrainingCoursesByDescription(
            String description
    );

    List<TrainingCourse>
    getTrainingCoursesByDurationInDaysBetween(
            Integer minDays,
            Integer maxDays
    );

    List<TrainingCourse>
    getTrainingCoursesByFeesBetween(
            BigDecimal minFees,
            BigDecimal maxFees
    );

    List<TrainingCourse> getRecentTrainingCourses();

    List<TrainingCourse> getAllTrainingCourses();
}