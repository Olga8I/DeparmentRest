package org.example.service;

import org.example.dto.PhoneNumberDto;
import org.example.exception.NotFoundException;

import java.util.List;

public interface PhoneNumberService {
    void save(PhoneNumberDto phoneNumber);

    void update(PhoneNumberDto phoneNumber) throws NotFoundException;

    PhoneNumberDto findById(Long phoneNumberId) throws NotFoundException;

    List<PhoneNumberDto> findAll();

    void delete(Long phoneNumberId);
}
