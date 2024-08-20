package org.example.repository;

import org.example.config.TestAppConfig;
import org.example.model.Department;
import org.example.repository.dao.DepartmentRepositoryDao;
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
class DepartmentRepositoryDaoTest {

    @Container
    private static final PostgreSQLContainer<?> container =
            new PostgreSQLContainer<>("postgres:latest")
                    .withDatabaseName("deparmentrest");

    private DepartmentRepositoryDao departmentRepositoryDao;

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
        departmentRepositoryDao = context.getBean(DepartmentRepositoryDao.class);
        departmentRepositoryDao.findAll().forEach(department -> departmentRepositoryDao.delete(department.getId()));
    }

    @Test
    void testAddAndFindByIdDepartment() {
        Department department = new Department();
        department.setName("IT Department");

        departmentRepositoryDao.add(department);
        Department foundDepartment = departmentRepositoryDao.findById(department.getId()).orElse(null);

        assertNotNull(foundDepartment);
        assertEquals(department.getName(), foundDepartment.getName());
    }

    @Test
    void testUpdateDepartment() {
        Department department = new Department();
        department.setName("HR Department");

        departmentRepositoryDao.add(department);
        department.setName("Human Resources Department");

        departmentRepositoryDao.update(department);
        Department updatedDepartment = departmentRepositoryDao.findById(department.getId()).orElse(null);

        assertNotNull(updatedDepartment);
        assertEquals("Human Resources Department", updatedDepartment.getName());
    }

    @Test
    void testDeleteDepartment() {
        Department department = new Department();
        department.setName("Finance Department");

        departmentRepositoryDao.add(department);
        Long id = department.getId();

        departmentRepositoryDao.delete(id);
        Department foundDepartment = departmentRepositoryDao.findById(id).orElse(null);

        assertNull(foundDepartment);
    }

    @Test
    void testExistsById() {
        Department department = new Department();
        department.setName("Marketing Department");

        departmentRepositoryDao.add(department);
        Long id = department.getId();

        assertTrue(departmentRepositoryDao.existsById(id));
        departmentRepositoryDao.delete(id);
        assertFalse(departmentRepositoryDao.existsById(id));
    }

    @Test
    void testFindAll() {
        Department department1 = new Department();
        department1.setName("Department 1");

        Department department2 = new Department();
        department2.setName("Department 2");

        departmentRepositoryDao.add(department1);
        departmentRepositoryDao.add(department2);

        List<Department> departments = departmentRepositoryDao.findAll();

        assertEquals(2, departments.size());
        assertTrue(departments.stream().anyMatch(dept -> dept.getName().equals("Department 1")));
        assertTrue(departments.stream().anyMatch(dept -> dept.getName().equals("Department 2")));
    }
}


