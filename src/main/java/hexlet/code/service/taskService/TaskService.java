package hexlet.code.service.taskService;

import com.querydsl.core.types.Predicate;
import hexlet.code.DTO.TaskDTO;
import hexlet.code.model.Task;

public interface TaskService {
    Task getTaskById(long id);
    Iterable<Task> getAllTasks(Predicate predicate);
    Task createTask(TaskDTO taskDTO);
    Task updateTask(long id, TaskDTO taskDTO);
    void deleteTaskById(long id);
}
