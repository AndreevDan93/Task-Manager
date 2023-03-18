package hexlet.code.service.userService;


import hexlet.code.dto.UserDto;
import hexlet.code.model.User;

import java.util.List;

public interface UserService {
    User createUser(UserDto userDTO);

    User updateUser(Long id, UserDto userDTO);

    User getUserById(Long id);

    List<User> getAllUsers();

    void deleteUser(Long id);

    String getCurrentUserName();

    User getCurrentUser();


}

