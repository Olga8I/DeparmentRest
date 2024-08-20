package org.example.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class DepartmentCreateDtoTest {

    @Test
    void testDefaultConstructor() {
        DepartmentCreateDto departmentCreateDto = new DepartmentCreateDto();
        assertNull(departmentCreateDto.getName());
    }

    @Test
    void testParameterizedConstructor() {
        DepartmentCreateDto departmentCreateDto = new DepartmentCreateDto("IT");

        assertEquals("IT", departmentCreateDto.getName());
    }

    @Test
    void testSettersAndGetters() {
        DepartmentCreateDto departmentCreateDto = new DepartmentCreateDto();
        departmentCreateDto.setName("HR");

        assertEquals("HR", departmentCreateDto.getName());
    }
}