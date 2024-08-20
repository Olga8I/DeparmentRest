package org.example.mapper;

import org.example.dto.PhoneNumberDto;
import org.example.model.PhoneNumber;
import org.mapstruct.Mapper;

import java.util.List;
@Mapper(componentModel = "spring")
public interface PhoneNumberMapper {
    PhoneNumber mapToEntity(PhoneNumberDto phoneNumberDto);

    PhoneNumberDto mapToDto (PhoneNumber phoneNumber);

    List<PhoneNumberDto> mapToListToDto(List<PhoneNumber> phoneNumberList);

}
