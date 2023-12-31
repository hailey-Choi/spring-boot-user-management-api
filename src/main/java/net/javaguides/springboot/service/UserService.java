package net.javaguides.springboot.service;

import net.javaguides.springboot.dto.UserDto;
import net.javaguides.springboot.entity.User;

import java.util.List;

public interface UserService {

    // When the Spring bean (UserService) only has one parametrized constructor,
    // can omit adding @Autowired annotation
    UserDto createUser(UserDto user);

    User getUserById(Long userId);

    List<UserDto> getAllUsers();

    User updateUser(User user);

    void deleteUser(Long userId);
}
