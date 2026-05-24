package biz_connect_info.service.TrainingCourse;

import biz_connect_info.models.TrainingCourse;

import java.math.BigDecimal;
import java.util.List;

abstract class TrainingCourseServiceImpl
        implements TrainingCourseService {

    @Override
    public TrainingCourse updateTrainingCourse(
            TrainingCourse trainingCourse
    ) {
        return new TrainingCourseServiceDAL()
                .updateTrainingCourse(trainingCourse);
    }

    @Override
    public void deleteTrainingCourse(Long courseId) {
        new TrainingCourseServiceDAL()
                .deleteTrainingCourse(courseId);
    }

    @Override
    public TrainingCourse getTrainingCourseById(
            Long courseId
    ) {
        return new TrainingCourseServiceDAL()
                .getTrainingCourseById(courseId);
    }

    @Override
    public List<TrainingCourse>
    getTrainingCoursesByCourseName(
            String courseName
    ) {
        return new TrainingCourseServiceDAL()
                .getTrainingCoursesByCourseName(
                        courseName
                );
    }

    @Override
    public List<TrainingCourse>
    getTrainingCoursesByTechnologyArea(
            String technologyArea
    ) {
        return new TrainingCourseServiceDAL()
                .getTrainingCoursesByTechnologyArea(
                        technologyArea
                );
    }

    @Override
    public List<TrainingCourse>
    getTrainingCoursesByCreatedBy(
            Long userId
    ) {
        return new TrainingCourseServiceDAL()
                .getTrainingCoursesByCreatedBy(
                        userId
                );
    }

    @Override
    public List<TrainingCourse>
    getTrainingCoursesByIsActive(
            String isActive
    ) {
        return new TrainingCourseServiceDAL()
                .getTrainingCoursesByIsActive(
                        isActive
                );
    }

    @Override
    public List<TrainingCourse>
    searchTrainingCoursesByDescription(
            String description
    ) {
        return new TrainingCourseServiceDAL()
                .searchTrainingCoursesByDescription(
                        description
                );
    }

    @Override
    public List<TrainingCourse>
    getTrainingCoursesByDurationInDaysBetween(
            Integer minDays,
            Integer maxDays
    ) {
        return new TrainingCourseServiceDAL()
                .getTrainingCoursesByDurationInDaysBetween(
                        minDays,
                        maxDays
                );
    }

    @Override
    public List<TrainingCourse>
    getTrainingCoursesByFeesBetween(
            BigDecimal minFees,
            BigDecimal maxFees
    ) {
        return new TrainingCourseServiceDAL()
                .getTrainingCoursesByFeesBetween(
                        minFees,
                        maxFees
                );
    }

    @Override
    public List<TrainingCourse>
    getRecentTrainingCourses() {
        return new TrainingCourseServiceDAL()
                .getRecentTrainingCourses();
    }

    @Override
    public List<TrainingCourse>
    getAllTrainingCourses() {
        return new TrainingCourseServiceDAL()
                .getAllTrainingCourses();
    }
}