package net.javaguides.springboot.service.impl;

import lombok.AllArgsConstructor;
import net.javaguides.springboot.dto.UserDto;
import net.javaguides.springboot.entity.User;
import net.javaguides.springboot.mapper.UserMapper;
import net.javaguides.springboot.repository.UserRepository;
import net.javaguides.springboot.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    // When the Spring bean (UserServiceImpl) only has one parametrized constructor,
    // can omit adding @Autowired annotation
    private UserRepository userRepository;

    // Refactor create user API to transfer DTO instead of JPA entity
    @Override
    public UserDto createUser(UserDto userDto) {

        // Convert UserDto into User JPA entity
        User user = UserMapper.mapToUser(userDto);

        User savedUser = userRepository.save(user);

        // Convert User JPA entity into UserDto
        UserDto savedUserDto = UserMapper.mapToUserDto(savedUser);

        return savedUserDto;
    }

    @Override
    public User getUserById(Long userId) {
        Optional<User> optionalUser =  userRepository.findById(userId);
        return optionalUser.get();
    }

    // Refactor get all users API to transfer DTO instead of JPA entity
    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();

        return users.stream().map(UserMapper::mapToUserDto).collect(Collectors.toList());
    }

    @Override
    public User updateUser(User user) {
        User existingUser = userRepository.findById(user.getId()).get();
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        User updatedUser = userRepository.save(existingUser);
        return updatedUser;
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}
