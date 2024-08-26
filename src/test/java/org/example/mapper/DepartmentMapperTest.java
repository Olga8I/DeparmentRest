package org.example.mapper;
import org.example.dto.DepartmentCreateDto;
import org.example.dto.DepartmentResponseDto;
import org.example.model.Department;
import org.example.model.PhoneNumber;
import org.example.model.Role;
import org.example.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Set;

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
        PhoneNumber phoneNumber = new PhoneNumber("877980",new User());
        User user = new User("Иван", "Ivan", new Role(), List.of(phoneNumber), null);
        Department entity = new Department();
        entity.setId(1L);
        entity.setName("Science Department");
        entity.setUserList(Set.of(user));

        DepartmentResponseDto dto = departmentMapper.mapToDto(entity);

        assertNotNull(dto);
        assertEquals(entity.getId(), dto.getId());
        assertEquals(entity.getName(), dto.getName());
    }

}
