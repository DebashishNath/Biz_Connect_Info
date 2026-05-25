package biz_connect_info.controllers;

import biz_connect_info.models.TrainingCourse;
import biz_connect_info.service.TrainingCourse.TrainingCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import static utils.Constants.BIZ_CONNECT_API_PATH;

@RestController
@RequestMapping(BIZ_CONNECT_API_PATH + "training-course")
@CrossOrigin(origins = "*")
public class TrainingCourseController {

    @Autowired
    private TrainingCourseService
            trainingCourseService;

    @PostMapping("/update")
    public TrainingCourse updateTrainingCourse(
            @RequestBody TrainingCourse trainingCourse
    ) {
        return trainingCourseService
                .updateTrainingCourse(trainingCourse);
    }

    @DeleteMapping("/delete/{courseId}")
    public void deleteTrainingCourse(
            @PathVariable Long courseId
    ) {
        trainingCourseService
                .deleteTrainingCourse(courseId);
    }

    @GetMapping("/{courseId}")
    public TrainingCourse getTrainingCourseById(
            @PathVariable Long courseId
    ) {
        return trainingCourseService
                .getTrainingCourseById(courseId);
    }

    @GetMapping("/all")
    public List<TrainingCourse>
    getAllTrainingCourses() {
        return trainingCourseService
                .getAllTrainingCourses();
    }
}