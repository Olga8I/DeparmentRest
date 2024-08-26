package org.example.mapper;

import org.example.dto.*;
import org.example.model.Department;
import org.example.model.PhoneNumber;
import org.example.model.Role;
import org.example.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class UserMapperTest {
    @InjectMocks
    private UserMapper userMapper;

    @Mock
    private RoleMapper roleMapper;
    @Mock
    private PhoneNumberMapper phoneNumberMapper;
    @Mock
    private DepartmentMapper departmentMapper;
    @BeforeEach
    void setUp() {
        userMapper = Mappers.getMapper(UserMapper.class);
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void testMapToEntity_NullDto() {
        User user = userMapper.mapToEntity(null);
        assertNull(user);
    }

    @Test
    void testMapToDTO_NullUser() {
        UserResponseDto dto = userMapper.mapToDto(null);
        assertNull(dto);
    }

    @Test
    void testMapToDTOList_NullUserList() {
        List<UserUpdateDto> dtoList = userMapper.mapToListToDto(null);
        assertNull(dtoList);
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
        UserResponseDto dto = userMapper.mapToDto(user);
        assertNotNull(dto);
        assertEquals(user.getFirstName(), dto.getFirstName());
        assertEquals(user.getLastName(), dto.getLastName());

        assertNotNull(user.getPhoneNumberList());
        assertEquals(1, dto.getPhoneNumberList().size());

        assertNotNull(user.getDepartmentList());
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