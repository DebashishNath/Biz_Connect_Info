package biz_connect_info.service.Task;

import biz_connect_info.models.Task;

import java.time.LocalDateTime;
import java.util.List;

public interface TaskService {

    Task updateTask(Task task);

    void deleteTask(Long taskId);

    Task getTaskById(Long taskId);

    List<Task> getTasksByRelatedLeadId(
            Long leadId
    );

    List<Task> getTasksByAssignedTo(
            Long userId
    );

    List<Task> getTasksByTaskStatus(
            String taskStatus
    );

    List<Task> getTasksByPriority(
            String priority
    );

    List<Task> getTasksByDueDate(
            LocalDateTime dueDate
    );

    List<Task> getTasksBetweenDueDates(
            LocalDateTime fromDate,
            LocalDateTime toDate
    );

    List<Task> searchTasksByTaskTitle(
            String taskTitle
    );

    List<Task> searchTasksByTaskDescription(
            String taskDescription
    );

    List<Task> getOverdueTasks(
            LocalDateTime currentDateTime
    );

    List<Task> getRecentTasks();

    List<Task> getAllTasks();
}