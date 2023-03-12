package hexlet.code.controller;

import hexlet.code.DTO.UserDTO;
import hexlet.code.model.User;
import hexlet.code.service.userService.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
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

import static hexlet.code.controller.UserController.USER_CONTROLLER_PATH;

@AllArgsConstructor
@RestController
@RequestMapping("${base-url}" + USER_CONTROLLER_PATH)
public class UserController {
    public static final String USER_CONTROLLER_PATH = "/users";
    public static final String ID = "/{id}";
    private static final String ONLY_OWNER_BY_ID = """
                @userRepository.findById(#id).get().getEmail() == authentication.getName()
            """;

    private final UserService userService;


    //    Получение пользователя
    @GetMapping()
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping(ID)
    public User getUserById(@PathVariable("id") final long id) {
        return userService.getUserById(id);
    }


    //    Создание Пользователя
    @PostMapping()
    public User createUser(@RequestBody @Valid UserDTO userDTO) {
        return userService.createUser(userDTO);
    }


    //    Изменение Пользователя
    @PutMapping(ID)
    @PreAuthorize(ONLY_OWNER_BY_ID)
    public User updateUser(@PathVariable("id") final long id,
                           @RequestBody @Valid final UserDTO userDTO) {
        return userService.updateUser(id, userDTO);
    }


    //    Удалить Пользователя
    @DeleteMapping(ID)
    @PreAuthorize(ONLY_OWNER_BY_ID)
    public void deleteUser(@PathVariable("id") final long id) {
        userService.deleteUser(id);
    }

}
