package hexlet.code.service.userService;


import hexlet.code.DTO.UserDTO;
import hexlet.code.model.User;

import java.util.List;

public interface UserService {
    User createUser(UserDTO userDTO);

    User updateUser(Long id, UserDTO userDTO);

    User getUserById(Long id);

    List<User> getAllUsers();

    void deleteUser(Long id);

    String getCurrentUserName();

    User getCurrentUser();


}

