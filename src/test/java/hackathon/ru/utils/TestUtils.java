package hackathon.ru.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import hackathon.ru.controller.LabelController;
import hackathon.ru.controller.TaskController;
import hackathon.ru.controller.TaskStatusController;
import hackathon.ru.controller.UserController;
import hackathon.ru.dto.UserDto;
import hackathon.ru.model.User;
import hackathon.ru.repository.UserRepository;
import hackathon.ru.component.JWTHelper;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@Component
public class TestUtils {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LabelRepository labelRepository;
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private TaskStatusRepository taskStatusRepository;
    @Autowired
    private JWTHelper jwtHelper;

    public static final String ID = "/{id}";
    public static final String BASE_URL = "/api";
    public static final String BASE_USER_URL = BASE_URL + UserController.USER_CONTROLLER_PATH;
    public static final String BASE_LABEL_URL = BASE_URL + LabelController.LABEL_CONTROLLER_PATH;
    public static final String BASE_STATUS_URL = BASE_URL + TaskStatusController.TASK_STATUS_CONTROLLER_PATH;
    public static final String BASE_TASK_URL = BASE_URL + TaskController.TASK_CONTROLLER_PATH;

    public static final String TEST_USERNAME = "email@email.com";
    public static final String TEST_USERNAME_2 = "email2@email.com";
    private static final ObjectMapper MAPPER = new ObjectMapper().findAndRegisterModules();

    public void clearDB() {
        taskRepository.deleteAll();
        labelRepository.deleteAll();
        taskStatusRepository.deleteAll();
        userRepository.deleteAll();
    }

    private final UserDto testUserDto = new UserDto(
            "firstName",
            "lastName",
            TEST_USERNAME,
            "pass"
    );
    public UserDto getTestUserDto() {
        return testUserDto;
    }
    public User getUserByEmail(final String email) {
        return userRepository.findByEmail(email).get();
    }

    public ResultActions regDefaultUser() throws Exception {
        return regUser(testUserDto);
    }

    public ResultActions regUser(final UserDto dto) throws Exception {
        final var request = post(BASE_USER_URL)
                .content(asJson(dto))
                .contentType(APPLICATION_JSON);
        return perform(request);
    }


    public ResultActions createNewTaskStatus() throws Exception {
        TaskStatusDto taskStatusDto = new TaskStatusDto("new status");
        User user = userRepository.findAll().get(0);
        return perform(post(BASE_STATUS_URL)
                .content(asJson(taskStatusDto))
                .contentType(APPLICATION_JSON), user.getEmail());
    }
    public ResultActions createNewLabel() throws Exception {
        LabelDto labelDto = new LabelDto("new label");
        User user = userRepository.findAll().get(0);
        return perform(post(BASE_LABEL_URL)
                .content(asJson(labelDto))
                .contentType(APPLICATION_JSON), user.getEmail());
    }
    public ResultActions createNewTask() throws Exception {
        User user = userRepository.findAll().get(0);
        createNewLabel();
        Label label = labelRepository.findAll().get(0);
        createNewTaskStatus();
        TaskStatus taskStatus = taskStatusRepository.findAll().get(0);

        TaskDto taskDto = new TaskDto("Task",
                "description",
                user.getId(),
                taskStatus.getId(),
                List.of(label.getId()));

        return perform(post(BASE_TASK_URL)
                .content(asJson(taskDto))
                .contentType(APPLICATION_JSON), user.getEmail());
    }


    public ResultActions perform(final MockHttpServletRequestBuilder request, final String byUser) throws Exception {
        final String token = jwtHelper.expiring(Map.of("username", byUser));
        request.header(AUTHORIZATION, token);

        return perform(request);
    }
    public ResultActions perform(final MockHttpServletRequestBuilder request) throws Exception {
        return mockMvc.perform(request);
    }


    public static String asJson(final Object object) throws JsonProcessingException {
        return MAPPER.writeValueAsString(object);
    }
    public static <T> T fromJson(final String json, final TypeReference<T> to) throws JsonProcessingException {
        return MAPPER.readValue(json, to);
    }
}
