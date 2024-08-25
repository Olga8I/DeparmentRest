package org.example.mapper;

import org.example.dto.RoleCreateDto;
import org.example.dto.RoleResponseDto;
import org.example.dto.RoleUpdateDto;
import org.example.model.Role;
import org.mapstruct.Mapper;

import java.util.List;
@Mapper(componentModel = "spring")
public interface RoleMapper {
    Role mapToEntity(RoleCreateDto roleCreateDto);

    RoleResponseDto mapToDto(Role role);

    List<RoleUpdateDto> mapToListToDto(List<Role> roleList);

}
