package org.example.repository;

import org.example.config.TestAppConfig;
import org.example.model.PhoneNumber;
import org.example.model.User;
import org.example.repository.dao.PhoneNumberRepositoryDao;
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
class PhoneNumberRepositoryDaoTest {

    @Container
    private static final PostgreSQLContainer<?> container =
            new PostgreSQLContainer<>("postgres:latest")
                    .withDatabaseName("Aston_rest");

    private PhoneNumberRepositoryDao phoneNumberRepositoryDao;

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
        phoneNumberRepositoryDao = context.getBean(PhoneNumberRepositoryDao.class);
        phoneNumberRepositoryDao.findAll().forEach(phoneNumber -> phoneNumberRepositoryDao.delete(phoneNumber.getId()));
    }

    @Test
    void testAddAndFindByIdPhoneNumber() {
        User user = new User();
        PhoneNumber phoneNumber = new PhoneNumber("123456789", user);

        phoneNumberRepositoryDao.add(phoneNumber);
        PhoneNumber foundPhoneNumber = phoneNumberRepositoryDao.findById(phoneNumber.getId()).orElse(null);

        assertNotNull(foundPhoneNumber);
        assertEquals(phoneNumber.getNumber(), foundPhoneNumber.getNumber());
    }

    @Test
    void testUpdatePhoneNumber() {
        User user = new User();
        PhoneNumber phoneNumber = new PhoneNumber("987654321", user);

        phoneNumberRepositoryDao.add(phoneNumber);
        phoneNumber.setNumber("123123123");

        phoneNumberRepositoryDao.update(phoneNumber);
        PhoneNumber updatedPhoneNumber = phoneNumberRepositoryDao.findById(phoneNumber.getId()).orElse(null);

        assertNotNull(updatedPhoneNumber);
        assertEquals("123123123", updatedPhoneNumber.getNumber());
    }

    @Test
    void testDeletePhoneNumber() {
        User user = new User();
        PhoneNumber phoneNumber = new PhoneNumber("555555555", user);

        phoneNumberRepositoryDao.add(phoneNumber);
        Long id = phoneNumber.getId();

        phoneNumberRepositoryDao.delete(id);
        PhoneNumber foundPhoneNumber = phoneNumberRepositoryDao.findById(id).orElse(null);

        assertNull(foundPhoneNumber);
    }

    @Test
    void testExistsById() {
        User user = new User();
        PhoneNumber phoneNumber = new PhoneNumber("666666666", user);

        phoneNumberRepositoryDao.add(phoneNumber);
        Long id = phoneNumber.getId();

        assertTrue(phoneNumberRepositoryDao.existsById(id));
        phoneNumberRepositoryDao.delete(id);
        assertFalse(phoneNumberRepositoryDao.existsById(id));
    }

    @Test
    void testFindAll() {
        User user = new User();
        PhoneNumber phoneNumber1 = new PhoneNumber("123123123", user);
        PhoneNumber phoneNumber2 = new PhoneNumber("456456456", user);

        phoneNumberRepositoryDao.add(phoneNumber1);
        phoneNumberRepositoryDao.add(phoneNumber2);

        List<PhoneNumber> phoneNumbers = phoneNumberRepositoryDao.findAll();

        assertEquals(2, phoneNumbers.size());
        assertTrue(phoneNumbers.stream().anyMatch(phone -> phone.getNumber().equals("123123123")));
        assertTrue(phoneNumbers.stream().anyMatch(phone -> phone.getNumber().equals("456456456")));
    }
}

