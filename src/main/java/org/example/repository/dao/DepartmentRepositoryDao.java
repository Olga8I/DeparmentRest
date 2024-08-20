package org.example.repository.dao;

import org.example.model.Department;
import org.example.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class DepartmentRepositoryDao {
    private final DepartmentRepository departmentRepository;


    @Autowired
    public DepartmentRepositoryDao(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }
    public void add(Department department) {
        departmentRepository.save(department);
    }

    public void update(Department department) {
        departmentRepository.save(department);
    }

    public void delete(Long id) {
        departmentRepository.deleteById(id);
    }

    public Optional<Department> findById(Long id) {
        return Optional.ofNullable(departmentRepository.getReferenceById(id));
    }

    public boolean  existsById (Long id){ return departmentRepository.existsById(id);}

    public List<Department> findAll() {
        return departmentRepository.findAll();
    }
}
