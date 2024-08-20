package org.example.controller;

import org.example.dto.UserCreateDto;
import org.example.dto.UserResponseDto;
import org.example.dto.UserUpdateDto;
import org.example.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@Validated
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable Long id) {
        UserResponseDto userResponseDto = userService.findById(id);
        return ResponseEntity.ok(userResponseDto);
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        List<UserResponseDto> users = userService.findAll();
        return ResponseEntity.ok(users);
    }

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody UserCreateDto userCreateDto) {
        userService.save(userCreateDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("User " + userCreateDto.getFirstName() + " " + userCreateDto.getLastName() + " was created");
    }

    @PutMapping
    public ResponseEntity<String> updateUser(@RequestBody UserUpdateDto userUpdateDto) {
        userService.update(userUpdateDto);
        return ResponseEntity.ok("User " + userUpdateDto.getId() + " was updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.ok("User " + id + " was deleted");
    }
}
