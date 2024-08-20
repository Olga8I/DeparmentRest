package org.example.repository;

import org.example.config.TestAppConfig;
import org.example.model.Role;
import org.example.repository.dao.RoleRepositoryDao;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Testcontainers
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestAppConfig.class)
@Tag("DockerRequired")
class RoleRepositoryDaoTest {

    @Container
    private static final PostgreSQLContainer<?> container =
            new PostgreSQLContainer<>("postgres:latest")
                    .withDatabaseName("Aston_rest");

    private RoleRepositoryDao roleRepositoryDao;

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
        roleRepositoryDao = context.getBean(RoleRepositoryDao.class);
        roleRepositoryDao.findAll().forEach(role -> roleRepositoryDao.delete(role.getId()));
    }

    @Test
    void testAddAndFindByIdRole() {
        Role role = new Role("Admin",null);

        roleRepositoryDao.add(role);
        Optional<Role> foundRole = roleRepositoryDao.findById(role.getId());

        assertTrue(foundRole.isPresent());
        assertEquals(role.getName(), foundRole.get().getName());
    }

    @Test
    void testUpdateRole() {
        Role role = new Role("User", null);

        roleRepositoryDao.add(role);
        role.setName("Registered User");

        roleRepositoryDao.update(role);
        Optional<Role> updatedRole = roleRepositoryDao.findById(role.getId());

        assertTrue(updatedRole.isPresent());
        assertEquals("Registered User", updatedRole.get().getName());
    }

    @Test
    void testDeleteRole() {
        Role role = new Role("Guest", null);

        roleRepositoryDao.add(role);
        Long id = role.getId();

        roleRepositoryDao.delete(id);
        Optional<Role> foundRole = roleRepositoryDao.findById(id);

        assertFalse(foundRole.isPresent());
    }

    @Test
    void testExistsById() {
        Role role = new Role("Moderator", null);

        roleRepositoryDao.add(role);
        Long id = role.getId();

        assertTrue(roleRepositoryDao.existsById(id));
        roleRepositoryDao.delete(id);
        assertFalse(roleRepositoryDao.existsById(id));
    }

    @Test
    void testFindAll() {
        Role role1 = new Role("Role 1",null);
        Role role2 = new Role("Role 2", null);

        roleRepositoryDao.add(role1);
        roleRepositoryDao.add(role2);

        List<Role> roles = roleRepositoryDao.findAll();

        assertEquals(2, roles.size());
        assertTrue(roles.stream().anyMatch(r -> r.getName().equals("Role 1")));
        assertTrue(roles.stream().anyMatch(r -> r.getName().equals("Role 2")));
    }
}

