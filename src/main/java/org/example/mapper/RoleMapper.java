package org.example.mapper;

import org.example.model.Role;
import org.mapstruct.Mapper;

import java.util.List;
@Mapper(componentModel = "spring")
public interface RoleMapper {
    Role mapToEntity(org.example.dto.RoleDto roleDto);

    org.example.dto.RoleDto mapToDto(Role role);

    List<org.example.dto.RoleDto> mapToListToDto(List<Role> roleList);
}
