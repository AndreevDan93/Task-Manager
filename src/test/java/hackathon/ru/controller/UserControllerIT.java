package hackathon.ru.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import hackathon.ru.config.SpringConfigForIT;
import hackathon.ru.config.security.SecurityConfig;
import hackathon.ru.dto.UserDto;
import hackathon.ru.model.User;
import hackathon.ru.repository.UserRepository;
import hackathon.ru.utils.TestUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@ActiveProfiles(SpringConfigForIT.TEST_PROFILE)
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = RANDOM_PORT, classes = SpringConfigForIT.class)
public class UserControllerIT {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TestUtils utils;

    @AfterEach
    public void clear() {
        utils.clearDB();
    }

    @Test
    public void registration() throws Exception {
        assertEquals(0, userRepository.count());
        utils.regDefaultUser().andExpect(status().isCreated());
        assertEquals(1, userRepository.count());
    }

    @Test
    public void getUserById() throws Exception {
        utils.regDefaultUser();
        final User expectedUser = userRepository.findAll().get(0);

        final var response = utils.perform(
                        MockMvcRequestBuilders.get(TestUtils.BASE_USER_URL + TestUtils.ID, expectedUser.getId()),
                        expectedUser.getEmail()
                ).andExpect(status().isOk())
                .andReturn()
                .getResponse();

        final User user = TestUtils.fromJson(response.getContentAsString(), new TypeReference<>() {
        });

        assertEquals(expectedUser.getId(), user.getId());
        assertEquals(expectedUser.getEmail(), user.getEmail());
        assertEquals(expectedUser.getFirstName(), user.getFirstName());
        assertEquals(expectedUser.getLastName(), user.getLastName());
    }

    @Test
    public void getAllUsers() throws Exception {
        utils.regDefaultUser();
        UserDto userDto = new UserDto(
                "name",
                "fname",
                TestUtils.TEST_USERNAME_2,
                "pass"
        );
        utils.regUser(userDto);
        final var response = utils.perform(MockMvcRequestBuilders.get(TestUtils.BASE_USER_URL))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

        final List<User> users = TestUtils.fromJson(response.getContentAsString(), new TypeReference<>() {
        });

        assertThat(users).hasSize(2);
    }

    @Test
    public void twiceRegTheSameUserFail() throws Exception {
        utils.regDefaultUser().andExpect(status().isCreated());
        utils.regDefaultUser().andExpect(status().isUnprocessableEntity());

        assertEquals(1, userRepository.count());
    }

    @Test
    public void login() throws Exception {
        utils.regDefaultUser();
        final LoginDto loginDto = new LoginDto(
                utils.getTestUserDto().getEmail(),
                utils.getTestUserDto().getPassword()
        );
        final var loginRequest = MockMvcRequestBuilders.post(TestUtils.BASE_URL + SecurityConfig.LOGIN)
                .content(TestUtils.asJson(loginDto))
                .contentType(APPLICATION_JSON);
        utils.perform(loginRequest).andExpect(status().isOk());
    }


    @Test
    public void loginFail() throws Exception {
        final LoginDto loginDto = new LoginDto(
                utils.getTestUserDto().getEmail(),
                utils.getTestUserDto().getPassword()
        );
        final var loginRequest = MockMvcRequestBuilders.post(TestUtils.BASE_URL + SecurityConfig.LOGIN)
                .content(TestUtils.asJson(loginDto))
                .contentType(APPLICATION_JSON);
        utils.perform(loginRequest).andExpect(status().isUnauthorized());
    }

    @Test
    public void updateUser() throws Exception {
        utils.regDefaultUser();

        final Long userId = userRepository.findByEmail(TestUtils.TEST_USERNAME).get().getId();

        final var userDto = new UserDto("new name",
                "new last name",
                TestUtils.TEST_USERNAME_2,
                "new pass");

        final var updateRequest = MockMvcRequestBuilders.put(TestUtils.BASE_USER_URL + TestUtils.ID, userId)
                .content(TestUtils.asJson(userDto))
                .contentType(APPLICATION_JSON);

        utils.perform(updateRequest, TestUtils.TEST_USERNAME).andExpect(status().isOk());

        assertTrue(userRepository.existsById(userId));
        assertNull(userRepository.findByEmail(TestUtils.TEST_USERNAME).orElse(null));
        assertNotNull(userRepository.findByEmail(TestUtils.TEST_USERNAME_2).orElse(null));
    }

    @Test
    public void deleteUser() throws Exception {
        utils.regDefaultUser();

        final Long userId = userRepository.findByEmail(TestUtils.TEST_USERNAME).get().getId();

        utils.perform(MockMvcRequestBuilders.delete(TestUtils.BASE_USER_URL + TestUtils.ID, userId), TestUtils.TEST_USERNAME)
                .andExpect(status().isOk());

        assertEquals(0, userRepository.count());
    }

    @Test
    public void deleteUserFails() throws Exception {
        utils.regDefaultUser();
        utils.regUser(new UserDto(
                "firstName",
                "lastName",
                TestUtils.TEST_USERNAME_2,
                "pass"
        ));

        final Long userId = userRepository.findByEmail(TestUtils.TEST_USERNAME).get().getId();

        utils.perform(MockMvcRequestBuilders.delete(TestUtils.BASE_USER_URL + TestUtils.ID, userId), TestUtils.TEST_USERNAME_2)
                .andExpect(status().isForbidden());

        assertEquals(2, userRepository.count());
    }

}
