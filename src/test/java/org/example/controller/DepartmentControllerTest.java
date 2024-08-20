package org.example.controller;
import org.example.dto.DepartmentDto;
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

    private DepartmentDto departmentDto;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        departmentDto = new DepartmentDto();
        departmentDto.setId(1L);
        departmentDto.setName("IT");
    }

    @Test
    public void testCreateDepartment() {
        ResponseEntity<DepartmentDto> response = departmentController.createDepartment(departmentDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(departmentDto, response.getBody());

        verify(departmentService, times(1)).save(departmentDto);
    }

    @Test
    public void testGetDepartmentById_Success() throws NotFoundException {
        when(departmentService.findById(1L)).thenReturn(departmentDto);

        ResponseEntity<DepartmentDto> response = departmentController.getDepartmentById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(departmentDto, response.getBody());
    }

    @Test
    public void testGetDepartmentById_NotFound() throws NotFoundException {
        when(departmentService.findById(1L)).thenThrow(new NotFoundException("Department not found"));

        ResponseEntity<DepartmentDto> response = departmentController.getDepartmentById(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testGetAllDepartments() {
        List<DepartmentDto> departments = Arrays.asList(departmentDto);
        when(departmentService.findAll()).thenReturn(departments);

        ResponseEntity<List<DepartmentDto>> response = departmentController.getAllDepartments();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(departments, response.getBody());
    }

    @Test
    public void testUpdateDepartment_Success() throws NotFoundException {
        departmentDto.setName("Updated IT");
        ResponseEntity<Void> response = departmentController.updateDepartment(1L, departmentDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(departmentService, times(1)).update(departmentDto);
    }

    @Test
    public void testUpdateDepartment_NotFound() throws NotFoundException {
        doThrow(new NotFoundException("Department not found")).when(departmentService).update(departmentDto);

        ResponseEntity<Void> response = departmentController.updateDepartment(1L, departmentDto);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testDeleteDepartment_Success() throws NotFoundException {
        ResponseEntity<Void> response = departmentController.deleteDepartment(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(departmentService, times(1)).delete(1L);
    }

    @Test
    public void testDeleteDepartment_NotFound() throws NotFoundException {
        doThrow(new NotFoundException("Department not found")).when(departmentService).delete(1L);

        ResponseEntity<Void> response = departmentController.deleteDepartment(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testAddUserToDepartment_Success() throws NotFoundException {
        ResponseEntity<Void> response = departmentController.addUserToDepartment(1L, 1L);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(departmentService, times(1)).addUserToDepartment(1L, 1L);
    }

    @Test
    public void testAddUserToDepartment_NotFound() throws NotFoundException {
        doThrow(new NotFoundException("Department not found")).when(departmentService).addUserToDepartment(1L, 1L);

        ResponseEntity<Void> response = departmentController.addUserToDepartment(1L, 1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testDeleteUserFromDepartment_Success() throws NotFoundException {
        ResponseEntity<Void> response = departmentController.deleteUserFromDepartment(1L, 1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(departmentService, times(1)).deleteUserFromDepartment(1L, 1L);
    }

    @Test
    public void testDeleteUserFromDepartment_NotFound() throws NotFoundException {
        doThrow(new NotFoundException("Department not found")).when(departmentService).deleteUserFromDepartment(1L, 1L);

        ResponseEntity<Void> response = departmentController.deleteUserFromDepartment(1L, 1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
