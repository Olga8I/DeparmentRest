package org.example.service.impl;

import org.example.dto.RoleDto;
import org.example.exception.NotFoundException;
import org.example.mapper.RoleMapper;
import org.example.model.Role;
import org.example.repository.dao.RoleRepositoryDao;
import org.example.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private RoleRepositoryDao roleRepositoryDao;
    private final RoleMapper roleMapper;

    @Autowired
    public RoleServiceImpl(RoleMapper roleMapper) {
        this.roleMapper = roleMapper;
    }

    @Override
    public RoleDto save(RoleDto roleDto) {
        Role role = roleMapper.mapToEntity(roleDto);
        return roleMapper.mapToDto(role);
    }

    @Override
    public void update(org.example.dto.RoleDto roleDto) throws NotFoundException {
        checkRoleExist(roleDto.getId());
        Role role = roleMapper.mapToEntity(roleDto);
        roleRepositoryDao.add(role);
    }

    @Override
    public org.example.dto.RoleDto findById(Long roleId) throws NotFoundException {
        Role role = roleRepositoryDao.findById(roleId)
                .orElseThrow(() ->
                        new NotFoundException("Role not found."));
        return roleMapper.mapToDto(role);
    }

    @Override
    public List<org.example.dto.RoleDto> findAll() {
        List<Role> roleList = roleRepositoryDao.findAll();
        return roleMapper.mapToListToDto(roleList);
    }

    @Override
    public void delete(Long roleId) throws NotFoundException {
        checkRoleExist(roleId);
        roleRepositoryDao.delete(roleId);
    }

    private void checkRoleExist(Long roleId) throws NotFoundException {
        if (!roleRepositoryDao.existsById(roleId)) {
            throw new NotFoundException("Role not found.");
        }
    }
}
