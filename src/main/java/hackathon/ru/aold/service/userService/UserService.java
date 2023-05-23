package hackathon.ru.aold.service.userService;


import hackathon.ru.aold.dto.UserDto;
import hackathon.ru.aold.model.User;

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

