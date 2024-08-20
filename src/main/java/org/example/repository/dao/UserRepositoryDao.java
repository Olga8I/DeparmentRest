package org.example.repository.dao;
import org.example.model.User;
import org.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRepositoryDao {
    private final UserRepository userRepository;

    @Autowired
    public UserRepositoryDao(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public void add(User user) {
        userRepository.save(user);
    }

    public void update(User user) {
        userRepository.save(user);
    }


    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    public User findById(Long id) {
        return  userRepository.getReferenceById(id);
    }


    public List<User> findAll() {
        return  userRepository.findAll();
    }
}
