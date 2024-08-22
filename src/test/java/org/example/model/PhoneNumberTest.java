package org.example.model;

import org.example.model.PhoneNumber;
import org.example.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PhoneNumberTest {

    private PhoneNumber phoneNumber;
    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(1L);
        phoneNumber = new PhoneNumber("123-456-7890", user);
    }

    @Test
    void testConstructor() {
        assertNotNull(phoneNumber);
        assertEquals("123-456-7890", phoneNumber.getNumber());
        assertEquals(user, phoneNumber.getUser());
    }

    @Test
    void testGetId() {
        assertNull(phoneNumber.getId());
    }

    @Test
    void testSetId() {
        phoneNumber.setId(2L);
        assertEquals(2L, phoneNumber.getId());
    }

    @Test
    void testGetNumber() {
        assertEquals("123-456-7890", phoneNumber.getNumber());
    }

    @Test
    void testSetNumber() {
        phoneNumber.setNumber("987-654-3210");
        assertEquals("987-654-3210", phoneNumber.getNumber());
    }

    @Test
    void testGetUser() {
        assertEquals(user, phoneNumber.getUser());
    }

    @Test
    void testSetUser() {
        User newUser = new User();
        newUser.setId(2L);
        phoneNumber.setUser(newUser);
        assertEquals(newUser, phoneNumber.getUser());
    }

    @Test
    void testEquals() {
        PhoneNumber anotherPhoneNumber = new PhoneNumber("123-456-7890", user);
        phoneNumber.setId(1L);
        anotherPhoneNumber.setId(1L);

        assertEquals(phoneNumber, anotherPhoneNumber);
    }

    @Test
    void testNotEqualsDifferentId() {
        PhoneNumber anotherPhoneNumber = new PhoneNumber("123-456-7890", user);
        phoneNumber.setId(1L);
        anotherPhoneNumber.setId(2L);

        assertNotEquals(phoneNumber, anotherPhoneNumber);
    }

    @Test
    void testHashCode() {
        phoneNumber.setId(1L);
        assertEquals(phoneNumber.hashCode(), phoneNumber.hashCode());
    }

    @Test
    void testToString() {
        phoneNumber.setId(1L);
        String expectedString = "PhoneNumber{id=1, number='123-456-7890', userId=1}";
        assertEquals(expectedString, phoneNumber.toString());
    }
}