package org.example.dto;

import org.example.dto.DepartmentDto;
import org.example.dto.PhoneNumberDto;
import org.example.dto.RoleDto;
import org.example.dto.UserDto;
import org.example.model.Role;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class UserDtoTest {

    @Test
    void testUserDtoConstructorAndGetters() {
        RoleDto roleDto = new RoleDto();
        List<PhoneNumberDto> phoneNumbers = new ArrayList<>();
        Set<DepartmentDto> departments = new HashSet<>();

        UserDto userDto = new UserDto("John", "Doe", roleDto, phoneNumbers, departments);
        userDto.setId(1L);

        assertEquals(1L, userDto.getId());
        assertEquals("John", userDto.getFirstName());
        assertEquals("Doe", userDto.getLastName());
        assertEquals(roleDto, userDto.getRoleDto());
        assertEquals(phoneNumbers, userDto.getPhoneNumberList());
        assertEquals(departments, userDto.getDepartmentList());
    }

    @Test
    void testSetters() {
        UserDto userDto = new UserDto();
        RoleDto roleDto = new RoleDto();
        List<PhoneNumberDto> phoneNumbers = new ArrayList<>();
        Set<DepartmentDto> departments = new HashSet<>();

        userDto.setId(1L);
        userDto.setFirstName("Jane");
        userDto.setLastName("Smith");
        userDto.setRoleDto(roleDto);
        userDto.setPhoneNumberList(phoneNumbers);
        userDto.setDepartmentList(departments);

        assertEquals(1L, userDto.getId());
        assertEquals("Jane", userDto.getFirstName());
        assertEquals("Smith", userDto.getLastName());
        assertEquals(roleDto, userDto.getRoleDto());
        assertEquals(phoneNumbers, userDto.getPhoneNumberList());
        assertEquals(departments, userDto.getDepartmentList());
    }

    @Test
    void testEquals() {
        UserDto userDto1 = new UserDto("John", "Doe", new RoleDto(), new ArrayList<>(), new HashSet<>());
        userDto1.setId(1L);

        UserDto userDto2 = new UserDto("John", "Doe", new RoleDto(), new ArrayList<>(), new HashSet<>());
        userDto2.setId(1L);

        assertEquals(userDto1, userDto2);
        assertNotEquals(userDto1, new UserDto("Jane", "Doe", new RoleDto(), new ArrayList<>(), new HashSet<>()));
    }

    @Test
    void testHashCode() {
        UserDto userDto1 = new UserDto("John", "Doe", new RoleDto(), new ArrayList<>(), new HashSet<>());
        userDto1.setId(1L);

        UserDto userDto2 = new UserDto("John", "Doe", new RoleDto(), new ArrayList<>(), new HashSet<>());
        userDto2.setId(1L);

        assertEquals(userDto1.hashCode(), userDto2.hashCode());
    }

    @Test
    void testToString() {
        RoleDto roleDto = new RoleDto();
        List<PhoneNumberDto> phoneNumbers = new ArrayList<>();
        Set<DepartmentDto> departments = new HashSet<>();

        UserDto userDto = new UserDto("John", "Doe", roleDto, phoneNumbers, departments);
        userDto.setId(1L);

        String expected = "UserDto{id=1, firstName='John', lastName='Doe', role=" + roleDto + ", phoneNumberList=" + phoneNumbers + ", departmentList=" + departments + "}";
        assertEquals(expected, userDto.toString());
    }
}

