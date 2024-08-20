package org.example.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class RoleCreateDtoTest {
    @Test
    void testRoleCreateDtoDefaultConstructor() {
        RoleCreateDto roleCreateDto = new RoleCreateDto();
        assertNull(roleCreateDto.getName());
    }

    @Test
    void testRoleCreateDtoParameterizedConstructor() {
        String roleName = "Admin";
        RoleCreateDto roleCreateDto = new RoleCreateDto(roleName);
        assertEquals(roleName, roleCreateDto.getName());
    }

    @Test
    void testRoleCreateDtoSettersAndGetters() {
        RoleCreateDto roleCreateDto = new RoleCreateDto();
        roleCreateDto.setName("User");
        assertEquals("User", roleCreateDto.getName());
    }

}
