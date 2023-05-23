package hackathon.ru.aold.service.taskStatusService;

import hackathon.ru.aold.dto.TaskStatusDto;
import hackathon.ru.aold.model.TaskStatus;

import java.util.List;

public interface TaskStatusService {
    List<TaskStatus> getAllTaskStatuses();

    TaskStatus getTaskStatusById(long id);

    TaskStatus createTaskStatus(TaskStatusDto taskStatusDTO);

    TaskStatus updateTaskStatus(long id, TaskStatusDto taskStatusDTO);

    void deleteTaskStatusById(long id);
}
