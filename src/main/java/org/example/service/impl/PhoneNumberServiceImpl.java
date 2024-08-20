package org.example.service.impl;

import org.example.dto.PhoneNumberCreateDto;
import org.example.dto.PhoneNumberResponseDto;
import org.example.dto.PhoneNumberUpdateDto;
import org.example.exception.NotFoundException;
import org.example.model.PhoneNumber;
import org.example.mapper.PhoneNumberMapper;
import org.example.repository.PhoneNumberRepository;
import org.example.service.PhoneNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhoneNumberServiceImpl implements PhoneNumberService {

    private final PhoneNumberMapper phoneNumberMapper;
    private final PhoneNumberRepository phoneNumberRepository;

    @Autowired
    public PhoneNumberServiceImpl(PhoneNumberMapper phoneNumberMapper, PhoneNumberRepository phoneNumberRepository) {
        this.phoneNumberMapper = phoneNumberMapper;
        this.phoneNumberRepository = phoneNumberRepository;
    }

    @Override
    public void save(PhoneNumberCreateDto phoneNumberCreateDto) {
        PhoneNumber phoneNumber = phoneNumberMapper.mapToEntity(phoneNumberCreateDto);
    }

    @Override
    public void update(PhoneNumberUpdateDto phoneNumberUpdateDto) {
        PhoneNumber phoneNumber = phoneNumberRepository.findById(phoneNumberUpdateDto.getId()).orElseThrow(
                () -> new NotFoundException("Phone number not found.")
        );
        phoneNumber.setNumber(phoneNumberUpdateDto.getNumber());
        phoneNumberRepository.save(phoneNumber);
    }
    @Override
    public PhoneNumberResponseDto findById(Long phoneNumberId) throws NotFoundException {
        PhoneNumber phoneNumber = phoneNumberRepository.findById(phoneNumberId)
                .orElseThrow(() -> new NotFoundException("Phone number not found."));
        return phoneNumberMapper.mapToDto(phoneNumber);
    }

    @Override
    public List<PhoneNumberResponseDto> findAll() {

        return phoneNumberRepository.findAll().stream().map(phoneNumberMapper::mapToDto).toList();
    }

    @Override
    public void delete(Long phoneNumberId) {
        phoneNumberRepository.deleteById(phoneNumberId);
    }
}

