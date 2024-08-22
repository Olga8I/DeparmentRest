package org.example.dto;

import org.example.dto.DepartmentDto;
import org.example.dto.UserDto;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class DepartmentDtoTest {

    @Test
    void testDefaultConstructor() {//Проверяем, что при использовании конструктора по умолчанию значения id и name равны null.
        DepartmentDto departmentDto = new DepartmentDto();
        assertNull(departmentDto.getId());
        assertNull(departmentDto.getName());
        assertNull(departmentDto.getUserList());
    }

    @Test
    void testParameterizedConstructor() {//Проверяем, что конструктор с параметром инициализирует поле name и оставляет id равным null.
        UserDto user1 = new UserDto();
        UserDto user2 = new UserDto();
        DepartmentDto departmentDto = new DepartmentDto("IT", Arrays.asList(user1, user2));

        assertEquals("IT", departmentDto.getName());
        assertEquals(2, departmentDto.getUserList().size());
    }

    @Test
    void testSettersAndGetters() {//Проверяем, что с помощью сеттеров можно установить значения полей и они возвращаются корректно через геттеры.
        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setId(1L);
        departmentDto.setName("HR");

        UserDto user1 = new UserDto();
        departmentDto.setUserList(Collections.singletonList(user1));

        assertEquals(1L, departmentDto.getId());
        assertEquals("HR", departmentDto.getName());
        assertEquals(1, departmentDto.getUserList().size());
    }

    @Test
    void testEquals() {
        DepartmentDto departmentDto1 = new DepartmentDto();
        departmentDto1.setId(1L);
        departmentDto1.setName("Finance");

        DepartmentDto departmentDto2 = new DepartmentDto();
        departmentDto2.setId(1L);
        departmentDto2.setName("Finance");

        assertEquals(departmentDto1, departmentDto2);
    }

    @Test
    void testNotEquals() {
        DepartmentDto departmentDto1 = new DepartmentDto();
        departmentDto1.setId(1L);
        departmentDto1.setName("Finance");

        DepartmentDto departmentDto2 = new DepartmentDto();
        departmentDto2.setId(2L);
        departmentDto2.setName("IT");

        assertNotEquals(departmentDto1, departmentDto2);
    }

    @Test
    void testHashCode() {
        DepartmentDto departmentDto1 = new DepartmentDto();
        departmentDto1.setId(1L);
        departmentDto1.setName("Finance");

        DepartmentDto departmentDto2 = new DepartmentDto();
        departmentDto2.setId(1L);
        departmentDto2.setName("Finance");

        assertEquals(departmentDto1.hashCode(), departmentDto2.hashCode());
    }

    @Test
    void testToString() {
        DepartmentDto departmentDto = new DepartmentDto("Marketing", Collections.emptyList());
        String expectedString = "DepartmentDto{id=null, name='Marketing', userList=[]}";

        assertEquals(expectedString, departmentDto.toString());
    }
}
