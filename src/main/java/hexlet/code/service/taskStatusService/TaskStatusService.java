package hexlet.code.service.taskStatusService;

import hexlet.code.DTO.TaskStatusDTO;
import hexlet.code.model.TaskStatus;

import java.util.List;

public interface TaskStatusService {
    List<TaskStatus> getAllTaskStatuses();

    TaskStatus getTaskStatusById(long id);

    TaskStatus createTaskStatus(TaskStatusDTO taskStatusDTO);

    TaskStatus updateTaskStatus(long id, TaskStatusDTO taskStatusDTO);

    void deleteTaskStatusById(long id);
}
