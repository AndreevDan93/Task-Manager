package hexlet.code.service.taskService;

import com.querydsl.core.types.Predicate;
import hexlet.code.dto.TaskDto;
import hexlet.code.model.Task;

public interface TaskService {
    Task getTaskById(long id);
    Iterable<Task> getAllTasks(Predicate predicate);
    Task createTask(TaskDto taskDTO);
    Task updateTask(long id, TaskDto taskDTO);
    void deleteTaskById(long id);
}
