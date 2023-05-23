package hackathon.ru.controller;

import hackathon.ru.dto.TaskStatusDto;
import hackathon.ru.model.TaskStatus;
import hackathon.ru.service.taskStatusService.TaskStatusService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static hackathon.ru.controller.TaskStatusController.TASK_STATUS_CONTROLLER_PATH;

@Tag(name = "Task status controller")
@RestController
@RequestMapping("${base-url}" + TASK_STATUS_CONTROLLER_PATH)
@AllArgsConstructor
public class TaskStatusController {
    public static final String TASK_STATUS_CONTROLLER_PATH = "/statuses";

    public static final String ID = "/{id}";

    private final TaskStatusService taskStatusService;


    @Operation(summary = "Get Task Status by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Task Status with that id was successfully found"),
            @ApiResponse(responseCode = "404", description = "Task Status with that id does not exist")
    })
    @GetMapping(ID)
    public TaskStatus getTaskStatusByID(@PathVariable("id") final long id) {
        return taskStatusService.getTaskStatusById(id);
    }

    @Operation(summary = "Get all Task Statuses")
    @ApiResponse(responseCode = "200", description = "Task Statuses was successfully found")
    @GetMapping
    public List<TaskStatus> getAllTaskStatuses() {
        return taskStatusService.getAllTaskStatuses();
    }

    @Operation(summary = "Create new Task Status")
    @ApiResponse(responseCode = "201", description = "Task Status was successfully created")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    public TaskStatus createTaskStatus(@RequestBody final TaskStatusDto taskStatusDTO) {
        return taskStatusService.createTaskStatus(taskStatusDTO);
    }

    @Operation(summary = "Update Task Status by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Task Status with that id was successfully updated"),
            @ApiResponse(responseCode = "404", description = "Task Status with that id does not exist")
    })
    @PutMapping(ID)
    public TaskStatus updateTaskStatus(@PathVariable("id") final long id,
                                       @RequestBody final TaskStatusDto taskStatusDTO) {
        return taskStatusService.updateTaskStatus(id, taskStatusDTO);
    }

    @Operation(summary = "Delete Task Status by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Task Status with that id was successfully deleted"),
            @ApiResponse(responseCode = "404", description = "Task Status with that id does not exist")
    })
    @DeleteMapping(ID)
    public void deleteTaskStatus(@PathVariable("id") final long id) {
        taskStatusService.deleteTaskStatusById(id);
    }


}
