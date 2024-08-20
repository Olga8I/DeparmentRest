package org.example.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class PhoneNumberCreateDtoTest {

    @Test
    public void testDefaultConstructor() {
        PhoneNumberCreateDto phoneNumberCreateDto = new PhoneNumberCreateDto();
        assertNull(phoneNumberCreateDto.getNumber());
    }

    @Test
    public void testParameterizedConstructor() {
        PhoneNumberCreateDto phoneNumberCreateDto = new PhoneNumberCreateDto("123456789");
        assertEquals("123456789", phoneNumberCreateDto.getNumber());
    }

    @Test
    public void testSettersAndGetters() {
        PhoneNumberCreateDto phoneNumberCreateDto = new PhoneNumberCreateDto();
        phoneNumberCreateDto.setNumber("987654321");
        assertEquals("987654321", phoneNumberCreateDto.getNumber());
    }
}
