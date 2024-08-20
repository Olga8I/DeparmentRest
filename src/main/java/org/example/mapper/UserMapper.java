package org.example.mapper;

import org.example.model.User;
import org.example.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {RoleMapper.class, DepartmentMapper.class, PhoneNumberMapper.class})
public interface UserMapper {
    @Mapping(source = "roleDto", target = "role")
    @Mapping(source = "departmentList", target = "departmentList")
    @Mapping(source = "phoneNumberList", target = "phoneNumberList")
    User mapToEntity(UserDto userDto);
    @Mapping(source = "role", target = "roleDto")
    @Mapping(source = "departmentList", target = "departmentList")
    @Mapping(source = "phoneNumberList", target = "phoneNumberList")
    UserDto mapToDto(User user);

    List<UserDto> mapToListToDto(List<User> user);

}
