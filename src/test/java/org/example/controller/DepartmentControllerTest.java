package org.example.controller;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.example.dto.DepartmentCreateDto;
import org.example.dto.DepartmentResponseDto;
import org.example.dto.DepartmentUpdateDto;
import org.example.exception.NotFoundException;
import org.example.service.DepartmentService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

class DepartmentControllerTest {

    @InjectMocks
    private DepartmentController departmentController;

    @Mock
    private DepartmentService departmentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetDepartmentById() throws NotFoundException {
        DepartmentResponseDto expectedDepartment = new DepartmentResponseDto(1L, "HR", null);

        when(departmentService.findById(1L)).thenReturn(expectedDepartment);

        DepartmentResponseDto actualDepartment = departmentController.getDepartmentById(1L).getBody();
        Assertions.assertNotNull(actualDepartment);
        Assertions.assertEquals(expectedDepartment.getId(), actualDepartment.getId());
        Assertions.assertEquals(expectedDepartment.getName(), actualDepartment.getName());
    }

    @Test
    void testGetDepartmentByIdNotFound() {
        when(departmentService.findById(1L)).thenThrow(new NotFoundException("Department not found"));

        Assertions.assertThrows(NotFoundException.class, () -> departmentController.getDepartmentById(1L));
    }

    @Test
    void testGetAllDepartments() {
        List<DepartmentResponseDto> expectedDepartments = new ArrayList<>();
        expectedDepartments.add(new DepartmentResponseDto(1L, "Department", null));
        expectedDepartments.add(new DepartmentResponseDto(1L,"Department2", null));

        when(departmentService.findAll()).thenReturn(expectedDepartments);

        List<DepartmentResponseDto> actualDepartments = departmentController.getAllDepartments().getBody();
        Assertions.assertNotNull(actualDepartments);
        Assertions.assertEquals(2, actualDepartments.size());
    }

    @Test
    void testCreateDepartment() {
        DepartmentCreateDto departmentCreateDto = new DepartmentCreateDto();
        departmentCreateDto.setName("New Department");

        doNothing().when(departmentService).save(departmentCreateDto);

        String result = departmentController.createDepartment(departmentCreateDto).getBody();
        Assertions.assertEquals("Department New Department was created", result);
    }

    @Test
    void testUpdateDepartment() throws NotFoundException {
        DepartmentUpdateDto departmentUpdateDto = new DepartmentUpdateDto(1L, "Updated IT");

        doNothing().when(departmentService).update(departmentUpdateDto);

        String result = departmentController.updateDepartment(departmentUpdateDto).getBody();
        Assertions.assertEquals("Department 1 was updated", result);
    }

    @Test
    void testUpdateDepartmentNotFound() {
        DepartmentUpdateDto departmentUpdateDto = new DepartmentUpdateDto(1L, "Updated IT");

        doThrow(new NotFoundException("Department not found")).when(departmentService).update(departmentUpdateDto);

        Assertions.assertThrows(NotFoundException.class, () -> departmentController.updateDepartment(departmentUpdateDto));
    }

    @Test
    void testDeleteDepartment() throws NotFoundException {
        doNothing().when(departmentService).delete(1L);

        String result = departmentController.deleteDepartment(1L).getBody();
        Assertions.assertEquals("Department 1 was deleted", result);
    }

    @Test
    void testDeleteDepartmentNotFound() {
        doThrow(new NotFoundException("Department not found")).when(departmentService).delete(1L);

        Assertions.assertThrows(NotFoundException.class, () -> departmentController.deleteDepartment(1L));
    }
}
