package biz_connect_info.controllers;

import biz_connect_info.models.TrainingEnrollment;
import biz_connect_info.service.TrainingEnrollment.TrainingEnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import utils.MessageResponse;

import java.util.List;
import static utils.Constants.BIZ_CONNECT_API_PATH;

@RestController
@RequestMapping(BIZ_CONNECT_API_PATH + "training_enrollment")
@CrossOrigin(origins = "*")
public class TrainingEnrollmentController {

    @Autowired
    private TrainingEnrollmentService
            trainingEnrollmentService;

    @PostMapping("/update_training_enrollment")
    public TrainingEnrollment
    updateTrainingEnrollment(
            @RequestBody
            TrainingEnrollment trainingEnrollment
    ) {
        return trainingEnrollmentService
                .updateTrainingEnrollment(
                        trainingEnrollment
                );
    }

    @DeleteMapping("/delete_training_enrollment/{enrollmentId}")
    public MessageResponse deleteTrainingEnrollment(@PathVariable Long enrollmentId)
    {
        return trainingEnrollmentService.deleteTrainingEnrollment(enrollmentId);
    }

    @GetMapping("/get_training_enrollment_by_id/{enrollmentId}")
    public TrainingEnrollment getTrainingEnrollmentById(@PathVariable Long enrollmentId)
    {
        return trainingEnrollmentService.getTrainingEnrollmentById(enrollmentId);
    }

    @GetMapping("/get_all_training_enrollments")
    public List<TrainingEnrollment> getAllTrainingEnrollments()
    {
        return trainingEnrollmentService.getAllTrainingEnrollments();
    }
}