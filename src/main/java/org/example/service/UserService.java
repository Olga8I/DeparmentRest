package org.example.service;

import org.example.dto.UserCreateDto;
import org.example.dto.UserResponseDto;
import org.example.dto.UserUpdateDto;

import java.util.List;

public interface UserService {
    void save(UserCreateDto userCreateDto);

    void update(UserUpdateDto userUpdateDto);

    UserResponseDto findById(Long userId);

    List<UserResponseDto> findAll();

    void delete(Long userId);
}
