package org.example.mapper;
import org.example.dto.RoleCreateDto;
import org.example.dto.RoleResponseDto;
import org.example.dto.RoleUpdateDto;
import org.example.model.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RoleMapperTest {

    private RoleMapper roleMapper;

    @BeforeEach
    void setUp() {
        roleMapper = Mappers.getMapper(RoleMapper.class);
    }

    @Test
    void testMapToEntity() {
        RoleCreateDto dto = new RoleCreateDto();
        dto.setName("Admin");

        Role role = roleMapper.mapToEntity(dto);

        assertNotNull(role);
        assertEquals(dto.getName(), role.getName());
    }

    @Test
    void testMapToEntity_null() {
        Role role = roleMapper.mapToEntity(null);
        assertNull(role);
    }

    @Test
    void testMapToDTO() {
        Role role = new Role();
        role.setId(1L);
        role.setName("Admin");

        RoleResponseDto dto = roleMapper.mapToDto(role);

        assertNotNull(dto);
        assertEquals(role.getId(), dto.getId());
        assertEquals(role.getName(), dto.getName());
    }

    @Test
    void testMapToDTO_null() {
        RoleResponseDto dto = roleMapper.mapToDto(null);
        assertNull(dto);
    }

    @Test
    void testMapToDTOList() {
        Role role1 = new Role();
        role1.setId(1L);
        role1.setName("Admin");

        Role role2 = new Role();
        role2.setId(2L);
        role2.setName("User");

        List<Role> roles = Arrays.asList(role1, role2);

        List<RoleUpdateDto> dtoList = roleMapper.mapToListToDto(roles);

        assertNotNull(dtoList);
        assertEquals(2, dtoList.size());

        RoleUpdateDto dto1 = dtoList.get(0);
        assertEquals(role1.getId(), dto1.getId());
        assertEquals(role1.getName(), dto1.getName());

        RoleUpdateDto dto2 = dtoList.get(1);
        assertEquals(role2.getId(), dto2.getId());
        assertEquals(role2.getName(), dto2.getName());
    }

    @Test
    void testMapToDTOList_null() {
        List<RoleUpdateDto> dtoList = roleMapper.mapToListToDto(null);
        assertNull(dtoList);
    }

    @Test
    void testMapToDTOList_empty() {
        List<RoleUpdateDto> dtoList = roleMapper.mapToListToDto(Collections.emptyList());
        assertNotNull(dtoList);
        assertTrue(dtoList.isEmpty());
    }
}
