package ar.edu.unju.fi.pawmart.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.unju.fi.pawmart.dtos.UserDto;
import ar.edu.unju.fi.pawmart.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // GET ALL
    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.findAll();
    }

    // GET BY ID
    @GetMapping("/{uuid}")
    public UserDto getUserById(@PathVariable UUID uuid) {
        return userService.findById(uuid);
    }

    // CREATE
    @PostMapping
    public UserDto createUser(@RequestBody UserDto userDto) {
        return userService.create(userDto);
    }

    // UPDATE
    @PutMapping("/{uuid}")
    public UserDto updateUser(@PathVariable UUID uuid, @RequestBody UserDto userDto) {
        return userService.update(uuid, userDto);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable UUID id) {
        userService.delete(id);
    }
    
}
