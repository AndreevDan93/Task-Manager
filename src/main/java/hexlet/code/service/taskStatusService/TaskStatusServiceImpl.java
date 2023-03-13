package hexlet.code.service.taskStatusService;

import hexlet.code.DTO.TaskStatusDTO;
import hexlet.code.model.TaskStatus;
import hexlet.code.repository.TaskStatusRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class TaskStatusServiceImpl implements TaskStatusService{
    private final TaskStatusRepository taskStatusRepository;
    @Override
    public List<TaskStatus> getAllTaskStatuses() {
        return taskStatusRepository.findAll();
    }

    @Override
    public TaskStatus getTaskStatusById(long id) {
        return taskStatusRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Task Status with that id is not exist"));
    }

    @Override
    public TaskStatus createTaskStatus(TaskStatusDTO taskStatusDTO) {
        TaskStatus taskStatus = new TaskStatus();
        taskStatus.setName(taskStatusDTO.getName());
        return taskStatusRepository.save(taskStatus);
    }

    @Override
    public TaskStatus updateTaskStatus(long id, TaskStatusDTO taskStatusDTO) {
        TaskStatus taskStatus = getTaskStatusById(id);
        taskStatus.setName(taskStatusDTO.getName());
        return taskStatusRepository.save(taskStatus);
    }

    @Override
    public void deleteTaskStatusById(long id) {
        taskStatusRepository.deleteById(id);
    }
}
