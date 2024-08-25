package org.example.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class PhoneNumberUpdateDtoTest {

    @Test
    public void testDefaultConstructor() {
        PhoneNumberUpdateDto phoneNumberUpdateDto = new PhoneNumberUpdateDto();
        assertNull(phoneNumberUpdateDto.getId());
        assertNull(phoneNumberUpdateDto.getNumber());
    }

    @Test
    public void testParameterizedConstructor() {
        PhoneNumberUpdateDto phoneNumberUpdateDto = new PhoneNumberUpdateDto(1L, "123456789");
        assertEquals(1L, phoneNumberUpdateDto.getId());
        assertEquals("123456789", phoneNumberUpdateDto.getNumber());
    }

    @Test
    public void testSettersAndGetters() {
        PhoneNumberUpdateDto phoneNumberUpdateDto = new PhoneNumberUpdateDto();
        phoneNumberUpdateDto.setId(2L);
        phoneNumberUpdateDto.setNumber("987654321");

        assertEquals(2L, phoneNumberUpdateDto.getId());
        assertEquals("987654321", phoneNumberUpdateDto.getNumber());
    }
}