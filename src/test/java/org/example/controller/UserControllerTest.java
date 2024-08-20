package org.example.controller;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.example.dto.UserCreateDto;
import org.example.dto.UserResponseDto;
import org.example.dto.UserUpdateDto;
import org.example.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

public class UserControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void getUserById_ShouldReturnUser() throws Exception {
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(1L);
        userResponseDto.setFirstName("John");
        userResponseDto.setLastName("Doe");

        when(userService.findById(1L)).thenReturn(userResponseDto);

        mockMvc.perform(get("/api/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("John"))
                .andExpect(jsonPath("$.lastName").value("Doe"));
    }

    @Test
    public void getAllUsers_ShouldReturnListOfUsers() throws Exception {
        UserResponseDto user1 = new UserResponseDto();
        user1.setId(1L);
        user1.setFirstName("John");
        user1.setLastName("Doe");

        UserResponseDto user2 = new UserResponseDto();
        user2.setId(2L);
        user2.setFirstName("Jane");
        user2.setLastName("Doe");

        List<UserResponseDto> users = Arrays.asList(user1, user2);

        when(userService.findAll()).thenReturn(users);

        mockMvc.perform(get("/api/users/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName").value("John"))
                .andExpect(jsonPath("$[1].firstName").value("Jane"));
    }

    @Test
    public void createUser_ShouldReturnCreatedMessage() throws Exception {
        UserCreateDto userCreateDto = new UserCreateDto();
        userCreateDto.setFirstName("John");
        userCreateDto.setLastName("Doe");

        doNothing().when(userService).save(any(UserCreateDto.class));

        mockMvc.perform(post("/api/users")
                        .contentType("application/json")
                        .content("{\"firstName\": \"John\", \"lastName\": \"Doe\"}"))
                .andExpect(status().isCreated())
                .andExpect(content().string("User John Doe was created"));
    }

    @Test
    public void updateUser_ShouldReturnUpdateMessage() throws Exception {
        UserUpdateDto userUpdateDto = new UserUpdateDto();
        userUpdateDto.setId(1L);
        userUpdateDto.setFirstName("John");
        userUpdateDto.setLastName("Doe");

        doNothing().when(userService).update(any(UserUpdateDto.class));

        mockMvc.perform(put("/api/users")
                        .contentType("application/json")
                        .content("{\"id\":1, \"firstName\": \"John\", \"lastName\": \"Doe\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("User 1 was updated"));
    }

    @Test
    public void deleteUser_ShouldReturnDeletedMessage() throws Exception {
        doNothing().when(userService).delete(1L);

        mockMvc.perform(delete("/api/users/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("User 1 was deleted"));
    }
}
