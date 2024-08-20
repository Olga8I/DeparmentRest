package org.example.controller;

import org.example.dto.DepartmentCreateDto;
import org.example.dto.DepartmentResponseDto;
import org.example.dto.DepartmentUpdateDto;
import org.example.service.DepartmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
@Validated
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentResponseDto> getDepartmentById(@PathVariable Long id) {
        DepartmentResponseDto departmentResponseDto = departmentService.findById(id);
        return ResponseEntity.ok(departmentResponseDto);
    }

    @GetMapping("/all")
    public ResponseEntity<List<DepartmentResponseDto>> getAllDepartments() {
        List<DepartmentResponseDto> departments = departmentService.findAll();
        return ResponseEntity.ok(departments);
    }

    @PostMapping
    public ResponseEntity<String> createDepartment(@RequestBody DepartmentCreateDto departmentCreateDto) {
        departmentService.save(departmentCreateDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Department " + departmentCreateDto.getName() + " was created");
    }

    @PutMapping
    public ResponseEntity<String> updateDepartment(@RequestBody DepartmentUpdateDto departmentUpdateDto) {
        departmentService.update(departmentUpdateDto);
        return ResponseEntity.ok("Department id " + departmentUpdateDto.getId() + " was updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDepartment(@PathVariable Long id) {
        departmentService.delete(id);
        return ResponseEntity.ok("Department id " + id + " was deleted");
    }
}
