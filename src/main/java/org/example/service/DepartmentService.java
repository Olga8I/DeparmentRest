package org.example.service;

import org.example.dto.DepartmentDto;
import org.example.exception.NotFoundException;


import java.util.List;

public interface DepartmentService {
    void save(DepartmentDto departmentDto);

    void update(DepartmentDto department) throws NotFoundException;

    DepartmentDto findById(Long departmentId) throws NotFoundException;

    List<DepartmentDto> findAll();

    void delete(Long departmentId) throws NotFoundException;

    void deleteUserFromDepartment(Long departmentId, Long userId) throws NotFoundException;

    void addUserToDepartment(Long departmentId, Long userId) throws NotFoundException;
}
