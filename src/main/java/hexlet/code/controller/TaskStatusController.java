package hexlet.code.controller;

import hexlet.code.DTO.TaskStatusDTO;
import hexlet.code.model.TaskStatus;
import hexlet.code.service.taskStatusService.TaskStatusService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static hexlet.code.controller.TaskStatusController.STATUS_CONTROLLER_PATH;


@RestController
@RequestMapping("${base-url}" + STATUS_CONTROLLER_PATH)
@AllArgsConstructor
public class TaskStatusController {
    public static final String STATUS_CONTROLLER_PATH = "/statuses";
    public static final String ID = "/{id}";
    private final TaskStatusService taskStatusService;

    @GetMapping
    public List<TaskStatus> getAllTaskStatuses() {
        return taskStatusService.getAllTaskStatuses();
    }

    @GetMapping(ID)
    public TaskStatus getTaskStatusByID(@PathVariable("id") final long id) {
        return taskStatusService.getTaskStatusById(id);
    }

    @PostMapping()
    public TaskStatus createTaskStatus(@RequestBody final TaskStatusDTO taskStatusDTO) {
        return taskStatusService.createTaskStatus(taskStatusDTO);
    }

    @PutMapping(ID)
    public TaskStatus updateTaskStatus(@PathVariable("id") final long id,
                                       @RequestBody final TaskStatusDTO taskStatusDTO) {
        return taskStatusService.updateTaskStatus(id, taskStatusDTO);
    }

    @DeleteMapping(ID)
    public void deleteTaskStatus(@PathVariable("id") final long id) {
        taskStatusService.deleteTaskStatusById(id);
    }


}
