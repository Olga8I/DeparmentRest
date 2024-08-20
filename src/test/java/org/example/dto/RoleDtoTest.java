package org.example.dto;

import org.example.dto.RoleDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoleDtoTest {

    @Test
    void testDefaultConstructor() {
        RoleDto roleDto = new RoleDto();
        assertNull(roleDto.getId());
        assertNull(roleDto.getName());
    }

    @Test
    void testParameterizedConstructor() {
        String roleName = "Admin";
        RoleDto roleDto = new RoleDto(roleName);
        assertNull(roleDto.getId());
        assertEquals(roleName, roleDto.getName());
    }

    @Test
    void testSettersAndGetters() {
        RoleDto roleDto = new RoleDto();
        roleDto.setId(1L);
        roleDto.setName("User");

        assertEquals(1L, roleDto.getId());
        assertEquals("User", roleDto.getName());
    }

    @Test
    void testEquals() {
        RoleDto roleDto1 = new RoleDto();
        roleDto1.setId(1L);
        roleDto1.setName("User");

        RoleDto roleDto2 = new RoleDto();
        roleDto2.setId(1L);
        roleDto2.setName("User");

        assertEquals(roleDto1, roleDto2);
    }

    @Test
    void testEqualsDifferentIds() {
        RoleDto roleDto1 = new RoleDto();
        roleDto1.setId(1L);
        roleDto1.setName("User");

        RoleDto roleDto2 = new RoleDto();
        roleDto2.setId(2L);
        roleDto2.setName("User");

        assertNotEquals(roleDto1, roleDto2);
    }

    @Test
    void testHashCode() {
        RoleDto roleDto1 = new RoleDto();
        roleDto1.setId(1L);
        roleDto1.setName("User");

        RoleDto roleDto2 = new RoleDto();
        roleDto2.setId(1L);
        roleDto2.setName("User");

        assertEquals(roleDto1.hashCode(), roleDto2.hashCode());
    }

    @Test
    void testToString() {
        RoleDto roleDto = new RoleDto();
        roleDto.setId(1L);
        roleDto.setName("Admin");

        String expectedString = "RoleDto{id=1, name='Admin'}";
        assertEquals(expectedString, roleDto.toString());
    }
}
