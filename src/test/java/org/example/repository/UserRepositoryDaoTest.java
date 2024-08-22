package org.example.repository;

import org.example.config.TestAppConfig;
import org.example.model.User;
import org.example.repository.dao.UserRepositoryDao;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Testcontainers
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestAppConfig.class)
@Tag("DockerRequired")
class UserRepositoryDaoTest {

    @Container
    private static final PostgreSQLContainer<?> container =
            new PostgreSQLContainer<>("postgres:latest")
                    .withDatabaseName("Aston_rest");

    private UserRepositoryDao userRepositoryDao;

    @BeforeAll
    public static void setUp() {
        container.start();
        System.setProperty("spring.datasource.url", container.getJdbcUrl());
        System.setProperty("spring.datasource.username", container.getUsername());
        System.setProperty("spring.datasource.password", container.getPassword());
    }

    @BeforeEach
    void init() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TestAppConfig.class);
        userRepositoryDao = context.getBean(UserRepositoryDao.class);
        userRepositoryDao.findAll().forEach(user -> userRepositoryDao.delete(user.getId()));
    }

    @Test
    void testAddAndFindByIdUser() {
        User user = new User();
        user.setFirstName("John");
        user.setLastName("Doe");

        userRepositoryDao.add(user);
        User foundUser = userRepositoryDao.findById(user.getId());

        assertNotNull(foundUser);
        assertEquals(user.getFirstName(), foundUser.getFirstName());
        assertEquals(user.getLastName(), foundUser.getLastName());
    }

    @Test
    void testUpdateUser() {
        User user = new User();
        user.setFirstName("Jane");
        user.setLastName("Smith");

        userRepositoryDao.add(user);
        user.setFirstName("Janet");
        user.setLastName("Smithson");

        userRepositoryDao.update(user);
        User updatedUser = userRepositoryDao.findById(user.getId());

        assertNotNull(updatedUser);
        assertEquals("Janet", updatedUser.getFirstName());
        assertEquals("Smithson", updatedUser.getLastName());
    }

    @Test
    void testDeleteUser() {
        User user = new User();
        user.setFirstName("Alice");
        user.setLastName("Johnson");

        userRepositoryDao.add(user);
        Long id = user.getId();

        userRepositoryDao.delete(id);
        User foundUser = userRepositoryDao.findById(id);

        assertNull(foundUser);
    }

    @Test
    void testFindAll() {
        User user1 = new User();
        user1.setFirstName("User One");
        user1.setLastName("Last One");

        User user2 = new User();
        user2.setFirstName("User Two");
        user2.setLastName("Last Two");

        userRepositoryDao.add(user1);
        userRepositoryDao.add(user2);

        List<User> users = userRepositoryDao.findAll();

        assertEquals(2, users.size());
        assertTrue(users.stream().anyMatch(u -> u.getFirstName().equals("User One")));
        assertTrue(users.stream().anyMatch(u -> u.getFirstName().equals("User Two")));
    }
}
