package biz_connect_info.service.Task;

import biz_connect_info.models.Task;
import utils.MessageResponse;

import java.time.LocalDateTime;
import java.util.List;

abstract class TaskServiceImpl
        implements TaskService {

    @Override
    public Task updateTask(Task task) {
        return new TaskServiceDAL()
                .updateTask(task);
    }

    @Override
    public MessageResponse deleteTask(Long taskId) {
        return new TaskServiceDAL()
                .deleteTask(taskId);
    }

    @Override
    public Task getTaskById(Long taskId) {
        return new TaskServiceDAL()
                .getTaskById(taskId);
    }

    @Override
    public List<Task> getTasksByRelatedLeadId(
            Long leadId
    ) {
        return new TaskServiceDAL()
                .getTasksByRelatedLeadId(
                        leadId
                );
    }

    @Override
    public List<Task> getTasksByAssignedTo(
            Long userId
    ) {
        return new TaskServiceDAL()
                .getTasksByAssignedTo(userId);
    }

    @Override
    public List<Task> getTasksByTaskStatus(
            String taskStatus
    ) {
        return new TaskServiceDAL()
                .getTasksByTaskStatus(
                        taskStatus
                );
    }

    @Override
    public List<Task> getTasksByPriority(
            String priority
    ) {
        return new TaskServiceDAL()
                .getTasksByPriority(priority);
    }

    @Override
    public List<Task> getTasksByDueDate(
            LocalDateTime dueDate
    ) {
        return new TaskServiceDAL()
                .getTasksByDueDate(dueDate);
    }

    @Override
    public List<Task> getTasksBetweenDueDates(
            LocalDateTime fromDate,
            LocalDateTime toDate
    ) {
        return new TaskServiceDAL()
                .getTasksBetweenDueDates(
                        fromDate,
                        toDate
                );
    }

    @Override
    public List<Task> searchTasksByTaskTitle(
            String taskTitle
    ) {
        return new TaskServiceDAL()
                .searchTasksByTaskTitle(
                        taskTitle
                );
    }

    @Override
    public List<Task> searchTasksByTaskDescription(
            String taskDescription
    ) {
        return new TaskServiceDAL()
                .searchTasksByTaskDescription(
                        taskDescription
                );
    }

    @Override
    public List<Task> getOverdueTasks(
            LocalDateTime currentDateTime
    ) {
        return new TaskServiceDAL()
                .getOverdueTasks(
                        currentDateTime
                );
    }

    @Override
    public List<Task> getRecentTasks() {
        return new TaskServiceDAL()
                .getRecentTasks();
    }

    @Override
    public List<Task> getAllTasks() {
        return new TaskServiceDAL()
                .getAllTasks();
    }
}