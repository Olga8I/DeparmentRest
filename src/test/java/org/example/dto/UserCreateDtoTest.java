package org.example.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class UserCreateDtoTest {

    @Test
    void testDefaultConstructor() {
        UserCreateDto userCreateDto = new UserCreateDto();
        assertNull(userCreateDto.getFirstName());
        assertNull(userCreateDto.getLastName());
        assertNull(userCreateDto.getRoleDto());
    }

    @Test
    void testParameterizedConstructor() {
        String firstName = "Jane";
        String lastName = "Smith";
        RoleResponseDto role = new RoleResponseDto("User");

        UserCreateDto userCreateDto = new UserCreateDto(firstName, lastName, role);

        assertEquals(firstName, userCreateDto.getFirstName());
        assertEquals(lastName, userCreateDto.getLastName());
        assertEquals(role, userCreateDto.getRoleDto());
    }

    @Test
    void testSettersAndGetters() {
        UserCreateDto userCreateDto = new UserCreateDto();
        userCreateDto.setFirstName("Alice");
        userCreateDto.setLastName("Brown");

        RoleResponseDto role = new RoleResponseDto("Admin");
        userCreateDto.setRoleDto(role);

        assertEquals("Alice", userCreateDto.getFirstName());
        assertEquals("Brown", userCreateDto.getLastName());
        assertEquals(role, userCreateDto.getRoleDto());
    }
}

