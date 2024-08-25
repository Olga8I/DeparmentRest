package org.example.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class UserUpdateDtoTest {

    @Test
    void testDefaultConstructor() {
        UserUpdateDto userUpdateDto = new UserUpdateDto();
        assertNull(userUpdateDto.getId());
        assertNull(userUpdateDto.getFirstName());
        assertNull(userUpdateDto.getLastName());
        assertNull(userUpdateDto.getRoleDto());
    }

    @Test
    void testParameterizedConstructor() {
        String firstName = "Tom";
        String lastName = "Hanks";
        RoleResponseDto role = new RoleResponseDto("Manager");
        Long id = 2L;

        UserUpdateDto userUpdateDto = new UserUpdateDto(firstName, lastName, role, id);

        assertEquals(id, userUpdateDto.getId());
        assertEquals(firstName, userUpdateDto.getFirstName());
        assertEquals(lastName, userUpdateDto.getLastName());
        assertEquals(role, userUpdateDto.getRoleDto());
    }

    @Test
    void testSettersAndGetters() {
        UserUpdateDto userUpdateDto = new UserUpdateDto();
        userUpdateDto.setId(3L);
        userUpdateDto.setFirstName("Charlie");
        userUpdateDto.setLastName("Brown");

        RoleResponseDto role = new RoleResponseDto("Editor");
        userUpdateDto.setRoleDto(role);

        assertEquals(3L, userUpdateDto.getId());
        assertEquals("Charlie", userUpdateDto.getFirstName());
        assertEquals("Brown", userUpdateDto.getLastName());
        assertEquals(role, userUpdateDto.getRoleDto());
    }
}
