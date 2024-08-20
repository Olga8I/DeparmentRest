package org.example.mapper;

import org.example.dto.DepartmentDto;
import org.example.dto.PhoneNumberDto;
import org.example.dto.RoleDto;
import org.example.dto.UserDto;
import org.example.mapper.UserMapper;
import org.example.model.Department;
import org.example.model.PhoneNumber;
import org.example.model.User;
import org.example.model.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

class UserMapperTest {

    private UserMapper userMapper;

    @BeforeEach
    void setUp() {
        userMapper = Mappers.getMapper(UserMapper.class);
    }

    @Test
    void testMapToEntity() {
        UserDto dto = new UserDto();
        dto.setFirstName("John");
        dto.setLastName("Doe");
        dto.setRoleDto(new RoleDto("devoloper"));
        dto.setPhoneNumberList(Arrays.asList(new PhoneNumberDto("1234567890", null)));
        Set<DepartmentDto> departmentDtos = new HashSet<>();
        departmentDtos.add(new DepartmentDto("HR", null));
        dto.setDepartmentList(departmentDtos); // Инициализация DepartmentDto

        User user = userMapper.mapToEntity(dto);

        assertNotNull(user);
        assertEquals(dto.getFirstName(), user.getFirstName());
        assertEquals(dto.getLastName(), user.getLastName());
        assertNotNull(user.getRole());
        assertEquals(dto.getRoleDto().getId(), user.getRole().getId());
        assertEquals(dto.getRoleDto().getName(), user.getRole().getName());

        assertNotNull(user.getPhoneNumberList());
        assertEquals(dto.getPhoneNumberList().size(), user.getPhoneNumberList().size());
        assertEquals(dto.getPhoneNumberList().get(0).getNumberDto(), user.getPhoneNumberList().get(0).getNumber());

        assertNotNull(user.getDepartmentList());
        assertEquals(dto.getDepartmentList().size(), user.getDepartmentList().size());
        assertEquals(dto.getDepartmentList().iterator().next().getName(),
                user.getDepartmentList().iterator().next().getName());
    }

    @Test
    void testMapToDTO() {
        User user = new User();
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setRole(new Role("ADMIN", null)); // Инициализация объекта Role
        user.setPhoneNumberList(Arrays.asList(new PhoneNumber("1234567890",user)));
        user.setDepartmentList(new HashSet<>(Arrays.asList(new Department( "HR")))); // Инициализация Department

        UserDto dto = userMapper.mapToDto(user);

        assertNotNull(dto);
        assertEquals(user.getFirstName(), dto.getFirstName());
        assertEquals(user.getLastName(), dto.getLastName());
        assertNotNull(dto.getRoleDto());
        assertEquals(user.getRole().getId(), dto.getRoleDto().getId());
        assertEquals(user.getRole().getName(), dto.getRoleDto().getName());

        assertNotNull(dto.getPhoneNumberList());
        assertEquals(user.getPhoneNumberList().size(), dto.getPhoneNumberList().size());
        assertEquals(user.getPhoneNumberList().get(0).getNumber(), dto.getPhoneNumberList().get(0).getNumberDto());

        assertNotNull(dto.getDepartmentList());
        assertEquals(user.getDepartmentList().size(), dto.getDepartmentList().size());
        assertEquals(user.getDepartmentList().iterator().next().getName(),
                dto.getDepartmentList().iterator().next().getName());
    }

    @Test
    void testMapToDTOList() {
        User user1 = new User();
        user1.setFirstName("John");
        user1.setLastName("Doe");

        User user2 = new User();
        user2.setFirstName("Jane");
        user2.setLastName("Smith");

        List<User> users = Arrays.asList(user1, user2);

        List<UserDto> dtoList = userMapper.mapToListToDto(users);

        assertNotNull(dtoList);
        assertEquals(2, dtoList.size());

        UserDto dto1 = dtoList.get(0);
        assertEquals(user1.getFirstName(), dto1.getFirstName());
        assertEquals(user1.getLastName(), dto1.getLastName());

        UserDto dto2 = dtoList.get(1);
        assertEquals(user2.getFirstName(), dto2.getFirstName());
        assertEquals(user2.getLastName(), dto2.getLastName());
    }
}
