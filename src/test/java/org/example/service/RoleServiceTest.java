package org.example.service;

import org.example.dto.RoleCreateDto;
import org.example.dto.RoleUpdateDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.example.dto.RoleResponseDto;
import org.example.exception.NotFoundException;
import org.example.mapper.RoleMapper;
import org.example.model.Role;
import org.example.repository.RoleRepository;
import org.example.service.impl.RoleServiceImpl;

import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class RoleServiceTest {

    @InjectMocks
    private RoleServiceImpl roleService;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private RoleMapper roleMapper;

    private RoleUpdateDto roleUpdateDto;
    private RoleResponseDto roleResponseDto;
    private RoleCreateDto roleCreateDto;
    private Role role;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        roleResponseDto = new RoleResponseDto("Admin");
        role = new Role("Admin", null);
        role.setId(1L);
    }

    @Test
    public void testSave() {
        when(roleMapper.mapToEntity(roleCreateDto)).thenReturn(role);
        when(roleMapper.mapToDto(role)).thenReturn(roleResponseDto);
        when(roleRepository.save(any(Role.class))).thenReturn(role); // Save behavior

        roleService.save(roleCreateDto);

        verify(roleMapper).mapToEntity(roleCreateDto);
        verify(roleRepository).save(role);
    }

    @Test
    public void testUpdate_Success() throws NotFoundException {
        RoleUpdateDto roleUpdateDto = new RoleUpdateDto(1L, "Admin Updated");
        when(roleRepository.findById(roleUpdateDto.getId())).thenReturn(Optional.of(role));
        when(roleMapper.mapToEntity(roleCreateDto)).thenReturn(role);

        roleService.update(roleUpdateDto);

        verify(roleRepository).save(role);
        Assertions.assertEquals("Admin Updated", role.getName());
    }

    @Test
    public void testUpdate_RoleNotFound() {
        RoleUpdateDto roleUpdateDto = new RoleUpdateDto(1L, "Admin Updated");
        when(roleRepository.findById(roleUpdateDto.getId())).thenReturn(Optional.empty());

        Assertions.assertThrows(NotFoundException.class, () -> roleService.update(roleUpdateDto));
    }

    @Test
    public void testFindById_Success() throws NotFoundException {
        when(roleRepository.findById(role.getId())).thenReturn(Optional.of(role));
        when(roleMapper.mapToDto(role)).thenReturn(roleResponseDto);

        RoleResponseDto foundRoleResponseDto = roleService.findById(role.getId());

        Assertions.assertNotNull(foundRoleResponseDto);
        Assertions.assertEquals(roleResponseDto.getName(), foundRoleResponseDto.getName());
    }

    @Test
    public void testFindById_RoleNotFound() {
        when(roleRepository.findById(role.getId())).thenReturn(Optional.empty());

        Assertions.assertThrows(NotFoundException.class, () -> roleService.findById(role.getId()));
    }

    @Test
    public void testFindAll() {
        List<Role> roles = Arrays.asList(role);
        List<RoleUpdateDto> roleUpdateDtos = Arrays.asList(roleUpdateDto);

        when(roleRepository.findAll()).thenReturn(roles);
        when(roleMapper.mapToListToDto(roles)).thenReturn(roleUpdateDtos);

        List<RoleResponseDto> foundRoleResponseDtos = roleService.findAll();

        Assertions.assertNotNull(foundRoleResponseDtos);
        Assertions.assertEquals(1, foundRoleResponseDtos.size());
    }

    @Test
    public void testDelete_Success() throws NotFoundException {
        when(roleRepository.existsById(role.getId())).thenReturn(true);

        roleService.delete(role.getId());

        verify(roleRepository).deleteById(role.getId());
    }

    @Test
    public void testDelete_RoleNotFound() {
        when(roleRepository.existsById(role.getId())).thenReturn(false);

        Assertions.assertThrows(NotFoundException.class, () -> roleService.delete(role.getId()));
    }
}
