package org.example.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import jakarta.persistence.EntityNotFoundException;
import org.example.dto.DepartmentDto;
import org.example.exception.NotFoundException;
import org.example.mapper.DepartmentMapper;
import org.example.model.Department;
import org.example.repository.UserRepository;
import org.example.repository.dao.DepartmentRepositoryDao;
import org.example.service.impl.DepartmentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {

    @Mock
    private DepartmentRepositoryDao departmentRepositoryDao;

    @Mock
    private UserRepository userRepository;

    @Mock
    private DepartmentMapper departmentMapper;

    @InjectMocks
    private DepartmentServiceImpl departmentService;

    private DepartmentDto departmentDto;
    private Department department;

    @BeforeEach
    public void setUp() {
        departmentDto = new DepartmentDto();
        departmentDto.setId(1L);
        departmentDto.setName("HR");

        department = new Department();
        department.setId(1L);
        department.setName("HR");

    }

    @Test
    public void testSaveDepartment() {
        departmentService.save(departmentDto);
        verify(departmentRepositoryDao).add(department);
    }

    @Test
    public void testUpdateDepartment() throws NotFoundException {
        when(departmentRepositoryDao.existsById(departmentDto.getId())).thenReturn(true);
        departmentService.update(departmentDto);
        verify(departmentRepositoryDao).update(department);
    }

    @Test
    public void testUpdateDepartmentThrowsNotFoundException() {
        when(departmentRepositoryDao.existsById(departmentDto.getId())).thenReturn(false);
        NotFoundException thrown = assertThrows(NotFoundException.class, () -> {
            departmentService.update(departmentDto);
        });

        org.junit.jupiter.api.Assertions.assertEquals("Department not found.", thrown.getMessage());
    }

    @Test
    public void testFindById() throws NotFoundException {
        when(departmentRepositoryDao.findById(departmentDto.getId())).thenReturn(Optional.of(department));
        DepartmentDto foundDepartment = departmentService.findById(departmentDto.getId());
        verify(departmentRepositoryDao).findById(departmentDto.getId());
        org.junit.jupiter.api.Assertions.assertEquals(departmentDto, foundDepartment);
    }

    @Test
    public void testFindByIdThrowsNotFoundException() {
        when(departmentRepositoryDao.findById(departmentDto.getId())).thenReturn(Optional.empty());
        NotFoundException thrown = assertThrows(NotFoundException.class, () -> {
            departmentService.findById(departmentDto.getId());
        });

        org.junit.jupiter.api.Assertions.assertEquals("Department not found.", thrown.getMessage());
    }

    @Test
    public void testFindAll() {
        when(departmentRepositoryDao.findAll()).thenReturn(Collections.singletonList(department));

        departmentService.findAll();

        verify(departmentRepositoryDao).findAll();
    }

    @Test
    public void testDelete() throws NotFoundException {
        when(departmentRepositoryDao.existsById(departmentDto.getId())).thenReturn(true);
        departmentService.delete(departmentDto.getId());
        verify(departmentRepositoryDao).delete(departmentDto.getId());
    }

    @Test
    public void testDeleteThrowsNotFoundException() {
        when(departmentRepositoryDao.existsById(departmentDto.getId())).thenReturn(false);
        assertThrows(EntityNotFoundException.class, () -> {
            departmentService.delete(departmentDto.getId());
        });
    }
}
