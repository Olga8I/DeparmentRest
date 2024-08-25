package org.example.controller;
import org.example.dto.RoleCreateDto;
import org.example.dto.RoleResponseDto;
import org.example.dto.RoleUpdateDto;
import org.example.exception.NotFoundException;
import org.example.service.RoleService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class RoleControllerTest {

    @InjectMocks
    private RoleController roleController;

    @Mock
    private RoleService roleService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetRoleById() throws NotFoundException {
        RoleResponseDto roleResponseDto = new RoleResponseDto("ROLE_USER");
        roleResponseDto.setId(1L);

        when(roleService.findById(1L)).thenReturn(roleResponseDto);

        RoleResponseDto role = roleController.getRoleById(1L);
        Assertions.assertNotNull(role);
        Assertions.assertEquals(role.getId(), roleResponseDto.getId());
        Assertions.assertEquals(role.getName(), roleResponseDto.getName());
    }

    @Test
    void testGetRoleByIdNotFound() {
        when(roleService.findById(1L)).thenThrow(new NotFoundException("Role not found."));

        assertThrows(NotFoundException.class, () -> roleController.getRoleById(1L));
    }

    @Test
    void testGetAllRoles() {
        List<RoleResponseDto> roles = new ArrayList<>();
        roles.add(new RoleResponseDto("ROLE_USER"));
        roles.add(new RoleResponseDto("ROLE_ADMIN"));

        when(roleService.findAll()).thenReturn(roles);

        List<RoleResponseDto> responseDtoList = roleController.getAllRoles();
        Assertions.assertNotNull(responseDtoList);
        Assertions.assertEquals(2, responseDtoList.size());
    }

    @Test
    void testCreateRole() {
        RoleCreateDto roleCreateDto = new RoleCreateDto("ROLE_USER");
        RoleResponseDto savedRoleResponseDto = new RoleResponseDto("ROLE_USER");
        savedRoleResponseDto.setId(1L);

        doNothing().when(roleService).save(roleCreateDto);

        String result = roleController.createRole(roleCreateDto);
        Assertions.assertEquals(result, "Role with name " + savedRoleResponseDto.getName() + " was created");
    }

    @Test
    void testUpdateRole() {
        RoleUpdateDto roleUpdateDto = new RoleUpdateDto(1L, "ROLE_UPDATED");

        doNothing().when(roleService).update(roleUpdateDto);

        String result = roleController.updateRole(roleUpdateDto);
        Assertions.assertEquals(result, "Role with id " + roleUpdateDto.getId() + " was updated");
    }

    @Test
    void testDeleteRole() throws NotFoundException {
        doNothing().when(roleService).delete(1L);

        String result = roleController.deleteRole(1L);
        Assertions.assertEquals(result, "Role with id " + 1L + " was deleted");
    }

    @Test
    void testDeleteRoleNotFound() {
        doThrow(new NotFoundException("Role not found.")).when(roleService).delete(1L);

        assertThrows(NotFoundException.class, () -> roleController.deleteRole(1L));
    }
}
