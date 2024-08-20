package org.example.controller;

import org.example.controller.UserController;
import org.example.dto.UserDto;
import org.example.exception.NotFoundException;
import org.example.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;

public class UserControllerTest {

    private UserService userService;
    private UserController userController;

    @BeforeEach
    public void setUp() {
        userService = Mockito.mock(UserService.class);
        userController = new UserController(userService);
    }

    @Test
    public void createUser_ShouldReturnCreated() {
        UserDto userDto = new UserDto();
        userDto.setId(1L);

        ResponseEntity<Void> response = userController.createUser(userDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(userService).save(userDto);
    }

    @Test
    public void updateUser_ShouldReturnOk_WhenUserUpdated() throws NotFoundException {
        UserDto userDto = new UserDto();
        userDto.setId(1L);

        ResponseEntity<Void> response = userController.updateUser(1L, userDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(userService).update(userDto);
    }

    @Test
    public void updateUser_ShouldReturnBadRequest_WhenIdsDoNotMatch() {
        UserDto userDto = new UserDto();
        userDto.setId(2L);

        ResponseEntity<Void> response = userController.updateUser(1L, userDto);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void getUserById_ShouldReturnOk_WhenUserExists() throws NotFoundException {
        UserDto userDto = new UserDto();
        userDto.setId(1L);

        when(userService.findById(1L)).thenReturn(userDto);

        ResponseEntity<UserDto> response = userController.getUserById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userDto, response.getBody());
    }

    @Test
    public void getUserById_ShouldReturnNotFound_WhenUserDoesNotExist() throws NotFoundException {
        when(userService.findById(1L)).thenThrow(new NotFoundException("User not found."));

        ResponseEntity<UserDto> response = userController.getUserById(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void getAllUsers_ShouldReturnListOfUsers() {
        UserDto userDto1 = new UserDto();
        UserDto userDto2 = new UserDto();
        List<UserDto> userList = List.of(userDto1, userDto2);

        when(userService.findAll()).thenReturn(userList);

        ResponseEntity<List<UserDto>> response = userController.getAllUsers();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userList, response.getBody());
    }

    @Test
    public void deleteUser_ShouldReturnNoContent_WhenUserDeleted() throws NotFoundException {
        ResponseEntity<Void> response = userController.deleteUser(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(userService).delete(1L);
    }

    @Test
    public void deleteUser_ShouldReturnNotFound_WhenUserDoesNotExist() throws NotFoundException {
        doThrow(new NotFoundException("User not found.")).when(userService).delete(1L);

        ResponseEntity<Void> response = userController.deleteUser(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}

