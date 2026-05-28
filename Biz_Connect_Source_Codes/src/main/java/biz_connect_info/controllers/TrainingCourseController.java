package biz_connect_info.controllers;

import biz_connect_info.models.TrainingCourse;
import biz_connect_info.service.TrainingCourse.TrainingCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import utils.MessageResponse;

import java.util.List;
import static utils.Constants.BIZ_CONNECT_API_PATH;

@RestController
@RequestMapping(BIZ_CONNECT_API_PATH + "training_course")
@CrossOrigin(origins = "*")
public class TrainingCourseController {

    @Autowired
    private TrainingCourseService
            trainingCourseService;

    @PostMapping("/update_training_course")
    public TrainingCourse updateTrainingCourse(@RequestBody TrainingCourse trainingCourse)
    {
        return trainingCourseService.updateTrainingCourse(trainingCourse);
    }

    @DeleteMapping("/delete_training_course/{courseId}")
    public MessageResponse deleteTrainingCourse(@PathVariable Long courseId)
    {
        return trainingCourseService.deleteTrainingCourse(courseId);
    }

    @GetMapping("/get_training_course_by_id/{courseId}")
    public TrainingCourse getTrainingCourseById(@PathVariable Long courseId)
    {
        return trainingCourseService.getTrainingCourseById(courseId);
    }

    @GetMapping("/get_all_training_courses")
    public List<TrainingCourse> getAllTrainingCourses()
    {
        return trainingCourseService.getAllTrainingCourses();
    }
}