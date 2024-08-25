package org.example.controller;

import org.example.dto.UserCreateDto;
import org.example.dto.UserResponseDto;
import org.example.dto.UserUpdateDto;
import org.example.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/users/{id}")
    public UserResponseDto getUserById(@PathVariable("id") Long id) {
        return userService.findById(id);
    }

    @GetMapping(value = "/users/all")
    public List<UserResponseDto> getAllUsers() {
        return userService.findAll();
    }

    @PostMapping(value = "/users")
    public String createUser(@RequestBody UserCreateDto userCreateDto) {
        userService.save(userCreateDto);
        return "User " + userCreateDto.getFirstName() + " " + userCreateDto.getLastName() + " was created";
    }

    @PutMapping(value = "/users")
    public String updateUser(@RequestBody UserUpdateDto userUpdateDto) {
        userService.update(userUpdateDto);
        return "User with id " + userUpdateDto.getId() + " was updated";
    }

    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.delete(id);
        return "User with id " + id + " was deleted";
    }
}
