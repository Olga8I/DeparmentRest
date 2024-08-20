package org.example.service;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.example.exception.NotFoundException;
import org.example.mapper.RoleMapper;
import org.example.model.Role;
import org.example.repository.dao.RoleRepositoryDao;
import org.example.service.impl.RoleServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class RoleServiceTest {

    @InjectMocks
    private RoleServiceImpl roleService;

    @Mock
    private RoleRepositoryDao roleRepositoryDao;

    @Mock
    private RoleMapper roleMapper;

    private org.example.dto.RoleDto roleDto;
    private Role role;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        roleDto = new org.example.dto.RoleDto("Admin");
        role = new Role("Admin",null);
        role.setId(1L);
    }

    @Test
    public void testSave() {
        when(roleMapper.mapToEntity(roleDto)).thenReturn(role);
        when(roleMapper.mapToDto(role)).thenReturn(roleDto);

        org.example.dto.RoleDto savedRoleDto = roleService.save(roleDto);

        assertNotNull(savedRoleDto);
        assertEquals(roleDto.getName(), savedRoleDto.getName());
        verify(roleMapper).mapToEntity(roleDto);
        verify(roleMapper).mapToDto(role);
    }

    @Test
    public void testUpdate_Success() throws NotFoundException {
        when(roleMapper.mapToEntity(roleDto)).thenReturn(role);
        when(roleRepositoryDao.existsById(roleDto.getId())).thenReturn(true);

        roleService.update(roleDto);

        verify(roleRepositoryDao).add(role);
    }

    @Test
    public void testUpdate_RoleNotFound() {
        when(roleRepositoryDao.existsById(roleDto.getId())).thenReturn(false);

        assertThrows(NotFoundException.class, () -> roleService.update(roleDto));
    }

    @Test
    public void testFindById_Success() throws NotFoundException {
        when(roleRepositoryDao.findById(role.getId())).thenReturn(Optional.of(role));
        when(roleMapper.mapToDto(role)).thenReturn(roleDto);

        org.example.dto.RoleDto foundRoleDto = roleService.findById(role.getId());

        assertNotNull(foundRoleDto);
        assertEquals(roleDto.getName(), foundRoleDto.getName());
    }

    @Test
    public void testFindById_RoleNotFound() {
        when(roleRepositoryDao.findById(role.getId())).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> roleService.findById(role.getId()));
    }

    @Test
    public void testFindAll() {
        List<Role> roles = Arrays.asList(role);
        List<org.example.dto.RoleDto> roleDtos = Arrays.asList(roleDto);

        when(roleRepositoryDao.findAll()).thenReturn(roles);
        when(roleMapper.mapToListToDto(roles)).thenReturn(roleDtos);

        List<org.example.dto.RoleDto> foundRoleDtos = roleService.findAll();

        assertNotNull(foundRoleDtos);
        assertEquals(1, foundRoleDtos.size());
        assertEquals(roleDto, foundRoleDtos.get(0));
    }

    @Test
    public void testDelete_Success() throws NotFoundException {
        when(roleRepositoryDao.existsById(role.getId())).thenReturn(true);

        roleService.delete(role.getId());

        verify(roleRepositoryDao).delete(role.getId());
    }

    @Test
    public void testDelete_RoleNotFound() {
        when(roleRepositoryDao.existsById(role.getId())).thenReturn(false);

        assertThrows(NotFoundException.class, () -> roleService.delete(role.getId()));
    }
}

