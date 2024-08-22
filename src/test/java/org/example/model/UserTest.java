package org.example.model;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;

class UserTest {

    private User user;
    private Role role;

    @BeforeEach
    void setUp() {
        role = new Role();
        user = new User("John", "Doe", role, new ArrayList<>(), new HashSet<>());
    }

    @Test
    void testUserCreation() {
        assertNotNull(user);
        assertEquals("John", user.getFirstName());
        assertEquals("Doe", user.getLastName());
        assertEquals(role, user.getRole());
        assertTrue(user.getPhoneNumberList().isEmpty());
        assertTrue(user.getDepartmentList().isEmpty());
    }

    @Test
    void testSettersAndGetters() {
        user.setFirstName("Jane");
        user.setLastName("Smith");

        assertEquals("Jane", user.getFirstName());
        assertEquals("Smith", user.getLastName());

        Role newRole = new Role();
        user.setRole(newRole);
        assertEquals(newRole, user.getRole());
    }

    @Test
    void testToString() {
        String expectedString = "User{id=null, firstName='John', lastName='Doe', role=" + role + ", phoneNumberList=[], departmentList=[]}";
        assertEquals(expectedString, user.toString());
    }

    @Test
    void testEqualsAndHashCode() {
        User anotherUser = new User("John", "Doe", role, new ArrayList<>(), new HashSet<>());
        assertEquals(user, anotherUser);
        assertEquals(user.hashCode(), anotherUser.hashCode());

        anotherUser.setId(1L);
        user.setId(1L);
        assertEquals(user, anotherUser);

        anotherUser.setId(2L);
        assertNotEquals(user, anotherUser);
    }

    @Test
    void testPhoneNumbersAndDepartments() {
        PhoneNumber phoneNumber = new PhoneNumber();
        user.getPhoneNumberList().add(phoneNumber);
        assertEquals(1, user.getPhoneNumberList().size());

    }
}

