package org.example.service.impl;

import org.example.dto.DepartmentResponseDto;
import org.example.dto.DepartmentUpdateDto;
import org.example.exception.NotFoundException;
import org.example.mapper.DepartmentMapper;
import org.example.model.Department;
import org.example.dto.DepartmentCreateDto;
import org.example.repository.DepartmentRepository;
import org.example.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final DepartmentMapper departmentMapper;

    @Autowired
    public DepartmentServiceImpl(DepartmentRepository departmentRepository,
                                 DepartmentMapper departmentMapper) {
        this.departmentRepository = departmentRepository;
        this.departmentMapper = departmentMapper;
    }

    private void checkExistDepartment(Long departmentId) {
        if (!departmentRepository.existsById(departmentId)) {
            throw new NotFoundException("Department not found.");
        }
    }

    @Override
    public void save(DepartmentCreateDto departmentCreateDto) {
        Department department = departmentMapper.mapToEntity(departmentCreateDto);
        if (department.getName() == null) {
            throw new IllegalArgumentException("Department must have an id");
        }
    }

    @Override
    public void update(DepartmentUpdateDto departmentUpdateDto) {
        Department department = departmentRepository.findById(departmentUpdateDto.getId()).orElseThrow(
                () -> new NotFoundException("Department not found.")
        );
        department.setName(departmentUpdateDto.getName());
        departmentRepository.save(department);
    }

    @Override
    public DepartmentResponseDto findById(Long departmentId) {
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new NotFoundException("Department not found."));
        return departmentMapper.mapToDto(department);
    }

    @Override
    public List<DepartmentResponseDto> findAll() {
        return departmentRepository.findAll().stream().map(departmentMapper::mapToDto).toList();
    }

    @Override
    public void delete(Long departmentId) throws NotFoundException {
        checkExistDepartment(departmentId);
        departmentRepository.deleteById(departmentId);
    }

}

