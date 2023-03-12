package hexlet.code.service.taskService;

import hexlet.code.DTO.TaskDTO;
import hexlet.code.model.Task;
import hexlet.code.model.TaskStatus;
import hexlet.code.model.User;
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


    @Override
    public Task getTaskById(long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Task with that id is not exist"));
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public Task createTask(TaskDTO taskDTO) {
        Task task = new Task();
        log.info(userService.getCurrentUser().toString());
        final User author = userService.getCurrentUser();
        final TaskStatus taskStatus = taskStatusService.getTaskStatusById(taskDTO.getTaskStatusId());

        task.setName(taskDTO.getName());
        task.setDescription(taskDTO.getDescription());
        task.setAuthor(author);
        task.setTaskStatus(taskStatus);
//        task.setLabels();
        final Long executorId = taskDTO.getExecutorId();
        if (executorId != null) {
            task.setExecutor(userService.getUserById(executorId));
        }
        return taskRepository.save(task);
    }


    @Override
    public Task updateTask(long id, TaskDTO taskDTO) {
        Task task = getTaskById(id);
        final TaskStatus taskStatus = taskStatusService.getTaskStatusById(taskDTO.getTaskStatusId());;

        task.setName(taskDTO.getName());
        task.setDescription(taskDTO.getDescription());
        task.setTaskStatus(taskStatus);
//        task.setLabels();
        final Long executorId = taskDTO.getExecutorId();
        if (executorId != null) {
            task.setExecutor(userService.getUserById(executorId));
        }
        return taskRepository.save(task);
    }

    @Override
    public void deleteTaskById(long id) {
        taskRepository.deleteById(id);
    }


}
