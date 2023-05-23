package hackathon.ru.aold.service.taskService;

import com.querydsl.core.types.Predicate;
import hackathon.ru.aold.dto.TaskDto;
import hackathon.ru.aold.model.Label;
import hackathon.ru.aold.model.Task;
import hackathon.ru.aold.model.TaskStatus;
import hackathon.ru.aold.model.User;
import hackathon.ru.aold.repository.LabelRepository;
import hackathon.ru.aold.repository.TaskRepository;
import hackathon.ru.aold.service.taskStatusService.TaskStatusService;
import hackathon.ru.aold.service.userService.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;


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
            final Set<Label> labels = new HashSet<>(labelRepository.findAllById(taskDTO.getLabelIds()));
            task.setLabels(labels);
        }
        task.setName(taskDTO.getName());
        task.setDescription(taskDTO.getDescription());
        task.setAuthor(author);
        task.setTaskStatus(taskStatus);
    }
}