package org.example.service.impl;

import org.example.exception.NotFoundException;
import org.example.mapper.DepartmentMapper;
import org.example.model.Department;
import org.example.dto.DepartmentDto;
import org.example.repository.dao.DepartmentRepositoryDao;
import org.example.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepositoryDao departmentRepositoryDao;
    private final DepartmentMapper departmentMapper;

    @Autowired
    public DepartmentServiceImpl(DepartmentRepositoryDao departmentRepositoryDao,
                                 DepartmentMapper departmentMapper) {
        this.departmentRepositoryDao = departmentRepositoryDao;
        this.departmentMapper = departmentMapper;
    }

    private void checkExistDepartment(Long departmentId) throws NotFoundException {
        if (!departmentRepositoryDao.existsById(departmentId)) {
            throw new NotFoundException("Department not found.");
        }
    }
    @Override
    public void save(DepartmentDto departmentDto) {
        Department department = departmentMapper.mapToEntity(departmentDto);

    }

    @Override
    public void update(DepartmentDto departmentDto) throws NotFoundException {
        checkExistDepartment(departmentDto.getId());
        Department department = departmentMapper.mapToEntity(departmentDto);
        departmentRepositoryDao.update(department);
    }

    @Override
    public DepartmentDto findById(Long departmentId) throws NotFoundException {
        Department department = departmentRepositoryDao.findById(departmentId)
                .orElseThrow(() -> new NotFoundException("Department not found."));
        return departmentMapper.mapToDto(department);
    }

    @Override
    public List<DepartmentDto> findAll() {
        List<Department> departmentList = departmentRepositoryDao.findAll();
        return departmentMapper.mapToListToDto(departmentList);
    }

    @Override
    public void delete(Long departmentId) throws NotFoundException {
        checkExistDepartment(departmentId);
        departmentRepositoryDao.delete(departmentId);
    }

    @Override
    public void deleteUserFromDepartment(Long departmentId, Long userId) throws NotFoundException {
        checkExistDepartment(departmentId);
    }

    @Override
    public void addUserToDepartment(Long departmentId, Long userId) throws NotFoundException {
        checkExistDepartment(departmentId);
    }
}

