package org.example.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.dto.RoleCreateDto;
import org.example.dto.RoleResponseDto;
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
        RoleCreateDto roleCreateDto = new RoleCreateDto("ROLE_USER");
        RoleResponseDto savedRoleResponseDto = new RoleResponseDto("ROLE_USER");
        savedRoleResponseDto.setId(1L);

        when(roleService.save(any(RoleCreateDto.class))).thenReturn(savedRoleResponseDto);

        ResponseEntity<String> responseEntity = roleController.createRole(roleCreateDto);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(savedRoleResponseDto, responseEntity.getBody());
        verify(roleService, times(1)).save(roleCreateDto);
    }

    @Test
    public void testGetRoleById() throws NotFoundException {
        RoleResponseDto roleResponseDto = new RoleResponseDto("ROLE_USER");
        roleResponseDto.setId(1L);

        when(roleService.findById(1L)).thenReturn(roleResponseDto);

        ResponseEntity<RoleResponseDto> responseEntity = roleController.getRoleById(1L);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(roleResponseDto, responseEntity.getBody());
        verify(roleService, times(1)).findById(1L);
    }

    @Test
    public void testGetRoleByIdNotFound() throws NotFoundException {
        when(roleService.findById(1L)).thenThrow(new NotFoundException("Role not found."));

        ResponseEntity<RoleResponseDto> responseEntity = roleController.getRoleById(1L);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        verify(roleService, times(1)).findById(1L);
    }

    @Test
    public void testGetAllRoles() {
        RoleResponseDto roleResponseDto1 = new RoleResponseDto("ROLE_USER");
        RoleResponseDto roleResponseDto2 = new RoleResponseDto("ROLE_ADMIN");
        when(roleService.findAll()).thenReturn(Arrays.asList(roleResponseDto1, roleResponseDto2));

        ResponseEntity<List<RoleResponseDto>> responseEntity = roleController.getAllRoles();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(2, responseEntity.getBody().size());
        verify(roleService, times(1)).findAll();
    }

    @Test
    public void testDeleteRole() throws NotFoundException {
        doNothing().when(roleService).delete(1L);

        ResponseEntity<String> responseEntity = roleController.deleteRole(1L);

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
        verify(roleService, times(1)).delete(1L);
    }

    @Test
    public void testDeleteRoleNotFound() throws NotFoundException {
        doThrow(new NotFoundException("Role not found.")).when(roleService).delete(1L);

        ResponseEntity<String> responseEntity = roleController.deleteRole(1L);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        verify(roleService, times(1)).delete(1L);
    }
}
