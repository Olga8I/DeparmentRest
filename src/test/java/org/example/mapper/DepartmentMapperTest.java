package org.example.mapper;
import org.example.dto.DepartmentCreateDto;
import org.example.dto.DepartmentResponseDto;
import org.example.model.Department;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.*;

class DepartmentMapperTest {

    private DepartmentMapper departmentMapper;

    @BeforeEach
    void setUp() {
        departmentMapper = Mappers.getMapper(DepartmentMapper.class);
    }

    @Test
    void testMapToEntity() {
        DepartmentCreateDto dto = new DepartmentCreateDto();
        dto.setName("Science Department");

        Department department = departmentMapper.mapToEntity(dto);

        assertNotNull(department);
        assertNull(department.getId());
        assertEquals(dto.getName(), department.getName());
    }

    @Test
    void testMapToDto() {
        Department entity = new Department();
        entity.setId(1L);
        entity.setName("Science Department");

        DepartmentResponseDto dto = departmentMapper.mapToDto(entity);

        assertNotNull(dto);
        assertEquals(entity.getId(), dto.getId());
        assertEquals(entity.getName(), dto.getName());
    }

}
