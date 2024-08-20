package org.example.service;

import org.example.dto.UserDto;
import org.example.exception.NotFoundException;

import java.util.List;

public interface UserService {
    void save(UserDto userDto);

    void update(UserDto userDto) throws NotFoundException;

    UserDto findById(Long userId) throws NotFoundException;

    List<UserDto> findAll();

    void delete(Long userId) throws NotFoundException;
}
