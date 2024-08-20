package org.example.controller;

import org.example.dto.DepartmentCreateDto;
import org.example.dto.DepartmentResponseDto;
import org.example.dto.DepartmentUpdateDto;
import org.example.exception.NotFoundException;
import org.example.service.DepartmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class DepartmentControllerTest {

    @InjectMocks
    private DepartmentController departmentController;

    @Mock
    private DepartmentService departmentService;

    private DepartmentCreateDto departmentCreateDto;
    private DepartmentResponseDto departmentResponseDto;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        departmentCreateDto = new DepartmentCreateDto();
        departmentCreateDto.setName("IT");

        departmentResponseDto = new DepartmentResponseDto();
        departmentResponseDto.setId(1L);
        departmentResponseDto.setName("IT");
    }

    @Test
    public void testCreateDepartment() {
        ResponseEntity<String> response = departmentController.createDepartment(departmentCreateDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Department IT was created", response.getBody());

        verify(departmentService, times(1)).save(departmentCreateDto);
    }

    @Test
    public void testGetDepartmentById_Success() throws NotFoundException {
        when(departmentService.findById(1L)).thenReturn(departmentResponseDto);

        ResponseEntity<DepartmentResponseDto> response = departmentController.getDepartmentById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(departmentResponseDto, response.getBody());
    }

    @Test
    public void testGetDepartmentById_NotFound() throws NotFoundException {
        when(departmentService.findById(1L)).thenThrow(new NotFoundException("Department not found"));

        assertThrows(NotFoundException.class, () -> departmentController.getDepartmentById(1L));
    }

    @Test
    public void testGetAllDepartments() {
        List<DepartmentResponseDto> departments = Arrays.asList(departmentResponseDto);
        when(departmentService.findAll()).thenReturn(departments);

        ResponseEntity<List<DepartmentResponseDto>> response = departmentController.getAllDepartments();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(departments, response.getBody());
    }

    @Test
    public void testUpdateDepartment_Success() throws NotFoundException {
        DepartmentUpdateDto departmentUpdateDto = new DepartmentUpdateDto(1L, "Updated IT");
        ResponseEntity<String> response = departmentController.updateDepartment(departmentUpdateDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Department id 1 was updated", response.getBody());
        verify(departmentService, times(1)).update(departmentUpdateDto);
    }

    @Test
    public void testUpdateDepartment_NotFound() throws NotFoundException {
        DepartmentUpdateDto departmentUpdateDto = new DepartmentUpdateDto(1L, "Updated IT");
        doThrow(new NotFoundException("Department not found")).when(departmentService).update(departmentUpdateDto);

        assertThrows(NotFoundException.class, () -> departmentController.updateDepartment(departmentUpdateDto));
    }

    @Test
    public void testDeleteDepartment_Success() throws NotFoundException {
        ResponseEntity<String> response = departmentController.deleteDepartment(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Department id 1 was deleted", response.getBody());
        verify(departmentService, times(1)).delete(1L);
    }

    @Test
    public void testDeleteDepartment_NotFound() throws NotFoundException {
        doThrow(new NotFoundException("Department not found")).when(departmentService).delete(1L);

        assertThrows(NotFoundException.class, () -> departmentController.deleteDepartment(1L));
    }
}
