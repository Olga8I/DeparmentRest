package org.example.mapper;

import org.example.dto.PhoneNumberDto;
import org.example.dto.UserDto;
import org.example.mapper.PhoneNumberMapper;
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
        PhoneNumberDto dto = new PhoneNumberDto();
        dto.setNumberDto("123-456-7890");
        dto.setUserDto(new UserDto());

        PhoneNumber phoneNumber = phoneNumberMapper.mapToEntity(dto);

        assertNotNull(phoneNumber);
        assertEquals(dto.getId(), phoneNumber.getId());
        assertEquals(dto.getNumberDto(), phoneNumber.getNumber());
        assertEquals(dto.getUserDto().getId(), phoneNumber.getUser().getId());
    }

    @Test
    void testMapToDTO() {
        PhoneNumber phoneNumber = new PhoneNumber();
        phoneNumber.setNumber("123-456-7890");
        phoneNumber.setUser(new User());

        PhoneNumberDto dto = phoneNumberMapper.mapToDto(phoneNumber);

        assertNotNull(dto);
        assertEquals(phoneNumber.getId(), dto.getId());
        assertEquals(phoneNumber.getNumber(), dto.getNumberDto());
        assertEquals(phoneNumber.getUser().getId(), dto.getUserDto().getId());
    }

    @Test
    void testMapToDTOList() {
        PhoneNumber phoneNumber1 = new PhoneNumber();
        phoneNumber1.setNumber("123-456-7890");
        // phoneNumber1.setUser(new User());

        PhoneNumber phoneNumber2 = new PhoneNumber();
        phoneNumber2.setNumber("987-654-3210");
        // phoneNumber2.setUser(new User());

        List<PhoneNumber> phoneNumbers = Arrays.asList(phoneNumber1, phoneNumber2);

        List<PhoneNumberDto> dtoList = phoneNumberMapper.mapToListToDto(phoneNumbers);

        assertNotNull(dtoList);
        assertEquals(2, dtoList.size());

        PhoneNumberDto dto1 = dtoList.get(0);
        assertEquals(phoneNumber1.getId(), dto1.getId());
        assertEquals(phoneNumber1.getNumber(), dto1.getNumberDto());
        assertEquals(phoneNumber1.getUser().getId(), dto1.getUserDto().getId());

        PhoneNumberDto dto2 = dtoList.get(1);
        assertEquals(phoneNumber2.getId(), dto2.getId());
        assertEquals(phoneNumber2.getNumber(), dto2.getNumberDto());
        assertEquals(phoneNumber2.getUser().getId(), dto2.getUserDto().getId());
    }
}

