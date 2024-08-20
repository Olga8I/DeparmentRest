package org.example.mapper;

import org.example.dto.DepartmentDto;
import org.example.model.Department;
import org.mapstruct.Mapper;

import java.util.List;
@Mapper(componentModel = "spring")
public interface DepartmentMapper {
    Department mapToEntity (DepartmentDto departmentDto);

    DepartmentDto mapToDto (Department department);

    List<DepartmentDto> mapToListToDto (List<Department> departmentList);

}