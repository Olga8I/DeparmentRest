package org.example.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PhoneNumberTest {

    private PhoneNumber phoneNumber;
    private User user;

    @BeforeEach
    public void setUp() {
        user = new User();
        user.setId(1L);
        phoneNumber = new PhoneNumber("123456789", user);
    }

    @Test
    public void testConstructorAndGetters() {
        assertNotNull(phoneNumber);
        assertEquals("123456789", phoneNumber.getNumber());
        assertEquals(user, phoneNumber.getUser());
    }

    @Test
    public void testSetters() {
        phoneNumber.setNumber("987654321");
        assertEquals("987654321", phoneNumber.getNumber());

        User newUser = new User();
        newUser.setId(2L);
        phoneNumber.setUser(newUser);
        assertEquals(newUser, phoneNumber.getUser());
    }

    @Test
    public void testEquals() {
        PhoneNumber anotherPhoneNumber = new PhoneNumber("123456789", user);
        phoneNumber.setId(1L);
        anotherPhoneNumber.setId(1L);

        assertEquals(phoneNumber, anotherPhoneNumber);
        anotherPhoneNumber.setId(3L);
        assertNotEquals(phoneNumber, anotherPhoneNumber);

        assertNotEquals(phoneNumber, null);
        assertNotEquals(phoneNumber, new Object());
    }
    @Test
    public void testHashCode() {
        PhoneNumber anotherPhoneNumber = new PhoneNumber("123456789", user);
        phoneNumber.setId(1L);
        anotherPhoneNumber.setId(2L);

        assertNotEquals(phoneNumber.hashCode(), anotherPhoneNumber.hashCode());

        anotherPhoneNumber.setId(1L);
        assertEquals(phoneNumber.hashCode(), anotherPhoneNumber.hashCode());
    }

    @Test
    public void testToString() {
        String expectedString = "PhoneNumber{id=null, number='123456789', userId=1}";
        assertEquals(expectedString, phoneNumber.toString());

        phoneNumber.setId(1L);
        expectedString = "PhoneNumber{id=1, number='123456789', userId=1}";
        assertEquals(expectedString, phoneNumber.toString());
    }
}
