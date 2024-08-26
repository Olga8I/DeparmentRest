package org.example.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import org.example.dto.DepartmentCreateDto;
import org.example.dto.DepartmentResponseDto;
import org.example.dto.DepartmentUpdateDto;
import org.example.exception.NotFoundException;
import org.example.mapper.DepartmentMapper;
import org.example.model.Department;
import org.example.repository.DepartmentRepository;
import org.example.service.impl.DepartmentServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {

    @Mock
    private DepartmentRepository departmentRepository;

    @Mock
    private DepartmentMapper departmentMapper;

    @InjectMocks
    private DepartmentServiceImpl departmentService;

    private DepartmentCreateDto departmentCreateDto;
    private DepartmentUpdateDto departmentUpdateDto;
    private DepartmentResponseDto departmentResponseDto;
    private Department department;

    @BeforeEach
    public void setUp() {
        departmentCreateDto = new DepartmentCreateDto("HR");
        departmentUpdateDto = new DepartmentUpdateDto(1L, "HR");
        departmentResponseDto = new DepartmentResponseDto(1L, "HR",null);

        department = new Department("HR");
        department.setId(1L);
    }

    @Test
    public void testSaveDepartment() {
        when(departmentMapper.mapToEntity(departmentCreateDto)).thenReturn(department);
        departmentService.save(departmentCreateDto);
        verify(departmentRepository).save(department);
    }
    @Test
    public void testSaveDepartmentWithoutName() {
        DepartmentCreateDto departmentCreateDto = new DepartmentCreateDto();
        departmentCreateDto.setName(null);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            departmentService.save(departmentCreateDto);
        });

        String expectedMessage = "Department must have a name";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
        verify(departmentRepository, never()).save(department);
    }



    @Test
    public void testUpdateDepartment() throws NotFoundException {
        when(departmentRepository.findById(departmentUpdateDto.getId())).thenReturn(Optional.of(department));
        departmentService.update(departmentUpdateDto);
        verify(departmentRepository).save(department);
    }

    @Test
    public void testUpdateDepartmentThrowsNotFoundException() {
        when(departmentRepository.findById(departmentUpdateDto.getId())).thenReturn(Optional.empty());
        NotFoundException thrown = assertThrows(NotFoundException.class, () -> {
            departmentService.update(departmentUpdateDto);
        });

        org.junit.jupiter.api.Assertions.assertEquals("Department not found.", thrown.getMessage());
    }

    @Test
    public void testFindById() throws NotFoundException {
        when(departmentRepository.findById(department.getId())).thenReturn(Optional.of(department));
        when(departmentMapper.mapToDto(department)).thenReturn(departmentResponseDto);
        DepartmentResponseDto foundDepartment = departmentService.findById(department.getId());
        verify(departmentRepository).findById(department.getId());
        Assertions.assertEquals(department.getName(), foundDepartment.getName());
    }

    @Test
    public void testFindByIdThrowsNotFoundException() {
        when(departmentRepository.findById(department.getId())).thenReturn(Optional.empty());
        NotFoundException thrown = assertThrows(NotFoundException.class, () -> {
            departmentService.findById(department.getId());
        });

        org.junit.jupiter.api.Assertions.assertEquals("Department not found.", thrown.getMessage());
    }

    @Test
    public void testFindAll() {
        when(departmentRepository.findAll()).thenReturn(Collections.singletonList(department));

        departmentService.findAll();

        verify(departmentRepository).findAll();
    }

    @Test
    public void testDelete() throws NotFoundException {
        when(departmentRepository.existsById(department.getId())).thenReturn(true);
        departmentService.delete(department.getId());
        verify(departmentRepository).deleteById(department.getId());
    }

    @Test
    public void testDeleteThrowsNotFoundException() {
        when(departmentRepository.existsById(department.getId())).thenReturn(false);
        assertThrows(NotFoundException.class, () -> {
            departmentService.delete(department.getId());
        });
    }
}
