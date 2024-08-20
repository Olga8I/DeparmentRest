package org.example.service.impl;

import org.example.exception.NotFoundException;
import org.example.mapper.UserMapper;
import org.example.model.User;
import org.example.repository.dao.UserRepositoryDao;
import org.example.service.UserService;
import org.example.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {

    private final UserRepositoryDao userRepositoryDao;
    private final UserMapper userMapper;
    @Autowired
    public UserServiceImpl(UserRepositoryDao userRepositoryDao,
                           UserMapper userMapper) {
        this.userMapper = userMapper;
        this.userRepositoryDao = userRepositoryDao;
    }

    private void checkExistUser(Long userId) throws NotFoundException {
        if (!userRepositoryDao.findAll().stream().anyMatch(user -> user.getId().equals(userId))) {
            throw new NotFoundException("User not found.");
        }
    }

    @Override
    public void save(UserDto userDto) {
        User user = userMapper.mapToEntity(userDto);
    }

    @Override
    public void update(UserDto userDto) throws NotFoundException {
        if (userDto == null || userDto.getId() == null) {
            throw new IllegalArgumentException("UserDto or user ID must not be null");
        }
        checkExistUser(userDto.getId());
        User user = userMapper.mapToEntity(userDto);
        userRepositoryDao.add(user);
    }

    @Override
    public UserDto findById(Long userId) throws NotFoundException {
        checkExistUser(userId);
        User user = userRepositoryDao.findById(userId);
        return userMapper.mapToDto(user);
    }

    @Override
    public List<UserDto> findAll() {
        List<User> allUsers = userRepositoryDao.findAll();
        return userMapper.mapToListToDto(allUsers);
    }

    @Override
    public void delete(Long userId) throws NotFoundException {
        checkExistUser(userId);
        userRepositoryDao.delete(userId);
    }
}

