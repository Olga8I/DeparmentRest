package org.example.mapper;

import org.example.dto.*;
import org.example.model.Department;
import org.example.model.PhoneNumber;
import org.example.model.Role;
import org.example.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class UserMapperTest {

    private UserMapper userMapper;
    private RoleMapper roleMapper;

    @BeforeEach
    void setUp() {
        userMapper = Mappers.getMapper(UserMapper.class);
        roleMapper = Mappers.getMapper(RoleMapper.class);

    }

    @Test
    void testMapToEntity() {
        UserCreateDto dto = new UserCreateDto();
        dto.setFirstName("John");
        dto.setLastName("Doe");
        dto.setRoleDto(new RoleResponseDto("Developer"));
        User user = userMapper.mapToEntity(dto);

        assertNotNull(user);
        assertEquals(dto.getFirstName(), user.getFirstName());
        assertEquals(dto.getLastName(), user.getLastName());
    }

    @Test
    void testMapToDTO() {
        User user = new User();
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setRole(new Role("ADMIN", null));
        user.setPhoneNumberList(Arrays.asList(new PhoneNumber("1234567890", user)));
        user.setDepartmentList(new HashSet<>(Arrays.asList(new Department("HR"))));

        assertNotNull(user);
        assertEquals(user.getFirstName(), user.getFirstName());
        assertEquals(user.getLastName(), user.getLastName());

        assertNotNull(user.getPhoneNumberList());
        assertEquals(1, user.getPhoneNumberList().size());
        assertEquals("1234567890", user.getPhoneNumberList().get(0).getNumber());

        assertNotNull(user.getDepartmentList());
        assertEquals(1, user.getDepartmentList().size());
        assertEquals("HR", user.getDepartmentList().iterator().next().getName());
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

        List<UserUpdateDto> dtoList = userMapper.mapToListToDto(users);

        assertNotNull(dtoList);
        assertEquals(2, dtoList.size());

        UserUpdateDto dto1 = dtoList.get(0);
        assertEquals(user1.getFirstName(), dto1.getFirstName());
        assertEquals(user1.getLastName(), dto1.getLastName());

        UserUpdateDto dto2 = dtoList.get(1);
        assertEquals(user2.getFirstName(), dto2.getFirstName());
        assertEquals(user2.getLastName(), dto2.getLastName());
    }
}
