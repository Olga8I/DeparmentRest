package org.example.mapper;

import org.example.dto.DepartmentCreateDto;
import org.example.dto.DepartmentResponseDto;
import org.example.dto.UserResponseDto;
import org.example.model.Department;
import org.example.model.PhoneNumber;
import org.example.model.Role;
import org.example.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Set;
import java.util.LinkedHashSet;

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
        PhoneNumber phoneNumber = new PhoneNumber("877980", new User());
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

    @Test
    void testDepartmentToDepartmentCreateDto() {
        Department department = new Department();
        department.setName("Science Department");

        DepartmentCreateDto dto = departmentMapper.departmentToDepartmentCreateDto(department);

        assertNotNull(dto);
        assertEquals(department.getName(), dto.getName());
    }

    @Test
    void testDepartmentToDepartmentCreateDtoWithNull() {
        DepartmentCreateDto dto = departmentMapper.departmentToDepartmentCreateDto(null);
        assertNull(dto);
    }

    @Test
    void testDepartmentSetToDepartmentCreateDtoSet() {
        Department department1 = new Department();
        department1.setName("Science Department");

        Department department2 = new Department();
        department2.setName("Mathematics Department");

        Set<Department> departments = new LinkedHashSet<>();
        departments.add(department1);
        departments.add(department2);

        Set<DepartmentCreateDto> dtos = departmentMapper.departmentSetToDepartmentCreateDtoSet(departments);

        assertNotNull(dtos);
        assertEquals(1, dtos.size());
        assertTrue(dtos.stream().anyMatch(dto -> dto.getName().equals("Science Department")));
    }

    @Test
    void testDepartmentSetToDepartmentCreateDtoSetWithNull() {
        Set<DepartmentCreateDto> dtos = departmentMapper.departmentSetToDepartmentCreateDtoSet(null);
        assertNull(dtos);
    }

    @Test
    void testUserToUserResponseDto() {
        PhoneNumber phoneNumber = new PhoneNumber("877980", new User());
        User user = new User("Иван", "Ivan", new Role(), List.of(phoneNumber), null);

        UserResponseDto userResponseDto = departmentMapper.userToUserResponseDto(user);

        assertNotNull(userResponseDto);
        assertEquals(user.getFirstName(), userResponseDto.getFirstName());
        assertEquals(user.getLastName(), userResponseDto.getLastName());
    }

    @Test
    void testUserToUserResponseDtoWithNull() {
        UserResponseDto userResponseDto = departmentMapper.userToUserResponseDto(null);
        assertNull(userResponseDto);
    }
}
