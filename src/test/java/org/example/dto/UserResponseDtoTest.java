package org.example.dto;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class UserResponseDtoTest {

    @Test
    void testDefaultConstructor() {
        UserResponseDto userResponseDto = new UserResponseDto();
        assertNull(userResponseDto.getId());
        assertNull(userResponseDto.getFirstName());
        assertNull(userResponseDto.getLastName());
        assertNull(userResponseDto.getRoleDto());
        assertNotNull(userResponseDto.getPhoneNumberList());
        assertTrue(userResponseDto.getPhoneNumberList().isEmpty());
        assertNotNull(userResponseDto.getDepartmentList());
        assertTrue(userResponseDto.getDepartmentList().isEmpty());
    }

    @Test
    void testParameterizedConstructor() {
        String firstName = "Миша";
        String lastName = "Мишутин";
        RoleResponseDto role = new RoleResponseDto("Admin");

        UserResponseDto userResponseDto = new UserResponseDto(firstName, lastName, role,
                Collections.emptyList(), Collections.emptySet());

        assertNull(userResponseDto.getId());
        assertEquals(firstName, userResponseDto.getFirstName());
        assertEquals(lastName, userResponseDto.getLastName());
        assertEquals(role, userResponseDto.getRoleDto());
        assertTrue(userResponseDto.getPhoneNumberList().isEmpty());
        assertTrue(userResponseDto.getDepartmentList().isEmpty());
    }

    @Test
    void testSettersAndGetters() {
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(1L);
        userResponseDto.setFirstName("Петр");
        userResponseDto.setLastName("Петров");

        RoleResponseDto role = new RoleResponseDto("Admin");
        userResponseDto.setRoleDto(role);

        List<PhoneNumberResponseDto> phoneList = Arrays.asList(new PhoneNumberResponseDto());
        Set<DepartmentCreateDto> departmentSet = new HashSet<>(Collections.singletonList(new DepartmentCreateDto("IT")));

        userResponseDto.setPhoneNumberList(phoneList);
        userResponseDto.setDepartmentList(departmentSet);

        assertEquals(1L, userResponseDto.getId());
        assertEquals("Петр", userResponseDto.getFirstName());
        assertEquals("Петров", userResponseDto.getLastName());
        assertEquals(role, userResponseDto.getRoleDto());
        assertEquals(phoneList, userResponseDto.getPhoneNumberList());
        assertEquals(departmentSet, userResponseDto.getDepartmentList());
    }

    @Test
    void testEqualsAndHashCode() {
        RoleResponseDto role1 = new RoleResponseDto("Admin");
        RoleResponseDto role2 = new RoleResponseDto("User");

        UserResponseDto user1 = new UserResponseDto("Иван", "Иванов", role1, Collections.emptyList(), Collections.emptySet());
        UserResponseDto user2 = new UserResponseDto("Иван", "Иванов", role1, Collections.emptyList(), Collections.emptySet());
        UserResponseDto user3 = new UserResponseDto("Петр", "Петров", role2, Collections.emptyList(), Collections.emptySet());
        UserResponseDto user4 = new UserResponseDto("Иван", null, role1, Collections.emptyList(), Collections.emptySet());
        UserResponseDto user5 = new UserResponseDto(null, "Иванов", role1, Collections.emptyList(), Collections.emptySet());

        assertEquals(user1, user2);
        assertEquals(user1.hashCode(), user2.hashCode());

        assertNotEquals(user1, user3);

        assertNotEquals(user1, null);

        assertNotEquals(user1, new Object());

        user1 = new UserResponseDto("Иван", "Иванов", role1, Collections.emptyList(), Collections.emptySet());
        user3 = new UserResponseDto("Петр", "Иванов", role1, Collections.emptyList(), Collections.emptySet());
        assertNotEquals(user1, user3);

        user1 = new UserResponseDto("Иван", "Иванов", role1, Collections.emptyList(), Collections.emptySet());
        user3 = new UserResponseDto("Иван", "Петров", role1, Collections.emptyList(), Collections.emptySet());
        assertNotEquals(user1, user3);

        user1 = new UserResponseDto("Иван", "Иванов", role1, Collections.emptyList(), Collections.emptySet());
        user3 = new UserResponseDto("Петр", "Петров", role2, Collections.emptyList(), Collections.emptySet());
        assertNotEquals(user1, user3);

        user4 = new UserResponseDto("Иван", null, role1, Collections.emptyList(), Collections.emptySet());
        user5 = new UserResponseDto(null, "Иванов", role1, Collections.emptyList(), Collections.emptySet());
        UserResponseDto user6 = new UserResponseDto(null, null, role1, Collections.emptyList(), Collections.emptySet());

        assertNotEquals(user4, user5);
        assertNotEquals(user4, user6);
        assertNotEquals(user5, user6);
    }

}
