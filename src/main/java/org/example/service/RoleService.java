package org.example.service;

import org.example.dto.RoleCreateDto;
import org.example.dto.RoleResponseDto;
import org.example.dto.RoleUpdateDto;

import java.util.List;

public interface RoleService {
    void save(RoleCreateDto role);

    void update(RoleUpdateDto role);

    RoleResponseDto findById(Long roleId);

    List<RoleResponseDto> findAll();

    void delete(Long roleId);
}
