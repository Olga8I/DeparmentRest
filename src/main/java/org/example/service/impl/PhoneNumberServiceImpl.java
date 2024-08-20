package org.example.service.impl;

import org.example.dto.PhoneNumberDto;
import org.example.exception.NotFoundException;
import org.example.model.PhoneNumber;
import org.example.mapper.PhoneNumberMapper;
import org.example.repository.dao.PhoneNumberRepositoryDao;
import org.example.service.PhoneNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhoneNumberServiceImpl implements PhoneNumberService {

    private final PhoneNumberMapper phoneNumberMapper;
    private final PhoneNumberRepositoryDao phoneNumberRepositoryDao;

    @Autowired
    public PhoneNumberServiceImpl(PhoneNumberMapper phoneNumberMapper, PhoneNumberRepositoryDao phoneNumberRepositoryDao) {
        this.phoneNumberMapper = phoneNumberMapper;
        this.phoneNumberRepositoryDao = phoneNumberRepositoryDao;
    }

    @Override
    public void save(PhoneNumberDto phoneNumberDto) {
        PhoneNumber phoneNumber = phoneNumberMapper.mapToEntity(phoneNumberDto);
    }

    @Override
    public void update(PhoneNumberDto phoneNumberDto) throws NotFoundException {
        if (phoneNumberRepositoryDao.existsById(phoneNumberDto.getId())) {
            PhoneNumber phoneNumber = phoneNumberMapper.mapToEntity(phoneNumberDto);
            phoneNumberRepositoryDao.add(phoneNumber);
        } else {
            throw new NotFoundException("PhoneNumber not found.");
        }
    }
    @Override
    public PhoneNumberDto findById(Long phoneNumberId) throws NotFoundException {
        PhoneNumber phoneNumber = phoneNumberRepositoryDao.findById(phoneNumberId)
                .orElseThrow(() -> new NotFoundException("Department not found."));
        return phoneNumberMapper.mapToDto(phoneNumber);
    }

    @Override
    public List<PhoneNumberDto> findAll() {
        List<PhoneNumber> phoneNumberList = phoneNumberRepositoryDao.findAll();
        return phoneNumberMapper.mapToListToDto(phoneNumberList);
    }

    @Override
    public void delete(Long phoneNumberId) {
        phoneNumberRepositoryDao.delete(phoneNumberId);
    }
}

