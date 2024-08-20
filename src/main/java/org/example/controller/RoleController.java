package org.example.controller;

import org.example.dto.RoleCreateDto;
import org.example.dto.RoleResponseDto;
import org.example.dto.RoleUpdateDto;
import org.example.service.RoleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
@Validated
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleResponseDto> getRoleById(@PathVariable Long id) {
        RoleResponseDto roleResponseDto = roleService.findById(id);
        return ResponseEntity.ok(roleResponseDto);
    }

    @GetMapping("/all")
    public ResponseEntity<List<RoleResponseDto>> getAllRoles() {
        List<RoleResponseDto> roles = roleService.findAll();
        return ResponseEntity.ok(roles);
    }

    @PostMapping
    public ResponseEntity<String> createRole(@RequestBody RoleCreateDto roleCreateDto) {
        RoleResponseDto createdRole = roleService.save(roleCreateDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Role " + createdRole.getName() + " was created");
    }

    @PutMapping
    public ResponseEntity<String> updateRole(@RequestBody RoleUpdateDto roleUpdateDto) {
        roleService.update(roleUpdateDto);
        return ResponseEntity.ok("Role id " + roleUpdateDto.getId() + " was updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRole(@PathVariable Long id) {
        roleService.delete(id);
        return ResponseEntity.ok("Role" + id + " was deleted");
    }
}
