package org.example.service;

import org.example.dto.DepartmentCreateDto;
import org.example.dto.DepartmentResponseDto;
import org.example.dto.DepartmentUpdateDto;


import java.util.List;

public interface DepartmentService {
    void save(DepartmentCreateDto departmentCreateDto);

    void update(DepartmentUpdateDto department);

    DepartmentResponseDto findById(Long departmentId);

    List<DepartmentResponseDto> findAll();

    void delete(Long departmentId);

}
