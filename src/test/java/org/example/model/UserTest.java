package org.example.model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {
    private User user;
    private Role role;
    private PhoneNumber phoneNumber1;
    private PhoneNumber phoneNumber2;
    private Department department1;
    private Department department2;

    @BeforeEach
    public void setUp() {
        role = new Role();
        role.setId(1L);

        phoneNumber1 = new PhoneNumber("123456789", user);
        phoneNumber2 = new PhoneNumber("987654321", user);

        department1 = new Department();
        department1.setId(1L);
        department2 = new Department();
        department2.setId(2L);

        user = new User(
                "John",
                "Doe",
                role,
                new ArrayList<>(Arrays.asList(phoneNumber1, phoneNumber2)),
                new HashSet<>(Arrays.asList(department1, department2))
        );
    }

    @Test
    public void testUserInitialization() {
        assertNotNull(user);
        assertEquals("John", user.getFirstName());
        assertEquals("Doe", user.getLastName());
        assertEquals(role, user.getRole());
        assertEquals(2, user.getPhoneNumberList().size());
        assertEquals(2, user.getDepartmentList().size());
    }

    @Test
    public void testGettersAndSetters() {
        user.setFirstName("Jane");
        assertEquals("Jane", user.getFirstName());

        user.setLastName("Smith");
        assertEquals("Smith", user.getLastName());

        Role newRole = new Role();
        newRole.setId(2L);
        user.setRole(newRole);
        assertEquals(newRole, user.getRole());

        PhoneNumber newPhoneNumber = new PhoneNumber("5555555", user);
        user.getPhoneNumberList().add(newPhoneNumber);
        assertEquals(3, user.getPhoneNumberList().size());

        Department newDepartment = new Department();
        newDepartment.setId(3L);
        user.getDepartmentList().add(newDepartment);
        assertEquals(3, user.getDepartmentList().size());
    }

    @Test
    public void testEqualsAndHashCode() {
        User user1 = new User();
        user1.setId(1L);

        User user2 = new User();
        user2.setId(1L);

        User user3 = new User();
        user3.setId(2L);

        assertEquals(user1, user2);
        assertEquals(user1.hashCode(), user2.hashCode());

        assertNotEquals(user1, user3);
        assertNotEquals(user1.hashCode(), user3.hashCode());

        assertNotEquals(user1, null);

        assertNotEquals(user1, new Object());
    }



    @Test
    public void testToString() {
        String expectedString = "User{" +
                "id=null" +
                ", firstName='John'" +
                ", lastName='Doe'" +
                ", role=" + role +
                ", phoneNumberList=" + user.getPhoneNumberList() +
                ", departmentList=" + user.getDepartmentList() +
                '}';
        assertEquals(expectedString, user.toString().substring(0, expectedString.length()));
    }
}