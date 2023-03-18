package hexlet.code.service.taskStatusService;

import hexlet.code.dto.TaskStatusDto;
import hexlet.code.model.TaskStatus;

import java.util.List;

public interface TaskStatusService {
    List<TaskStatus> getAllTaskStatuses();

    TaskStatus getTaskStatusById(long id);

    TaskStatus createTaskStatus(TaskStatusDto taskStatusDTO);

    TaskStatus updateTaskStatus(long id, TaskStatusDto taskStatusDTO);

    void deleteTaskStatusById(long id);
}
