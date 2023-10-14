package de.pascaljahn.userapi.services;

import de.pascaljahn.userapi.api.UserDto;
import de.pascaljahn.userapi.data.User;
import de.pascaljahn.userapi.data.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    Logger logger = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDto> getAllUsers() {
        logger.debug("Find all");
        return userRepository.findAll()
                .stream()
                .map(this::mapEntityToDto)
                .collect(Collectors.toList());
    }

    public List<UserDto> getUsersByVorname(String vorname) {
        logger.debug("Find Vorname: {}", vorname);
        return userRepository.findByVornameIgnoreCase(vorname)
                .stream()
                .map(this::mapEntityToDto)
                .collect(Collectors.toList());
    }

    public Optional<UserDto> getUserById(long id) {
        logger.debug("Find ID: {}", id);
        return userRepository.findById(id)
                .map(this::mapEntityToDto);
    }

    public void createUser(UserDto userDto) {
        logger.debug("Create: {}", userDto);
        User user = mapDtoToEntity(userDto);
        userRepository.save(user);
    }

    @Transactional
    public void updateUser(long id, UserDto userDto) {
        logger.debug("Update: {}", userDto);
        userRepository.findById(id)
                .ifPresent(user -> {
                    user.setName(userDto.getName());
                    user.setVorname(userDto.getVorname());
                    user.setEmail(userDto.getEmail());
                });
    }

    public void deleteUser(long id) {
        logger.debug("Delete ID: {}", id);
        userRepository.deleteById(id);
    }

    private User mapDtoToEntity(UserDto dto) {
        User user = new User();
        user.setName(dto.getName());
        user.setVorname(dto.getVorname());
        user.setEmail(dto.getEmail());
        return user;
    }

    private UserDto mapEntityToDto(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setVorname(user.getVorname());
        dto.setEmail(user.getEmail());
        return dto;
    }
}
