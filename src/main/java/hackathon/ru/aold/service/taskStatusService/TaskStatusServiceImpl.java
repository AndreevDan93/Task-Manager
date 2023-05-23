package hackathon.ru.aold.service.taskStatusService;

import hackathon.ru.aold.dto.TaskStatusDto;
import hackathon.ru.aold.model.TaskStatus;
import hackathon.ru.aold.repository.TaskStatusRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class TaskStatusServiceImpl implements TaskStatusService {
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
    public TaskStatus createTaskStatus(TaskStatusDto taskStatusDTO) {
        TaskStatus taskStatus = new TaskStatus();
        taskStatus.setName(taskStatusDTO.getName());
        return taskStatusRepository.save(taskStatus);
    }

    @Override
    public TaskStatus updateTaskStatus(long id, TaskStatusDto taskStatusDTO) {
        TaskStatus taskStatus = getTaskStatusById(id);
        taskStatus.setName(taskStatusDTO.getName());
        return taskStatusRepository.save(taskStatus);
    }

    @Override
    public void deleteTaskStatusById(long id) {
        taskStatusRepository.deleteById(id);
    }
}
