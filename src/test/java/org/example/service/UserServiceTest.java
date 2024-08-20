package org.example.service;

import org.example.dto.UserDto;
import org.example.exception.NotFoundException;
import org.example.mapper.UserMapper;
import org.example.model.User;
import org.example.repository.dao.UserRepositoryDao;
import org.example.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepositoryDao userRepositoryDao;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserServiceImpl userService;

    private UserDto userDto;
    private User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userDto = new UserDto();
        userDto.setId(1L);
        userDto.setFirstName("John");
        userDto.setLastName("Doe");

        user = new User();
        user.setId(1L);
        user.setFirstName("John");
        user.setLastName("Doe");
    }

    @Test
    void testUpdateUserSuccessfully() throws NotFoundException {
        when(userMapper.mapToEntity(userDto)).thenReturn(user);
        when(userRepositoryDao.findAll()).thenReturn(new ArrayList<>() {{
            add(user);
        }});

        userService.update(userDto);

        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        verify(userRepositoryDao, times(1)).add(userCaptor.capture());

        assertEquals(user, userCaptor.getValue());
    }

    @Test
    void testUpdateUserNotFound() {
        userDto.setId(2L);
        when(userRepositoryDao.findAll()).thenReturn(new ArrayList<>() {{
            add(user);
        }});

        NotFoundException exception = assertThrows(NotFoundException.class, () -> userService.update(userDto));
        assertEquals("User not found.", exception.getMessage());
    }

    @Test
    void testFindByIdUserExists() throws NotFoundException {
        when(userRepositoryDao.findAll()).thenReturn(new ArrayList<>() {{
            add(user);
        }});
        when(userRepositoryDao.findById(1L)).thenReturn(user);
        when(userMapper.mapToDto(user)).thenReturn(userDto);

        UserDto foundUserDto = userService.findById(1L);

        assertEquals(userDto, foundUserDto);
    }

    @Test
    void testFindByIdUserNotFound() {
        NotFoundException exception = assertThrows(NotFoundException.class, () -> userService.findById(2L));
        assertEquals("User not found.", exception.getMessage());
    }

    @Test
    void testDeleteUserSuccessfully() throws NotFoundException {
        when(userRepositoryDao.findAll()).thenReturn(new ArrayList<>() {{
            add(user);
        }});

        userService.delete(1L);
        verify(userRepositoryDao, times(1)).delete(1L);
    }

    @Test
    void testDeleteUserNotFound() {
        NotFoundException exception = assertThrows(NotFoundException.class, () -> userService.delete(2L));
        assertEquals("User not found.", exception.getMessage());
    }
}

