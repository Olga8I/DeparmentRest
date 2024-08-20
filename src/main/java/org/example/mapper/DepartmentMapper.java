package org.example.mapper;

import org.example.dto.DepartmentCreateDto;
import org.example.dto.DepartmentResponseDto;
import org.example.model.Department;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface DepartmentMapper {
    Department mapToEntity(DepartmentCreateDto departmentCreateDto);

    @Mapping(source = "userList", target = "userList")
    DepartmentResponseDto mapToDto(Department department);


}