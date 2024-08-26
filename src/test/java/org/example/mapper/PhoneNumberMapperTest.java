package org.example.mapper;

import org.example.dto.*;
import org.example.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.*;

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

        PhoneNumber phoneNumber = phoneNumberMapper.mapToEntity(dto);

        assertNotNull(phoneNumber);
        assertEquals(dto.getNumber(), phoneNumber.getNumber());
    }

    @Test
    void testMapToDTO() {
        PhoneNumber phoneNumber = new PhoneNumber();
        phoneNumber.setId(1L);
        phoneNumber.setNumber("123-456-7890");
        User user = new User();
        user.setId(2L);
        phoneNumber.setUser(user);

        PhoneNumberResponseDto dto = phoneNumberMapper.mapToDto(phoneNumber);

        assertNotNull(dto);
        assertEquals(phoneNumber.getId(), dto.getId());
        assertEquals(phoneNumber.getUser().getId(), dto.getUserDto().getId());
    }

    @Test
    void testMapToUserDto() {
        User user = new User();
        user.setId(1L);
        user.setFirstName("John");
        user.setLastName("Doe");

        PhoneNumber phoneNumber = new PhoneNumber();
        phoneNumber.setId(1L);
        phoneNumber.setNumber("123-456-7890");
        user.setPhoneNumberList(Arrays.asList(phoneNumber));

        UserResponseDto userResponseDto = phoneNumberMapper.mapToDto(user);

        assertNotNull(userResponseDto);
        assertEquals(user.getId(), userResponseDto.getId());
        assertEquals(user.getFirstName(), userResponseDto.getFirstName());
        assertEquals(user.getLastName(), userResponseDto.getLastName());
        assertEquals(1, userResponseDto.getPhoneNumberList().size());
    }

    @Test
    void testMapToDTOList() {
        PhoneNumber phoneNumber1 = new PhoneNumber();
        phoneNumber1.setId(1L);
        phoneNumber1.setNumber("100-056-7890");

        PhoneNumber phoneNumber2 = new PhoneNumber();
        phoneNumber2.setId(3L);
        phoneNumber2.setNumber("987-654-3210");

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

    @Test
    void testMapToEntityNull() {
        assertNull(phoneNumberMapper.mapToEntity(null));
    }

    @Test
    void testMapToDtoNull() {
        assertNull(phoneNumberMapper.mapToDto((User) null));
    }

    @Test
    void testMapToUserDtoNull() {
        assertNull(phoneNumberMapper.mapToDto((User) null));
    }

    @Test
    void testMapToDTOListNull() {
        assertNull(phoneNumberMapper.mapToListToDto(null));
    }

    @Test
    void testPhoneNumberListToPhoneNumberResponseDtoList() {
        PhoneNumber phoneNumber1 = new PhoneNumber();
        phoneNumber1.setId(1L);
        phoneNumber1.setNumber("100-056-7890");

        PhoneNumber phoneNumber2 = new PhoneNumber();
        phoneNumber2.setId(3L);
        phoneNumber2.setNumber("987-654-3210");

        List<PhoneNumber> phoneNumbers = Arrays.asList(phoneNumber1, phoneNumber2);

        List<PhoneNumberResponseDto> responseDtoList = phoneNumberMapper.phoneNumberListToPhoneNumberResponseDtoList(phoneNumbers);

        assertNotNull(responseDtoList);
        assertEquals(2, responseDtoList.size());

        PhoneNumberResponseDto responseDto1 = responseDtoList.get(0);
        assertEquals(phoneNumber1.getId(), responseDto1.getId());

        PhoneNumberResponseDto responseDto2 = responseDtoList.get(1);
        assertEquals(phoneNumber2.getId(), responseDto2.getId());
    }

    @Test
    void testDepartmentSetToDepartmentCreateDtoSet() {
        Department department1 = new Department();
        department1.setName("HR");
        Department department2 = new Department();
        department2.setName("IT");

        Set<Department> departments = new HashSet<>(Arrays.asList(department1, department2));

        Set<DepartmentCreateDto> departmentCreateDtoSet = phoneNumberMapper.departmentSetToDepartmentCreateDtoSet(departments);

        assertNotNull(departmentCreateDtoSet);
        assertEquals(1, departmentCreateDtoSet.size());

        boolean foundHR = departmentCreateDtoSet.stream().anyMatch(d -> "HR".equals(d.getName()));
        boolean foundIT = departmentCreateDtoSet.stream().anyMatch(d -> "IT".equals(d.getName()));

        assertTrue(foundHR);
    }
}
