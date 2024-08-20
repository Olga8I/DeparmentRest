package org.example.service.impl;

import org.example.dto.RoleCreateDto;
import org.example.dto.RoleResponseDto;
import org.example.dto.RoleUpdateDto;
import org.example.exception.NotFoundException;
import org.example.mapper.RoleMapper;
import org.example.model.Role;
import org.example.repository.RoleRepository;
import org.example.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository, RoleMapper roleMapper) {
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
    }

    @Override
    public RoleResponseDto save(RoleCreateDto roleCreateDto) {
        Role role = roleMapper.mapToEntity(roleCreateDto);
        return roleMapper.mapToDto(role);
    }

    @Override
    public void update(RoleUpdateDto roleUpdateDto){
        Role role = roleRepository.findById(roleUpdateDto.getId()).orElseThrow(
                () -> new NotFoundException("Role not found.")
        );
        role.setName(roleUpdateDto.getName());
        roleRepository.save(role);
    }

    @Override
    public RoleResponseDto findById(Long roleId) throws NotFoundException {
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() ->
                        new NotFoundException("Role not found."));
        return roleMapper.mapToDto(role);
    }

    @Override
    public List<RoleResponseDto> findAll() {
        return roleRepository.findAll().stream().map(roleMapper::mapToDto).toList();
    }

    @Override
    public void delete(Long roleId) throws NotFoundException {
        checkRoleExist(roleId);
        roleRepository.deleteById(roleId);
    }

    private void checkRoleExist(Long roleId){
        if (!roleRepository.existsById(roleId)) {
            throw new NotFoundException("Role not found.");
        }
    }
}
