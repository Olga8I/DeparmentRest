package org.example.mapper;

import org.example.dto.PhoneNumberCreateDto;
import org.example.dto.PhoneNumberResponseDto;
import org.example.dto.PhoneNumberUpdateDto;
import org.example.model.PhoneNumber;
import org.mapstruct.Mapper;

import java.util.List;
@Mapper(componentModel = "spring")
public interface PhoneNumberMapper {
    PhoneNumber mapToEntity(PhoneNumberCreateDto phoneNumberCreateDto);

    PhoneNumberResponseDto mapToDto (PhoneNumber phoneNumber);

    List<PhoneNumberUpdateDto> mapToListToDto(List<PhoneNumber> phoneNumberList);

}
