package org.example.controller;

import org.example.dto.RoleCreateDto;
import org.example.dto.RoleResponseDto;
import org.example.dto.RoleUpdateDto;
import org.example.service.RoleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping(value = "/roles/{id}")
    public RoleResponseDto getRoleById(@PathVariable("id") Long id) {
        return roleService.findById(id);
    }

    @GetMapping(value = "/roles/all")
    public List<RoleResponseDto> getAllRoles() {
        return roleService.findAll();
    }

    @PostMapping(value = "/roles")
    public String createRole(@RequestBody RoleCreateDto roleCreateDto) {
        roleService.save(roleCreateDto);
        return "Role with name " + roleCreateDto.getName() + " was created";
    }

    @PutMapping(value = "/roles")
    public String updateRole(@RequestBody RoleUpdateDto roleUpdateDto) {
        roleService.update(roleUpdateDto);
        return "Role with id " + roleUpdateDto.getId() + " was updated";
    }

    @DeleteMapping("/roles/{id}")
    public String deleteRole(@PathVariable("id") Long id) {
        roleService.delete(id);
        return "Role with id " + id + " was deleted";
    }
}
