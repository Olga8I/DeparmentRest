package org.example.service;

import org.example.dto.PhoneNumberCreateDto;
import org.example.dto.PhoneNumberResponseDto;
import org.example.dto.PhoneNumberUpdateDto;

import java.util.List;

public interface PhoneNumberService {
    void save(PhoneNumberCreateDto phoneNumberCreateDto);

    void update(PhoneNumberUpdateDto phoneNumberUpdateDto);

    PhoneNumberResponseDto findById(Long phoneNumberId);

    List<PhoneNumberResponseDto> findAll();

    void delete(Long phoneNumberId);
}
