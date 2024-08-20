package org.example.controller;

import org.example.dto.PhoneNumberCreateDto;
import org.example.dto.PhoneNumberResponseDto;
import org.example.dto.PhoneNumberUpdateDto;
import org.example.service.PhoneNumberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/phone_numbers")
@Validated
public class PhoneNumberController {

    private final PhoneNumberService phoneNumberService;

    public PhoneNumberController(PhoneNumberService phoneNumberService) {
        this.phoneNumberService = phoneNumberService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PhoneNumberResponseDto> getPhoneNumberById(@PathVariable Long id) {
        PhoneNumberResponseDto phoneNumberResponseDto = phoneNumberService.findById(id);
        return ResponseEntity.ok(phoneNumberResponseDto);
    }

    @GetMapping("/all")
    public ResponseEntity<List<PhoneNumberResponseDto>> getAllPhoneNumbers() {
        List<PhoneNumberResponseDto> phoneNumbers = phoneNumberService.findAll();
        return ResponseEntity.ok(phoneNumbers);
    }

    @PostMapping
    public ResponseEntity<String> createPhoneNumber(@RequestBody PhoneNumberCreateDto phoneNumberCreateDto) {
        phoneNumberService.save(phoneNumberCreateDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Phone number " + phoneNumberCreateDto.getNumber() + " was created");
    }

    @PutMapping
    public ResponseEntity<String> updatePhoneNumber(@RequestBody PhoneNumberUpdateDto phoneNumberUpdateDto) {
        phoneNumberService.update(phoneNumberUpdateDto);
        return ResponseEntity.ok("Phone number " + phoneNumberUpdateDto.getId() + " was updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePhoneNumber(@PathVariable Long id) {
        phoneNumberService.delete(id);
        return ResponseEntity.ok("Phone number " + id + " was deleted");
    }
}
