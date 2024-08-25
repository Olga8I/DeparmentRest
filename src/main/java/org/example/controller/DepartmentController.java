package org.example.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.example.dto.DepartmentCreateDto;
import org.example.dto.DepartmentResponseDto;
import org.example.dto.DepartmentUpdateDto;
import org.example.service.DepartmentService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping(value = "/departments/{id}")
    public ResponseEntity<DepartmentResponseDto> getDepartmentById(@PathVariable("id") Long id) {
        DepartmentResponseDto departmentResponseDto = departmentService.findById(id);
        return ResponseEntity.ok(departmentResponseDto);
    }

    @GetMapping(value = "/departments/all")
    public ResponseEntity<List<DepartmentResponseDto>> getAllDepartments() {
        List<DepartmentResponseDto> departments = departmentService.findAll();
        return ResponseEntity.ok(departments);
    }

    @PostMapping(value = "/departments")
    public ResponseEntity<String> createDepartment(@RequestBody DepartmentCreateDto departmentCreateDto) {
        departmentService.save(departmentCreateDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Department " + departmentCreateDto.getName() + " was created");
    }

    @PutMapping(value = "/departments")
    public ResponseEntity<String> updateDepartment(@RequestBody DepartmentUpdateDto departmentUpdateDto) {
        departmentService.update(departmentUpdateDto);
        return ResponseEntity.ok("Department " + departmentUpdateDto.getId() + " was updated");
    }

    @DeleteMapping("/departments/{id}")
    public ResponseEntity<String> deleteDepartment(@PathVariable("id") Long id) {
        departmentService.delete(id);
        return ResponseEntity.ok("Department " + id + " was deleted");
    }
}
