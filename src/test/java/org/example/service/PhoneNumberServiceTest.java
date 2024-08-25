package org.example.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.example.dto.PhoneNumberCreateDto;
import org.example.dto.PhoneNumberResponseDto;
import org.example.dto.PhoneNumberUpdateDto;
import org.example.exception.NotFoundException;
import org.example.mapper.PhoneNumberMapper;
import org.example.model.PhoneNumber;
import org.example.repository.PhoneNumberRepository;
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
    private PhoneNumberRepository phoneNumberRepository;

    private PhoneNumberCreateDto phoneNumberCreateDto;
    private PhoneNumberUpdateDto phoneNumberUpdateDto;
    private PhoneNumberResponseDto phoneNumberResponseDto;
    private PhoneNumber phoneNumber;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        phoneNumberCreateDto = new PhoneNumberCreateDto("123456789");

        phoneNumberUpdateDto = new PhoneNumberUpdateDto(1L, "987654321");

        phoneNumberResponseDto = new PhoneNumberResponseDto();
        phoneNumberResponseDto.setId(1L);
        phoneNumberResponseDto.setNumberDto("123456789");

        phoneNumber = new PhoneNumber();
        phoneNumber.setId(1L);
        phoneNumber.setNumber("123456789");
    }

    @Test
    public void testUpdateSuccess() throws NotFoundException {
        when(phoneNumberRepository.findById(phoneNumberUpdateDto.getId())).thenReturn(Optional.of(phoneNumber));
        when(phoneNumberMapper.mapToEntity(phoneNumberCreateDto)).thenReturn(phoneNumber);

        phoneNumberService.update(phoneNumberUpdateDto);

        verify(phoneNumberRepository).save(phoneNumber);
    }

    @Test
    public void testUpdateNotFound() {
        when(phoneNumberRepository.findById(phoneNumberUpdateDto.getId())).thenReturn(Optional.empty());

        Exception exception = assertThrows(NotFoundException.class, () -> {
            phoneNumberService.update(phoneNumberUpdateDto);
        });

        assertEquals("Phone number not found.", exception.getMessage());
    }

    @Test
    public void testFindByIdSuccess() throws NotFoundException {
        when(phoneNumberRepository.findById(phoneNumberResponseDto.getId())).thenReturn(Optional.of(phoneNumber));
        when(phoneNumberMapper.mapToDto(phoneNumber)).thenReturn(phoneNumberResponseDto);

        PhoneNumberResponseDto foundPhoneNumberResponseDto = phoneNumberService.findById(phoneNumberResponseDto.getId());

        assertEquals(phoneNumberResponseDto, foundPhoneNumberResponseDto);
    }

    @Test
    public void testFindByIdNotFound() {
        when(phoneNumberRepository.findById(phoneNumberResponseDto.getId())).thenReturn(Optional.empty());

        Exception exception = assertThrows(NotFoundException.class, () -> {
            phoneNumberService.findById(phoneNumberResponseDto.getId());
        });

        assertEquals("Phone number not found.", exception.getMessage());
    }

    @Test
    public void testFindAll() {
        List<PhoneNumber> phoneNumbers = new ArrayList<>();
        phoneNumbers.add(phoneNumber);
        when(phoneNumberRepository.findAll()).thenReturn(phoneNumbers);
        when(phoneNumberMapper.mapToDto(phoneNumber)).thenReturn(phoneNumberResponseDto);

        List<PhoneNumberResponseDto> result = phoneNumberService.findAll();

        assertEquals(1, result.size());
        assertEquals(phoneNumberResponseDto, result.get(0));
    }

    @Test
    public void testDelete() {
        doNothing().when(phoneNumberRepository).deleteById(phoneNumberResponseDto.getId());
        phoneNumberService.delete(phoneNumberResponseDto.getId());
        verify(phoneNumberRepository).deleteById(phoneNumberResponseDto.getId());
    }

    @Test
    public void testSave() {
        when(phoneNumberMapper.mapToEntity(phoneNumberCreateDto)).thenReturn(phoneNumber);
        phoneNumberService.save(phoneNumberCreateDto);
        verify(phoneNumberRepository).save(phoneNumber);
    }
}
