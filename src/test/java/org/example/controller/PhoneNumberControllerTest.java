package org.example.controller;
import org.example.dto.PhoneNumberCreateDto;
import org.example.dto.PhoneNumberResponseDto;
import org.example.dto.PhoneNumberUpdateDto;
import org.example.service.PhoneNumberService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

class PhoneNumberControllerTest {

    @InjectMocks
    private PhoneNumberController phoneNumberController;

    @Mock
    private PhoneNumberService phoneNumberService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetPhoneNumberById() {
        PhoneNumberResponseDto phoneNumberResponseDto = new PhoneNumberResponseDto("123456789", null);
        phoneNumberResponseDto.setId(1L);

        when(phoneNumberService.findById(1L)).thenReturn(phoneNumberResponseDto);

        PhoneNumberResponseDto phoneNumber = phoneNumberController.getPhoneNumberById(1L);
        Assertions.assertNotNull(phoneNumber);
        Assertions.assertEquals(phoneNumberResponseDto.getId(), phoneNumber.getId());
        Assertions.assertEquals(phoneNumberResponseDto.getNumberDto(), phoneNumber.getNumberDto());
    }

    @Test
    void testGetAllPhoneNumbers() {
        List<PhoneNumberResponseDto> phoneNumbers = new ArrayList<>();
        phoneNumbers.add(new PhoneNumberResponseDto("123456789", null));
        phoneNumbers.add(new PhoneNumberResponseDto("987654321", null));

        when(phoneNumberService.findAll()).thenReturn(phoneNumbers);

        List<PhoneNumberResponseDto> responseDtoList = phoneNumberController.getAllPhoneNumbers();
        Assertions.assertNotNull(responseDtoList);
        Assertions.assertEquals(2, responseDtoList.size());
    }

    @Test
    void testCreatePhoneNumber() {
        PhoneNumberCreateDto phoneNumberCreateDto = new PhoneNumberCreateDto("123456789");

        String result = phoneNumberController.createPhoneNumber(phoneNumberCreateDto);

        Assertions.assertEquals("Phone number 123456789 was created", result);
        verify(phoneNumberService, times(1)).save(phoneNumberCreateDto);
    }

    @Test
    void testUpdatePhoneNumber() {
        PhoneNumberUpdateDto phoneNumberUpdateDto = new PhoneNumberUpdateDto(1L, "987654321");

        String result = phoneNumberController.updatePhoneNumber(phoneNumberUpdateDto);

        Assertions.assertEquals("Phone number 1 was updated", result);
        verify(phoneNumberService, times(1)).update(phoneNumberUpdateDto);
    }

    @Test
    void testDeletePhoneNumber() {
        Long phoneNumberId = 1L;

        String result = phoneNumberController.deletePhoneNumber(phoneNumberId);

        Assertions.assertEquals("Phone number 1 was deleted", result);
        verify(phoneNumberService, times(1)).delete(phoneNumberId);
    }
}
