package org.example.mapper;

import org.example.dto.DepartmentCreateDto;
import org.example.dto.DepartmentResponseDto;
import org.example.dto.UserResponseDto;
import org.example.model.Department;
import org.example.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Set;


@Mapper(componentModel = "spring")
public interface DepartmentMapper {
    Department mapToEntity(DepartmentCreateDto departmentCreateDto);

    @Mapping(source = "userList", target = "userList")
    DepartmentResponseDto mapToDto(Department department);


    DepartmentCreateDto departmentToDepartmentCreateDto(Department department);


    Set<DepartmentCreateDto> departmentSetToDepartmentCreateDtoSet(Set<Department> departments);

    UserResponseDto userToUserResponseDto(User user);
}