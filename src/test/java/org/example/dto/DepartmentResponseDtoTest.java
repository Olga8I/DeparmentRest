package org.example.dto;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class DepartmentResponseDtoTest {

    @Test
    void testDefaultConstructor() {
        DepartmentResponseDto departmentResponseDto = new DepartmentResponseDto();
        assertNull(departmentResponseDto.getId());
        assertNull(departmentResponseDto.getName());
        assertNull(departmentResponseDto.getUserList());
    }

    @Test
    void testParameterizedConstructor() {
        UserResponseDto user1 = new UserResponseDto();
        UserResponseDto user2 = new UserResponseDto();
        DepartmentResponseDto departmentResponseDto = new DepartmentResponseDto(1l,"IT", Arrays.asList(user1, user2));

        assertEquals("IT", departmentResponseDto.getName());
        assertEquals(2, departmentResponseDto.getUserList().size());
    }

    @Test
    void testSettersAndGetters() {
        DepartmentResponseDto departmentResponseDto = new DepartmentResponseDto();
        departmentResponseDto.setId(1L);
        departmentResponseDto.setName("HR");

        UserResponseDto user1 = new UserResponseDto();
        departmentResponseDto.setUserList(Collections.singletonList(user1));

        assertEquals(1L, departmentResponseDto.getId());
        assertEquals("HR", departmentResponseDto.getName());
        assertEquals(1, departmentResponseDto.getUserList().size());
    }
}

