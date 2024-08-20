package org.example.mapper;

import org.example.dto.DepartmentDto;
import org.example.mapper.DepartmentMapper;
import org.example.model.Department;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DepartmentMapperTest {

    private DepartmentMapper departmentMapper;

    @BeforeEach
    void setUp() {
        departmentMapper = Mappers.getMapper(DepartmentMapper.class);
    }

    @Test
    void testMapToEntity() {
        DepartmentDto dto = new DepartmentDto();
        dto.setName("Science Department");

        Department department = departmentMapper.mapToEntity(dto);

        assertNotNull(department);
        assertEquals(dto.getId(), department.getId());
        assertEquals(dto.getName(), department.getName());
    }

    @Test
    void testMapToDTO() {
        Department entity = new Department();
        entity.setName("Science Department");

        DepartmentDto dto = departmentMapper.mapToDto(entity);

        assertNotNull(dto);
        assertEquals(entity.getId(), dto.getId());
        assertEquals(entity.getName(), dto.getName());
    }

    @Test
    void testMapToDTOList() {
        Department department1 = new Department();
        department1.setName("Science Department");

        Department department2 = new Department();
        department2.setName("Arts Department");

        List<Department> departments = Arrays.asList(department1, department2);

        List<DepartmentDto> dtoList = departmentMapper.mapToListToDto(departments);

        assertNotNull(dtoList);
        assertEquals(2, dtoList.size());

        DepartmentDto dto1 = dtoList.get(0);
        assertEquals(department1.getId(), dto1.getId());
        assertEquals(department1.getName(), dto1.getName());

        DepartmentDto dto2 = dtoList.get(1);
        assertEquals(department2.getId(), dto2.getId());
        assertEquals(department2.getName(), dto2.getName());
    }
}
