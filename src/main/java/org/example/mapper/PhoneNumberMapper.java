package org.example.mapper;

import org.example.dto.*;
import org.example.model.Department;
import org.example.model.PhoneNumber;
import org.example.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface PhoneNumberMapper {
    PhoneNumber mapToEntity(PhoneNumberCreateDto phoneNumberCreateDto);
    UserResponseDto mapToDto(User user);
    @Mapping(source = "user", target = "userDto")
    PhoneNumberResponseDto mapToDto (PhoneNumber phoneNumber);

    List<PhoneNumberUpdateDto> mapToListToDto(List<PhoneNumber> phoneNumberList);

    List<PhoneNumberResponseDto> phoneNumberListToPhoneNumberResponseDtoList(List<PhoneNumber> phoneNumbers);

    Set<DepartmentCreateDto> departmentSetToDepartmentCreateDtoSet(Set<Department> departments);
}
