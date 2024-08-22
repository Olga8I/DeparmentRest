package org.example.dto;

import org.example.dto.PhoneNumberDto;
import org.example.dto.UserDto;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PhoneNumberDtoTest {

    @Test
    public void testDefaultConstructor() {
        PhoneNumberDto phoneNumberDto = new PhoneNumberDto();
        assertNull(phoneNumberDto.getId());
        assertNull(phoneNumberDto.getNumberDto());
        assertNull(phoneNumberDto.getUserDto());
    }

    @Test
    public void testParameterizedConstructor() {
        UserDto userDto = new UserDto();
        PhoneNumberDto phoneNumberDto = new PhoneNumberDto("123456789", userDto);
        assertNull(phoneNumberDto.getId());
        assertEquals("123456789", phoneNumberDto.getNumberDto());
        assertEquals(userDto, phoneNumberDto.getUserDto());
    }

    @Test
    public void testSettersAndGetters() {
        PhoneNumberDto phoneNumberDto = new PhoneNumberDto();

        UserDto userDto = new UserDto();
        phoneNumberDto.setId(1L);
        phoneNumberDto.setNumberDto("987654321");
        phoneNumberDto.setUserDto(userDto);

        assertEquals(1L, phoneNumberDto.getId());
        assertEquals("987654321", phoneNumberDto.getNumberDto());
        assertEquals(userDto, phoneNumberDto.getUserDto());
    }

    @Test
    public void testEqualsAndHashCode() {
        UserDto userDto1 = new UserDto();
        UserDto userDto2 = new UserDto();

        PhoneNumberDto phoneNumberDto1 = new PhoneNumberDto("123456789", userDto1);
        PhoneNumberDto phoneNumberDto2 = new PhoneNumberDto("123456789", userDto1);
        PhoneNumberDto phoneNumberDto3 = new PhoneNumberDto("987654321", userDto2);

        assertEquals(phoneNumberDto1, phoneNumberDto2);
        assertNotEquals(phoneNumberDto1, phoneNumberDto3);
        assertEquals(phoneNumberDto1.hashCode(), phoneNumberDto2.hashCode());
        assertNotEquals(phoneNumberDto1.hashCode(), phoneNumberDto3.hashCode());
    }

    @Test
    public void testToString() {
        UserDto userDto = new UserDto();
        PhoneNumberDto phoneNumberDto = new PhoneNumberDto("123456789", userDto);
        phoneNumberDto.setId(1L);

        String expectedString = "PhoneNumberDto{id=1, number='123456789', userDto=" + userDto + "}";
        assertEquals(expectedString, phoneNumberDto.toString());
    }
}
