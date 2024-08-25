package org.example.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class RoleResponseDtoTest {
    @Test
    void testRoleResponseDtoDefaultConstructor() {
        RoleResponseDto roleResponseDto = new RoleResponseDto();
        assertNull(roleResponseDto.getId());
        assertNull(roleResponseDto.getName());
    }

    @Test
    void testRoleResponseDtoParameterizedConstructor() {
        String roleName = "Admin";
        RoleResponseDto roleResponseDto = new RoleResponseDto(roleName);
        assertNull(roleResponseDto.getId());
        assertEquals(roleName, roleResponseDto.getName());
    }

    @Test
    void testRoleResponseDtoSettersAndGetters() {
        RoleResponseDto roleResponseDto = new RoleResponseDto();
        roleResponseDto.setId(1L);
        roleResponseDto.setName("User");

        assertEquals(1L, roleResponseDto.getId());
        assertEquals("User", roleResponseDto.getName());
    }

    @Test
    void testRoleResponseDtoEquals() {
        RoleResponseDto roleResponseDto1 = new RoleResponseDto();
        roleResponseDto1.setId(1L);
        roleResponseDto1.setName("User");

        RoleResponseDto roleResponseDto2 = new RoleResponseDto();
        roleResponseDto2.setId(1L);
        roleResponseDto2.setName("User");

        assertEquals(roleResponseDto1, roleResponseDto2);
    }

    @Test
    void testRoleResponseDtoEqualsDifferentIds() {
        RoleResponseDto roleResponseDto1 = new RoleResponseDto();
        roleResponseDto1.setId(1L);
        roleResponseDto1.setName("User");

        RoleResponseDto roleResponseDto2 = new RoleResponseDto();
        roleResponseDto2.setId(2L);
        roleResponseDto2.setName("User");

        assertNotEquals(roleResponseDto1, roleResponseDto2);
    }

    @Test
    void testRoleResponseDtoHashCode() {
        RoleResponseDto roleResponseDto1 = new RoleResponseDto();
        roleResponseDto1.setId(1L);
        roleResponseDto1.setName("User");

        RoleResponseDto roleResponseDto2 = new RoleResponseDto();
        roleResponseDto2.setId(1L);
        roleResponseDto2.setName("User");

        assertEquals(roleResponseDto1.hashCode(), roleResponseDto2.hashCode());
    }

    @Test
    void testRoleResponseDtoToString() {
        RoleResponseDto roleResponseDto = new RoleResponseDto();
        roleResponseDto.setId(1L);
        roleResponseDto.setName("Admin");

        String expectedString = "RoleResponseDto{id=1, name='Admin'}";
        assertEquals(expectedString, roleResponseDto.toString());
    }
}
