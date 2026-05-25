package biz_connect_info.controllers;

import biz_connect_info.models.TrainingEnrollment;
import biz_connect_info.service.TrainingEnrollment.TrainingEnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import static utils.Constants.BIZ_CONNECT_API_PATH;

@RestController
@RequestMapping(BIZ_CONNECT_API_PATH + "training-enrollment")
@CrossOrigin(origins = "*")
public class TrainingEnrollmentController {

    @Autowired
    private TrainingEnrollmentService
            trainingEnrollmentService;

    @PostMapping("/update")
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

    @DeleteMapping("/delete/{enrollmentId}")
    public void deleteTrainingEnrollment(
            @PathVariable Long enrollmentId
    ) {
        trainingEnrollmentService
                .deleteTrainingEnrollment(
                        enrollmentId
                );
    }

    @GetMapping("/{enrollmentId}")
    public TrainingEnrollment
    getTrainingEnrollmentById(
            @PathVariable Long enrollmentId
    ) {
        return trainingEnrollmentService
                .getTrainingEnrollmentById(
                        enrollmentId
                );
    }

    @GetMapping("/all")
    public List<TrainingEnrollment>
    getAllTrainingEnrollments() {
        return trainingEnrollmentService
                .getAllTrainingEnrollments();
    }
}