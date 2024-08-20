package org.example.controller;

import org.example.dto.DepartmentDto;
import org.example.exception.NotFoundException;
import org.example.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping
    public ResponseEntity<DepartmentDto> createDepartment(@RequestBody DepartmentDto departmentDto) {
        departmentService.save(departmentDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(departmentDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentDto> getDepartmentById(@PathVariable Long id) {
        try {
            DepartmentDto departmentDto = departmentService.findById(id);
            return ResponseEntity.ok(departmentDto);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<DepartmentDto>> getAllDepartments() {
        List<DepartmentDto> departmentDto = departmentService.findAll();
        return ResponseEntity.ok(departmentDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateDepartment(@PathVariable Long id, @RequestBody DepartmentDto departmentDto) {
        departmentDto.setId(id);
        try {
            departmentService.update(departmentDto);
            return ResponseEntity.ok().build();
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable Long id) {
        try {
            departmentService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/{departmentId}/users/{userId}")
    public ResponseEntity<Void> addUserToDepartment(@PathVariable Long departmentId, @PathVariable Long userId) {
        try {
            departmentService.addUserToDepartment(departmentId, userId);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{departmentId}/users/{userId}")
    public ResponseEntity<Void> deleteUserFromDepartment(@PathVariable Long departmentId, @PathVariable Long userId) {
        try {
            departmentService.deleteUserFromDepartment(departmentId, userId);
            return ResponseEntity.noContent().build();
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}

