package org.example.service;
import org.example.dto.UserCreateDto;
import org.example.dto.UserResponseDto;
import org.example.dto.UserUpdateDto;
import org.example.exception.NotFoundException;
import org.example.mapper.UserMapper;
import org.example.model.User;
import org.example.repository.UserRepository;
import org.example.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserServiceImpl userService;

    private UserCreateDto userCreateDto;
    private UserUpdateDto userUpdateDto;
    private UserResponseDto userResponseDto;
    private User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        userCreateDto = new UserCreateDto("John", "Doe", null);
        userUpdateDto = new UserUpdateDto("John", "Doe", null, 1L);

        userResponseDto = new UserResponseDto();
        userResponseDto.setId(1L);
        userResponseDto.setFirstName("John");
        userResponseDto.setLastName("Doe");

        user = new User();
        user.setId(1L);
        user.setFirstName("John");
        user.setLastName("Doe");
    }

    @Test
    void testSaveUserSuccessfully() {
        when(userMapper.mapToEntity(userCreateDto)).thenReturn(user);

        userService.save(userCreateDto);

        verify(userRepository, times(1)).save(user);
    }

    @Test
    void testUpdateUserSuccessfully() throws NotFoundException {
        when(userMapper.mapToEntity(userCreateDto)).thenReturn(user);
        when(userRepository.findById(1L)).thenReturn(java.util.Optional.of(user));

        userService.update(userUpdateDto);

        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        verify(userRepository, times(1)).save(userCaptor.capture());

        assertEquals(user.getFirstName(), userCaptor.getValue().getFirstName());
        assertEquals(user.getLastName(), userCaptor.getValue().getLastName());
    }

    @Test
    void testUpdateUserNotFound() {
        UserUpdateDto updateDto = new UserUpdateDto("Jane", "Doe", null, 2L);
        when(userRepository.findById(2L)).thenReturn(java.util.Optional.empty());

        NotFoundException exception = assertThrows(NotFoundException.class, () -> userService.update(updateDto));
        assertEquals("User not found.", exception.getMessage());
    }

    @Test
    void testFindByIdUserExists() throws NotFoundException {
        when(userRepository.findById(1L)).thenReturn(java.util.Optional.of(user));
        when(userMapper.mapToDto(user)).thenReturn(userResponseDto);

        UserResponseDto foundUserResponseDto = userService.findById(1L);

        assertEquals(userResponseDto, foundUserResponseDto);
    }

    @Test
    void testFindByIdUserNotFound() {
        when(userRepository.findById(2L)).thenReturn(java.util.Optional.empty());

        NotFoundException exception = assertThrows(NotFoundException.class, () -> userService.findById(2L));
        assertEquals("User not found.", exception.getMessage());
    }

    @Test
    void testDeleteUserSuccessfully() throws NotFoundException {
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        userService.delete(user.getId());
        verify(userRepository).deleteById(1L);
    }

    @Test
    void testDeleteUserNotFound() {
        when(userRepository.findById(2L)).thenReturn(java.util.Optional.empty());

        NotFoundException exception = assertThrows(NotFoundException.class, () -> userService.delete(2L));
        assertEquals("User not found.", exception.getMessage());
    }
    @Test
    void testFindAllUsers() {
        List<User> users = List.of(user);
        when(userRepository.findAll()).thenReturn(users);
        when(userMapper.mapToDto(any())).thenAnswer(invocation -> {
            User user = invocation.getArgument(0);
            return new UserResponseDto(user.getFirstName(), user.getLastName(), null, null, null);
        });

        List<UserResponseDto> userResponseDtos = userService.findAll();

        assertEquals(1, userResponseDtos.size());
        assertEquals(userResponseDto, userResponseDtos.get(0));
    }

    @Test
    void testDeleteUserWithCheckExist() throws NotFoundException {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        userService.delete(1L);
        verify(userRepository, times(1)).deleteById(1L);
    }

    @Test
    void testCheckExistUserExists() throws NotFoundException {
        when(userRepository.findAll()).thenReturn(List.of(user));

        userService.checkExistUser(1L);
    }

    @Test
    void testCheckExistUserNotExists() {
        when(userRepository.findAll()).thenReturn(List.of());

        NotFoundException exception = assertThrows(NotFoundException.class, () -> userService.checkExistUser(2L));
        assertEquals("User not found.", exception.getMessage());
    }

}
