package org.example.mapper;

import org.example.dto.PhoneNumberCreateDto;
import org.example.dto.PhoneNumberResponseDto;
import org.example.dto.PhoneNumberUpdateDto;
import org.example.dto.UserResponseDto;
import org.example.model.PhoneNumber;
import org.example.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PhoneNumberMapperTest {

    private PhoneNumberMapper phoneNumberMapper;

    @BeforeEach
    void setUp() {
        phoneNumberMapper = Mappers.getMapper(PhoneNumberMapper.class);
    }

    @Test
    void testMapToEntity() {
        PhoneNumberCreateDto dto = new PhoneNumberCreateDto();
        dto.setNumber("123-456-7890");
        UserResponseDto userDto = new UserResponseDto();
        userDto.setId(2L);

        PhoneNumber phoneNumber = phoneNumberMapper.mapToEntity(dto);

        assertNotNull(phoneNumber);
        assertEquals(dto.getNumber(), phoneNumber.getNumber());
    }

    @Test
    void testMapToDTO() {
        PhoneNumber phoneNumber = new PhoneNumber();
        phoneNumber.setId(1L);
        phoneNumber.setNumber(null);
        User user = new User();
        user.setId(2L);
        phoneNumber.setUser(user);

        PhoneNumberResponseDto dto = phoneNumberMapper.mapToDto(phoneNumber);

        assertNotNull(dto);
        assertEquals(phoneNumber.getId(), dto.getId());
        assertEquals(phoneNumber.getNumber(), dto.getNumberDto());
        assertEquals(phoneNumber.getUser().getId(), dto.getUserDto().getId());
    }

    @Test
    void testMapToDTOList() {
        PhoneNumber phoneNumber1 = new PhoneNumber();
        phoneNumber1.setId(1L);
        phoneNumber1.setNumber("100-056-7890");
        User user1 = new User();
        user1.setId(2L);
        phoneNumber1.setUser(user1);

        PhoneNumber phoneNumber2 = new PhoneNumber();
        phoneNumber2.setId(3L);
        phoneNumber2.setNumber("987-654-3210");
        User user2 = new User();
        user2.setId(4L);
        phoneNumber2.setUser(user2);

        List<PhoneNumber> phoneNumbers = Arrays.asList(phoneNumber1, phoneNumber2);

        List<PhoneNumberUpdateDto> dtoList = phoneNumberMapper.mapToListToDto(phoneNumbers);

        assertNotNull(dtoList);
        assertEquals(2, dtoList.size());

        PhoneNumberUpdateDto dto1 = dtoList.get(0);
        assertEquals(phoneNumber1.getId(), dto1.getId());
        assertEquals(phoneNumber1.getNumber(), dto1.getNumber());

        PhoneNumberUpdateDto dto2 = dtoList.get(1);
        assertEquals(phoneNumber2.getId(), dto2.getId());
        assertEquals(phoneNumber2.getNumber(), dto2.getNumber());
    }
}
