package org.example.controller;

import org.example.dto.UserCreateDto;
import org.example.dto.UserResponseDto;
import org.example.dto.UserUpdateDto;
import org.example.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

class UserControllerTest {
    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetUserById() {
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(1L);
        userResponseDto.setFirstName("John");
        userResponseDto.setLastName("Doe");

        when(userService.findById(1L)).thenReturn(userResponseDto);

        UserResponseDto user = userController.getUserById(1L);
        Assertions.assertNotNull(user);
        Assertions.assertEquals(user.getId(), userResponseDto.getId());
        Assertions.assertEquals(user.getFirstName(), userResponseDto.getFirstName());
        Assertions.assertEquals(user.getLastName(), userResponseDto.getLastName());
    }

    @Test
    void testGetAllUsers() {
        List<UserResponseDto> users = new ArrayList<>();
        UserResponseDto user1 = new UserResponseDto();
        user1.setId(1L);
        user1.setFirstName("John");
        user1.setLastName("Doe");
        users.add(user1);

        UserResponseDto user2 = new UserResponseDto();
        user2.setId(2L);
        user2.setFirstName("Jane");
        user2.setLastName("Doe");
        users.add(user2);

        when(userService.findAll()).thenReturn(users);

        List<UserResponseDto> responseDtoList = userController.getAllUsers();
        Assertions.assertNotNull(responseDtoList);
        Assertions.assertEquals(2, responseDtoList.size());
        Assertions.assertEquals("John", responseDtoList.get(0).getFirstName());
        Assertions.assertEquals("Jane", responseDtoList.get(1).getFirstName());
    }

    @Test
    void testCreateUser() {
        UserCreateDto userCreateDto = new UserCreateDto();
        userCreateDto.setFirstName("John");
        userCreateDto.setLastName("Doe");

        doNothing().when(userService).save(userCreateDto);

        String result = userController.createUser(userCreateDto);
        Assertions.assertEquals(result, "User John Doe was created");
    }

    @Test
    void testUpdateUser() {
        UserUpdateDto userUpdateDto = new UserUpdateDto();
        userUpdateDto.setId(1L);
        userUpdateDto.setFirstName("John");
        userUpdateDto.setLastName("Doe");

        doNothing().when(userService).update(userUpdateDto);

        String result = userController.updateUser(userUpdateDto);
        Assertions.assertEquals(result, "User with id " + userUpdateDto.getId() + " was updated");
    }

    @Test
    void testDeleteUser() {
        doNothing().when(userService).delete(1L);

        String result = userController.deleteUser(1L);
        Assertions.assertEquals(result, "User with id " + 1L + " was deleted");
    }
}
