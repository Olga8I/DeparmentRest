package org.example.controller;

import org.example.dto.PhoneNumberCreateDto;
import org.example.dto.PhoneNumberResponseDto;
import org.example.dto.PhoneNumberUpdateDto;
import org.example.service.PhoneNumberService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PhoneNumberController {

    private final PhoneNumberService phoneNumberService;

    public PhoneNumberController(PhoneNumberService phoneNumberService) {
        this.phoneNumberService = phoneNumberService;
    }

    @GetMapping(value = "/phone_numbers/{id}")
    public PhoneNumberResponseDto getPhoneNumberById(@PathVariable("id") Long id) {
        return phoneNumberService.findById(id);
    }

    @GetMapping(value = "/phone_numbers/all")
    public List<PhoneNumberResponseDto> getAllPhoneNumbers() {
        return phoneNumberService.findAll();
    }

    @PostMapping(value = "/phone_numbers")
    public String createPhoneNumber(@RequestBody PhoneNumberCreateDto phoneNumberCreateDto) {
        phoneNumberService.save(phoneNumberCreateDto);
        return "Phone number " + phoneNumberCreateDto.getNumber() + " was created";
    }

    @PutMapping(value = "/phone_numbers")
    public String updatePhoneNumber(@RequestBody PhoneNumberUpdateDto phoneNumberUpdateDto) {
        phoneNumberService.update(phoneNumberUpdateDto);
        return "Phone number " + phoneNumberUpdateDto.getId() + " was updated";
    }

    @DeleteMapping("/phone_numbers/{id}")
    public String deletePhoneNumber(@PathVariable("id") Long id) {
        phoneNumberService.delete(id);
        return "Phone number " + id + " was deleted";
    }
}

