package org.example.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.controller.RoleController;
import org.example.dto.RoleDto;
import org.example.exception.NotFoundException;
import org.example.service.RoleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

@SpringJUnitConfig
public class RoleControllerTest {

    @InjectMocks
    private RoleController roleController;

    @Mock
    private RoleService roleService;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testCreateRole() {
        RoleDto roleDto = new RoleDto("ROLE_USER");
        RoleDto savedRoleDto = new RoleDto("ROLE_USER");
        savedRoleDto.setId(1L);

        when(roleService.save(any(RoleDto.class))).thenReturn(savedRoleDto);

        ResponseEntity<RoleDto> responseEntity = roleController.createRole(roleDto);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(savedRoleDto, responseEntity.getBody());
        verify(roleService, times(1)).save(roleDto);
    }

    @Test
    public void testGetRoleById() throws NotFoundException {
        RoleDto roleDto = new RoleDto("ROLE_USER");
        roleDto.setId(1L);

        when(roleService.findById(1L)).thenReturn(roleDto);

        ResponseEntity<RoleDto> responseEntity = roleController.getRoleById(1L);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(roleDto, responseEntity.getBody());
        verify(roleService, times(1)).findById(1L);
    }

    @Test
    public void testGetRoleByIdNotFound() throws NotFoundException {
        when(roleService.findById(1L)).thenThrow(new NotFoundException("Role not found."));

        ResponseEntity<RoleDto> responseEntity = roleController.getRoleById(1L);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        verify(roleService, times(1)).findById(1L);
    }

    @Test
    public void testGetAllRoles() {
        RoleDto roleDto1 = new RoleDto("ROLE_USER");
        RoleDto roleDto2 = new RoleDto("ROLE_ADMIN");
        when(roleService.findAll()).thenReturn(Arrays.asList(roleDto1, roleDto2));

        ResponseEntity<List<RoleDto>> responseEntity = roleController.getAllRoles();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(2, responseEntity.getBody().size());
        verify(roleService, times(1)).findAll();
    }

    @Test
    public void testDeleteRole() throws NotFoundException {
        doNothing().when(roleService).delete(1L);

        ResponseEntity<Void> responseEntity = roleController.deleteRole(1L);

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
        verify(roleService, times(1)).delete(1L);
    }

    @Test
    public void testDeleteRoleNotFound() throws NotFoundException {
        doThrow(new NotFoundException("Role not found.")).when(roleService).delete(1L);

        ResponseEntity<Void> responseEntity = roleController.deleteRole(1L);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        verify(roleService, times(1)).delete(1L);
    }
}

