package biz_connect_info.repository;

//////////////////////////////////////////////////////////////////
// TRAINING COURSE REPOSITORY
//////////////////////////////////////////////////////////////////

import biz_connect_info.models.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface TrainingCourseRepository extends JpaRepository<TrainingCourse, Long> {

    List<TrainingCourse> findByTechnologyArea(String technologyArea);

    List<TrainingCourse> findByCourseNameContainingIgnoreCase(String courseName);

    List<TrainingCourse> findByIsActive(String isActive);

    List<TrainingCourse> findAllByOrderByCourseNameAsc();

    List<TrainingCourse> findTop10ByOrderByCreatedAtDesc();

    List<TrainingCourse> findByTechnologyAreaContainingIgnoreCase(String technologyArea);

    List<TrainingCourse> findByCreatedById(Long userId);

    List<TrainingCourse> findByDescriptionContainingIgnoreCase(String description);

    List<TrainingCourse> findByDurationInDaysBetween(Integer minDays,Integer maxDays);

    List<TrainingCourse> findByFeesBetween(BigDecimal minFees,BigDecimal maxFees);

}