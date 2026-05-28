package biz_connect_info.controllers;

import biz_connect_info.models.Task;
import biz_connect_info.service.Task.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import utils.MessageResponse;

import java.util.List;
import static utils.Constants.BIZ_CONNECT_API_PATH;

@RestController
@RequestMapping(BIZ_CONNECT_API_PATH + "task")
@CrossOrigin(origins = "*")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping("/update_task")
    public Task updateTask(@RequestBody Task task)
    {
        return taskService.updateTask(task);
    }

    @DeleteMapping("/delete_task/{taskId}")
    public MessageResponse deleteTask(@PathVariable Long taskId)
    {
        return taskService.deleteTask(taskId);
    }

    @GetMapping("/get_task_by_id/{taskId}")
    public Task getTaskById(@PathVariable Long taskId)
    {
        return taskService.getTaskById(taskId);
    }

    @GetMapping("/get_all_tasks")
    public List<Task> getAllTasks()
    {
        return taskService.getAllTasks();
    }
}