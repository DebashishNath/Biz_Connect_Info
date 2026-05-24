package biz_connect_info.repository;

//////////////////////////////////////////////////////////////////
// TASK REPOSITORY
//////////////////////////////////////////////////////////////////

import biz_connect_info.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByAssignedToId(Long userId);

    List<Task> findByTaskStatus(String taskStatus);

    List<Task> findByPriority(String priority);

    List<Task> findByRelatedLeadLeadId(Long leadId);

    List<Task> findByDueDateBetween(
            LocalDateTime fromDate,
            LocalDateTime toDate
    );

    List<Task> findByTaskTitleContainingIgnoreCase(String taskTitle);

    List<Task> findTop10ByOrderByCreatedAtDesc();
}