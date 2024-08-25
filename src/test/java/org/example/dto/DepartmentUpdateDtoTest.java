package org.example.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DepartmentUpdateDtoTest {

    @Test
    void testDefaultConstructor() {
        DepartmentUpdateDto departmentUpdateDto = new DepartmentUpdateDto();
        assertNull(departmentUpdateDto.getId());
        assertNull(departmentUpdateDto.getName());
    }

    @Test
    void testParameterizedConstructor() {
        DepartmentUpdateDto departmentUpdateDto = new DepartmentUpdateDto(1L, "IT");

        assertEquals(1L, departmentUpdateDto.getId());
        assertEquals("IT", departmentUpdateDto.getName());
    }

    @Test
    void testSettersAndGetters() {
        DepartmentUpdateDto departmentUpdateDto = new DepartmentUpdateDto();
        departmentUpdateDto.setId(2L);
        departmentUpdateDto.setName("HR");

        assertEquals(2L, departmentUpdateDto.getId());
        assertEquals("HR", departmentUpdateDto.getName());
    }
}

