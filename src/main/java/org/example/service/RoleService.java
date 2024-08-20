package org.example.service;

import org.example.dto.RoleDto;
import org.example.exception.NotFoundException;

import java.util.List;

public interface RoleService {
    RoleDto save(RoleDto role);

    void update(RoleDto role) throws NotFoundException;

    RoleDto findById(Long roleId) throws NotFoundException;

    List<RoleDto> findAll();

    void delete(Long roleId) throws NotFoundException;
}
