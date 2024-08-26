package org.example.mapper;

import org.example.dto.PhoneNumberCreateDto;
import org.example.dto.PhoneNumberResponseDto;
import org.example.dto.PhoneNumberUpdateDto;
import org.example.dto.UserResponseDto;
import org.example.model.PhoneNumber;
import org.example.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
@Mapper(componentModel = "spring")
public interface PhoneNumberMapper {
    PhoneNumber mapToEntity(PhoneNumberCreateDto phoneNumberCreateDto);
    UserResponseDto mapToDto(User user);
    @Mapping(source = "user", target = "userDto")
    PhoneNumberResponseDto mapToDto (PhoneNumber phoneNumber);

    List<PhoneNumberUpdateDto> mapToListToDto(List<PhoneNumber> phoneNumberList);

}