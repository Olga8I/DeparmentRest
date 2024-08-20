package org.example.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class RoleUpdateDtoTest {
    @Test
    void testRoleUpdateDtoDefaultConstructor() {
        RoleUpdateDto roleUpdateDto = new RoleUpdateDto();
        assertNull(roleUpdateDto.getId());
        assertNull(roleUpdateDto.getName());
    }

    @Test
    void testRoleUpdateDtoParameterizedConstructor() {
        RoleUpdateDto roleUpdateDto = new RoleUpdateDto(1L, "Admin");
        assertEquals(1L, roleUpdateDto.getId());
        assertEquals("Admin", roleUpdateDto.getName());
    }

    @Test
    void testRoleUpdateDtoSettersAndGetters() {
        RoleUpdateDto roleUpdateDto = new RoleUpdateDto();
        roleUpdateDto.setId(1L);
        roleUpdateDto.setName("User");

        assertEquals(1L, roleUpdateDto.getId());
        assertEquals("User", roleUpdateDto.getName());
    }
}
