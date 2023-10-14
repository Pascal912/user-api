package de.pascaljahn.userapi.api;

import de.pascaljahn.userapi.services.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserDto> getUsers(@RequestParam Optional<String> vorname) {
        if (vorname.isPresent()) {
            return userService.getUsersByVorname(vorname.get());
        }
        return userService.getAllUsers();
    }

    @GetMapping("{id}")
    public Optional<UserDto> getUserById(@PathVariable long id) {
        return userService.getUserById(id);
    }

    @PostMapping
    public void createUser(@RequestBody @Valid UserDto userDto) {
        userService.createUser(userDto);
    }

    @PutMapping("{id}")
    public void updateUser(@PathVariable long id, @RequestBody @Valid UserDto userDto) {
        userService.updateUser(id, userDto);
    }

    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable long id) {
        userService.deleteUser(id);
    }
}
