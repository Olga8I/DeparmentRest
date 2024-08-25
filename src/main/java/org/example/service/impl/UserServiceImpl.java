package org.example.service.impl;

import org.example.dto.UserCreateDto;
import org.example.dto.UserResponseDto;
import org.example.dto.UserUpdateDto;
import org.example.exception.NotFoundException;
import org.example.mapper.UserMapper;
import org.example.model.User;
import org.example.repository.UserRepository;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           UserMapper userMapper) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }

    private void checkExistUser(Long userId) throws NotFoundException {
        if (!userRepository.findAll().stream().anyMatch(user -> user.getId().equals(userId))) {
            throw new NotFoundException("User not found.");
        }
    }

    @Override
    public void save(UserCreateDto userCreateDto) {
        User user = userMapper.mapToEntity(userCreateDto);
        userRepository.save(user);
    }

    @Override
    public void update(UserUpdateDto userUpdateDto) throws NotFoundException {
        User user = userRepository.findById(userUpdateDto.getId()).orElseThrow(
                () -> new NotFoundException("User not found.")
        );
        user.setFirstName(userUpdateDto.getFirstName());
        user.setLastName(userUpdateDto.getLastName());
        userRepository.save(user);
    }

    @Override
    public UserResponseDto findById(Long userId) throws NotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found."));
        return userMapper.mapToDto(user);
    }

    @Override
    public List<UserResponseDto> findAll() {
        return userRepository.findAll().stream().map(userMapper::mapToDto).toList();
    }

    @Override
    public void delete(Long userId) throws NotFoundException {
        if (userRepository.findById(userId).isPresent()) {
            userRepository.deleteById(userId);
        } else {
            throw new NotFoundException("User not found.");
        }
    }
}

