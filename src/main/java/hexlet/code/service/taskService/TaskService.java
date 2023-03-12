package hexlet.code.service.taskService;

import hexlet.code.DTO.TaskDTO;
import hexlet.code.model.Task;

import java.util.List;

public interface TaskService {
    Task getTaskById(long id);
    List<Task> getAllTasks();
    Task createTask(TaskDTO taskDTO);
    Task updateTask(long id, TaskDTO taskDTO);
    void deleteTaskById(long id);
}
