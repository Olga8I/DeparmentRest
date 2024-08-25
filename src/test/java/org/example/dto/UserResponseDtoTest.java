package org.example.dto;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class UserResponseDtoTest {

    @Test
    void testDefaultConstructor() {
        UserResponseDto userResponseDto = new UserResponseDto();
        assertNull(userResponseDto.getId());
        assertNull(userResponseDto.getFirstName());
        assertNull(userResponseDto.getLastName());
        assertNull(userResponseDto.getRoleDto());
        assertNotNull(userResponseDto.getPhoneNumberList()); // Проверяем, что список не null
        assertTrue(userResponseDto.getPhoneNumberList().isEmpty());
        assertNotNull(userResponseDto.getDepartmentList()); // Проверяем, что множество не null
        assertTrue(userResponseDto.getDepartmentList().isEmpty());
    }

    @Test
    void testParameterizedConstructor() {
        String firstName = "Миша";
        String lastName = "Мишутин";
        RoleResponseDto role = new RoleResponseDto("Admin");

        UserResponseDto userResponseDto = new UserResponseDto(firstName, lastName, role,
                Collections.emptyList(), Collections.emptySet());

        assertNull(userResponseDto.getId());
        assertEquals(firstName, userResponseDto.getFirstName());
        assertEquals(lastName, userResponseDto.getLastName());
        assertEquals(role, userResponseDto.getRoleDto());
    }

    @Test
    void testSettersAndGetters() {
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(1L);
        userResponseDto.setFirstName("Петр");
        userResponseDto.setLastName("Петров");

        RoleResponseDto role = new RoleResponseDto("Admin");
        userResponseDto.setRoleDto(role);

        assertEquals(1L, userResponseDto.getId());
        assertEquals("Петр", userResponseDto.getFirstName());
        assertEquals("Петров", userResponseDto.getLastName());
        assertEquals(role, userResponseDto.getRoleDto());
    }
}
