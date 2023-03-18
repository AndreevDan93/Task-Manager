package hexlet.code.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import hexlet.code.config.SpringConfigForIT;
import hexlet.code.dto.TaskDto;
import hexlet.code.model.Label;
import hexlet.code.model.Task;
import hexlet.code.model.TaskStatus;
import hexlet.code.repository.LabelRepository;
import hexlet.code.repository.TaskRepository;
import hexlet.code.utils.TestUtils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static hexlet.code.config.SpringConfigForIT.TEST_PROFILE;
import static hexlet.code.controller.LabelController.ID;
import static hexlet.code.controller.TaskController.TASK_CONTROLLER_PATH;
import static hexlet.code.controller.TaskStatusController.TASK_STATUS_CONTROLLER_PATH;
import static hexlet.code.controller.UserController.USER_CONTROLLER_PATH;
import static hexlet.code.utils.TestUtils.BASE_URL;
import static hexlet.code.utils.TestUtils.TEST_USERNAME;
import static hexlet.code.utils.TestUtils.asJson;
import static hexlet.code.utils.TestUtils.fromJson;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@ActiveProfiles(TEST_PROFILE)
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = RANDOM_PORT, classes = SpringConfigForIT.class)
public class TaskControllerIT {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private LabelRepository labelRepository;

    @Autowired
    private TestUtils utils;

    @AfterEach
    public void clear() {
        utils.tearDown();
    }

    @Test
    public void testCreateNewTask() throws Exception {
        assertThat(taskRepository.count()).isEqualTo(0);
        utils.createNewTask();
        assertThat(taskRepository.count()).isEqualTo(1);
    }

    @Test
    public void testGetById() throws Exception {
        utils.createNewTask();
        Task expectedTask = taskRepository.findAll().get(0);
        final var responseGet = utils.perform(get(BASE_URL + TASK_CONTROLLER_PATH + ID,
                        expectedTask.getId()), TEST_USERNAME)
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();
        Task task = fromJson(responseGet.getContentAsString(), new TypeReference<Task>() {
        });
        assertEquals(expectedTask.getName(), task.getName());
        assertEquals(expectedTask.getDescription(), task.getDescription());
        assertEquals(expectedTask.getAuthor().getId(), task.getAuthor().getId());
        assertEquals(expectedTask.getCreatedAt().getTime(), task.getCreatedAt().getTime());
    }

    @Test
    public void testGetAllTask() throws Exception {
        assertEquals(0, taskRepository.count());
        utils.createNewTask();
        final var responseGet = utils.perform(get(BASE_URL + TASK_CONTROLLER_PATH), TEST_USERNAME)
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();
        List<Task> tasks = fromJson(responseGet.getContentAsString(), new TypeReference<List<Task>>() {
        });
        assertEquals(1, tasks.size());
    }

    @Test
    public void testUpdateTask() throws Exception {
        utils.createNewTask();
        Task task = taskRepository.findAll().get(0);
        Label label = labelRepository.findAll().get(0);
        TaskDto taskDto = new TaskDto(
                "task",
                "des",
                task.getExecutor().getId(),
                task.getTaskStatus().getId(),
                List.of(label.getId()));

        final var updateRequest = put(BASE_URL + TASK_CONTROLLER_PATH + ID, task.getId(),TEST_USERNAME)
                .content(asJson(taskDto))
                .contentType(APPLICATION_JSON);

        utils.perform(updateRequest, TEST_USERNAME).andExpect(status().isOk());
        task = taskRepository.findAll().get(0);
        assertEquals(1, taskRepository.count());
        assertEquals("task", task.getName());
        assertEquals("des", task.getDescription());
    }

    @Test
    public void deleteById() throws Exception {
        utils.createNewTask();
        Assertions.assertThat(taskRepository.count()).isEqualTo(1);
        Task task = taskRepository.findAll().get(0);
        utils.perform(delete(BASE_URL + TASK_CONTROLLER_PATH + ID, task.getId()), TEST_USERNAME)
                .andExpect(status().isOk());
        Assertions.assertThat(taskRepository.count()).isEqualTo(0);
    }



}
