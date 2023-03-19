package hexlet.code.service.taskService;

import com.querydsl.core.types.Predicate;
import hexlet.code.dto.TaskDto;
import hexlet.code.model.Label;
import hexlet.code.model.Task;
import hexlet.code.model.TaskStatus;
import hexlet.code.model.User;
import hexlet.code.repository.LabelRepository;
import hexlet.code.repository.TaskRepository;
import hexlet.code.service.taskStatusService.TaskStatusService;
import hexlet.code.service.userService.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;


@Service
@AllArgsConstructor
@Log4j2
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final UserService userService;
    private final TaskStatusService taskStatusService;
    private final LabelRepository labelRepository;

    @Override
    public Task getTaskById(long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Task with that id is not exist"));
    }
    @Override
    public Iterable<Task> getAllTasks(Predicate predicate) {
        return taskRepository.findAll(predicate);
    }
    @Override
    public Task createTask(TaskDto taskDTO) {
        Task task = new Task();
        matDTOtoModel(taskDTO, task);
        return taskRepository.save(task);
    }
    @Override
    public Task updateTask(long id, TaskDto taskDTO) {
        Task task = getTaskById(id);
        matDTOtoModel(taskDTO, task);
        return taskRepository.save(task);
    }
    @Override
    public void deleteTaskById(long id) {
        taskRepository.deleteById(id);
    }
    private void matDTOtoModel(TaskDto taskDTO, Task task) {
        final User author = userService.getCurrentUser();
        final TaskStatus taskStatus = taskStatusService.getTaskStatusById(taskDTO.getTaskStatusId());
        final Long executorId = taskDTO.getExecutorId();
        if (executorId != null) {
            task.setExecutor(userService.getUserById(executorId));
        }
        if (taskDTO.getLabelIds() != null) {
            final List<Label> labels = labelRepository.findAllById(taskDTO.getLabelIds());
            task.setLabels(labels);
        }
        task.setName(taskDTO.getName());
        task.setDescription(taskDTO.getDescription());
        task.setAuthor(author);
        task.setTaskStatus(taskStatus);
    }
}
