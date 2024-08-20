package org.example.service;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.example.dto.PhoneNumberDto;
import org.example.exception.NotFoundException;
import org.example.mapper.PhoneNumberMapper;
import org.example.model.PhoneNumber;
import org.example.repository.dao.PhoneNumberRepositoryDao;
import org.example.service.impl.PhoneNumberServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PhoneNumberServiceTest {

    @InjectMocks
    private PhoneNumberServiceImpl phoneNumberService;

    @Mock
    private PhoneNumberMapper phoneNumberMapper;

    @Mock
    private PhoneNumberRepositoryDao phoneNumberRepositoryDao;

    private PhoneNumberDto phoneNumberDto;
    private PhoneNumber phoneNumber;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        phoneNumberDto = new PhoneNumberDto();
        phoneNumberDto.setId(1L);
        phoneNumberDto.setNumberDto("123456789");

        phoneNumber = new PhoneNumber();
        phoneNumber.setId(1L);
        phoneNumber.setNumber("123456789");
    }

    @Test
    public void testUpdateSuccess() throws NotFoundException {
        when(phoneNumberRepositoryDao.existsById(phoneNumberDto.getId())).thenReturn(true);
        when(phoneNumberMapper.mapToEntity(phoneNumberDto)).thenReturn(phoneNumber);

        phoneNumberService.update(phoneNumberDto);

        verify(phoneNumberRepositoryDao).add(phoneNumber);
    }

    @Test
    public void testUpdateNotFound() {
        when(phoneNumberRepositoryDao.existsById(phoneNumberDto.getId())).thenReturn(false);

        Exception exception = assertThrows(NotFoundException.class, () -> {
            phoneNumberService.update(phoneNumberDto);
        });

        assertEquals("PhoneNumber not found.", exception.getMessage());
    }

    @Test
    public void testFindByIdSuccess() throws NotFoundException {
        when(phoneNumberRepositoryDao.findById(phoneNumberDto.getId())).thenReturn(Optional.of(phoneNumber));
        when(phoneNumberMapper.mapToDto(phoneNumber)).thenReturn(phoneNumberDto);

        PhoneNumberDto foundPhoneNumberDto = phoneNumberService.findById(phoneNumberDto.getId());

        assertEquals(phoneNumberDto, foundPhoneNumberDto);
    }

    @Test
    public void testFindByIdNotFound() {
        when(phoneNumberRepositoryDao.findById(phoneNumberDto.getId())).thenReturn(Optional.empty());

        Exception exception = assertThrows(NotFoundException.class, () -> {
            phoneNumberService.findById(phoneNumberDto.getId());
        });

        assertEquals("Department not found.", exception.getMessage());
    }

    @Test
    public void testFindAll() {
        List<PhoneNumber> phoneNumbers = new ArrayList<>();
        phoneNumbers.add(phoneNumber);
        when(phoneNumberRepositoryDao.findAll()).thenReturn(phoneNumbers);
        when(phoneNumberMapper.mapToListToDto(phoneNumbers)).thenReturn(List.of(phoneNumberDto));

        List<PhoneNumberDto> result = phoneNumberService.findAll();

        assertEquals(1, result.size());
        assertEquals(phoneNumberDto, result.get(0));
    }

    @Test
    public void testDelete() {
        phoneNumberService.delete(phoneNumberDto.getId());
        verify(phoneNumberRepositoryDao).delete(phoneNumberDto.getId());
    }
}
