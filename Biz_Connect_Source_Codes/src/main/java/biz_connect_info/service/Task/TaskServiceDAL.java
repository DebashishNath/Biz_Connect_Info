package biz_connect_info.service.Task;

import biz_connect_info.models.Task;
import biz_connect_info.repository.TaskRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import utils.CodeConstants;
import utils.MessageResponse;

import java.time.LocalDateTime;
import java.util.List;

@Service
class TaskServiceDAL extends TaskServiceImpl {

    @Autowired
    private TaskRepository taskRep;

    public TaskServiceDAL() {
    }

    @Override
    public Task updateTask(Task task) {

        MessageResponse msgResp;

        try {

            Task taskToUpdate =
                    taskRep.save(task);

            msgResp = new MessageResponse(
                    CodeConstants.SUCCESS.getID(),
                    "Task updated successfully!"
            );

            taskToUpdate.setReturnMessage(msgResp);

            return taskToUpdate;

        } catch (Exception ex) {

            System.out.println(
                    "Error Is: " + ex.getMessage()
            );

            msgResp = new MessageResponse(
                    CodeConstants.FAILURE.getID(),
                    "Failed to update task!"
            );

            task.setReturnMessage(msgResp);

            return task;
        }
    }

    @Override
    public MessageResponse deleteTask(Long taskId) {
        MessageResponse msgResp = new MessageResponse();
        try
        {
            taskRep.deleteById(taskId);
            msgResp = new MessageResponse(CodeConstants.SUCCESS.getID(), "Task details deleted successfully!");
            return msgResp;
        }catch(Exception ex)
        {
            System.out.println(ex.getMessage());
            msgResp = new MessageResponse(CodeConstants.FAILURE.getID(),"Failed to delete task");
            return msgResp;
        }
    }

    @Override
    public Task getTaskById(Long taskId) {

        try {

            return taskRep
                    .findById(taskId)
                    .orElse(null);

        } catch (Exception ex) {

            System.out.println(
                    "Error Is: " + ex.getMessage()
            );

            return null;
        }
    }

    @Override
    public List<Task> getTasksByRelatedLeadId(
            Long leadId
    ) {

        try {

            return taskRep
                    .findByRelatedLeadLeadId(
                            leadId
                    );

        } catch (Exception ex) {

            System.out.println(
                    "Error Is: " + ex.getMessage()
            );

            return null;
        }
    }

    @Override
    public List<Task> getTasksByAssignedTo(
            Long userId
    ) {

        try {

            return taskRep
                    .findByAssignedToId(userId);

        } catch (Exception ex) {

            System.out.println(
                    "Error Is: " + ex.getMessage()
            );

            return null;
        }
    }

    @Override
    public List<Task> getTasksByTaskStatus(
            String taskStatus
    ) {

        try {

            return taskRep
                    .findByTaskStatus(taskStatus);

        } catch (Exception ex) {

            System.out.println(
                    "Error Is: " + ex.getMessage()
            );

            return null;
        }
    }

    @Override
    public List<Task> getTasksByPriority(
            String priority
    ) {

        try {

            return taskRep
                    .findByPriority(priority);

        } catch (Exception ex) {

            System.out.println(
                    "Error Is: " + ex.getMessage()
            );

            return null;
        }
    }

    @Override
    public List<Task> getTasksByDueDate(
            LocalDateTime dueDate
    ) {

        try {

            return taskRep
                    .findByDueDate(dueDate);

        } catch (Exception ex) {

            System.out.println(
                    "Error Is: " + ex.getMessage()
            );

            return null;
        }
    }

    @Override
    public List<Task> getTasksBetweenDueDates(
            LocalDateTime fromDate,
            LocalDateTime toDate
    ) {

        try {

            return taskRep
                    .findByDueDateBetween(
                            fromDate,
                            toDate
                    );

        } catch (Exception ex) {

            System.out.println(
                    "Error Is: " + ex.getMessage()
            );

            return null;
        }
    }

    @Override
    public List<Task> searchTasksByTaskTitle(
            String taskTitle
    ) {

        try {

            return taskRep
                    .findByTaskTitleContainingIgnoreCase(
                            taskTitle
                    );

        } catch (Exception ex) {

            System.out.println(
                    "Error Is: " + ex.getMessage()
            );

            return null;
        }
    }

    @Override
    public List<Task> searchTasksByTaskDescription(
            String taskDescription
    ) {

        try {

            return taskRep
                    .findByTaskDescriptionContainingIgnoreCase(
                            taskDescription
                    );

        } catch (Exception ex) {

            System.out.println(
                    "Error Is: " + ex.getMessage()
            );

            return null;
        }
    }

    @Override
    public List<Task> getOverdueTasks(
            LocalDateTime currentDateTime
    ) {

        try {

            return taskRep
                    .findByDueDateBeforeAndTaskStatusNot(
                            currentDateTime,
                            "COMPLETED"
                    );

        } catch (Exception ex) {

            System.out.println(
                    "Error Is: " + ex.getMessage()
            );

            return null;
        }
    }

    @Override
    public List<Task> getRecentTasks() {

        try {

            return taskRep
                    .findTop10ByOrderByCreatedAtDesc();

        } catch (Exception ex) {

            System.out.println(
                    "Error Is: " + ex.getMessage()
            );

            return null;
        }
    }

    @Override
    public List<Task> getAllTasks() {

        try {

            return taskRep.findAll();

        } catch (Exception ex) {

            System.out.println(
                    "Error Is: " + ex.getMessage()
            );

            return null;
        }
    }
}