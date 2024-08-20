package org.example.mapper;

import org.example.dto.UserCreateDto;
import org.example.dto.UserResponseDto;
import org.example.dto.UserUpdateDto;
import org.example.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {RoleMapper.class, DepartmentMapper.class, PhoneNumberMapper.class})
public interface UserMapper {
    User mapToEntity(UserCreateDto userCreateDto);
    @Mapping(source = "role", target = "roleDto")
    @Mapping(source = "departmentList", target = "departmentList")
    @Mapping(source = "phoneNumberList", target = "phoneNumberList")
    UserResponseDto mapToDto(User user);//responseDto


    List<UserUpdateDto> mapToListToDto(List<User> userList);//updateDto

}
