package hackathon.ru.service.taskStatusService;

import hackathon.ru.model.TaskStatus;
import hackathon.ru.dto.TaskStatusDto;

import java.util.List;

public interface TaskStatusService {
    List<TaskStatus> getAllTaskStatuses();

    TaskStatus getTaskStatusById(long id);

    TaskStatus createTaskStatus(TaskStatusDto taskStatusDTO);

    TaskStatus updateTaskStatus(long id, TaskStatusDto taskStatusDTO);

    void deleteTaskStatusById(long id);
}
