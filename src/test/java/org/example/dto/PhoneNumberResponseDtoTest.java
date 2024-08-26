package org.example.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PhoneNumberResponseDtoTest {

    @Test
    public void testDefaultConstructor() {
        PhoneNumberResponseDto phoneNumberResponseDto = new PhoneNumberResponseDto();
        assertNull(phoneNumberResponseDto.getId());
        assertNull(phoneNumberResponseDto.getNumberDto());
        assertNull(phoneNumberResponseDto.getUserDto());
    }

    @Test
    public void testParameterizedConstructor() {
        UserResponseDto userResponseDto = new UserResponseDto();
        PhoneNumberResponseDto phoneNumberResponseDto = new PhoneNumberResponseDto("123456789", userResponseDto);
        assertNull(phoneNumberResponseDto.getId());
        assertEquals("123456789", phoneNumberResponseDto.getNumberDto());
        assertEquals(userResponseDto, phoneNumberResponseDto.getUserDto());
    }

    @Test
    public void testSettersAndGetters() {
        PhoneNumberResponseDto phoneNumberResponseDto = new PhoneNumberResponseDto();

        UserResponseDto userResponseDto = new UserResponseDto();
        phoneNumberResponseDto.setId(1L);
        phoneNumberResponseDto.setNumberDto("987654321");
        phoneNumberResponseDto.setUserDto(userResponseDto);

        assertEquals(1L, phoneNumberResponseDto.getId());
        assertEquals("987654321", phoneNumberResponseDto.getNumberDto());
        assertEquals(userResponseDto, phoneNumberResponseDto.getUserDto());
    }

    @Test
    public void testEqualsAndHashCode() {
        UserResponseDto userResponseDto1 = new UserResponseDto();
        PhoneNumberResponseDto phoneNumberResponseDto1 = new PhoneNumberResponseDto("123456789", userResponseDto1);
        phoneNumberResponseDto1.setId(1L);

        PhoneNumberResponseDto phoneNumberResponseDto2 = new PhoneNumberResponseDto("123456789", userResponseDto1);
        phoneNumberResponseDto2.setId(1L);

        PhoneNumberResponseDto phoneNumberResponseDto3 = new PhoneNumberResponseDto("987654321", userResponseDto1);
        phoneNumberResponseDto3.setId(2L);

        UserResponseDto userResponseDto2 = new UserResponseDto();
        PhoneNumberResponseDto phoneNumberResponseDto4 = new PhoneNumberResponseDto("123456789", userResponseDto2);
        phoneNumberResponseDto4.setId(3L);

        assertEquals(phoneNumberResponseDto1, phoneNumberResponseDto2);
        assertEquals(phoneNumberResponseDto1.hashCode(), phoneNumberResponseDto2.hashCode());

        assertNotEquals(phoneNumberResponseDto1, phoneNumberResponseDto3);
        assertNotEquals(phoneNumberResponseDto1.hashCode(), phoneNumberResponseDto3.hashCode());

        assertNotEquals(phoneNumberResponseDto1, phoneNumberResponseDto4);
        assertNotEquals(phoneNumberResponseDto1.hashCode(), phoneNumberResponseDto4.hashCode());

        assertEquals(phoneNumberResponseDto1, phoneNumberResponseDto1);
        assertEquals(phoneNumberResponseDto1.hashCode(), phoneNumberResponseDto1.hashCode());

        assertNotEquals(phoneNumberResponseDto1, null);

        assertNotEquals(phoneNumberResponseDto1, new Object());
    }


    @Test
    public void testToString() {
        UserResponseDto userResponseDto = new UserResponseDto();
        PhoneNumberResponseDto phoneNumberResponseDto = new PhoneNumberResponseDto("123456789", userResponseDto);
        phoneNumberResponseDto.setId(1L);

        String expectedString = "PhoneNumberResponseDto{id=1, numberDto='123456789', userResponseDto=" + userResponseDto + "}";
        assertEquals(expectedString, phoneNumberResponseDto.toString());
    }
}
