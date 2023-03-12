package hexlet.code.controller;

import hexlet.code.DTO.TaskDTO;
import hexlet.code.model.Task;
import hexlet.code.service.taskService.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

import static hexlet.code.controller.TaskController.TASKS_CONTROLLER_PATH;

@RestController
@RequestMapping("${base-url}" + TASKS_CONTROLLER_PATH)
@AllArgsConstructor
public class TaskController {
    public static final String TASKS_CONTROLLER_PATH = "/tasks";
    public static final String ID = "/{id}";
    private final TaskService taskService;

    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping(ID)
    public Task getTaskById(@PathVariable("id") final long id) {
        return taskService.getTaskById(id);
    }

    @PostMapping
    public Task createTask(@RequestBody @Valid final TaskDTO taskDTO) {
        return taskService.createTask(taskDTO);
    }

    @PutMapping(ID)
    public Task updateTask(@PathVariable("id") final long id,
                           @RequestBody @Valid final TaskDTO taskDTO) {
        return taskService.updateTask(id, taskDTO);
    }

    @DeleteMapping(ID)
    public void deleteTaskById(@PathVariable("id") final long id) {
        taskService.deleteTaskById(id);
    }


}
