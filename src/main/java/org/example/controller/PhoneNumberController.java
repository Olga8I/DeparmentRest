package org.example.controller;

import org.example.dto.PhoneNumberDto;
import org.example.exception.NotFoundException;
import org.example.service.PhoneNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/phoneNumbers")
public class PhoneNumberController {

    private final PhoneNumberService phoneNumberService;

    @Autowired
    public PhoneNumberController(PhoneNumberService phoneNumberService) {
        this.phoneNumberService = phoneNumberService;
    }

    @PostMapping
    public ResponseEntity<PhoneNumberDto> create(@RequestBody PhoneNumberDto phoneNumberDto) {
        phoneNumberService.save(phoneNumberDto);
        return new ResponseEntity<>(phoneNumberDto, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PhoneNumberDto> update(@PathVariable Long id, @RequestBody PhoneNumberDto phoneNumberDto) {
        phoneNumberDto.setId(id);
        try {
            phoneNumberService.update(phoneNumberDto);
            return new ResponseEntity<>(phoneNumberDto, HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<PhoneNumberDto> getById(@PathVariable Long id) {
        try {
            PhoneNumberDto phoneNumberDto = phoneNumberService.findById(id);
            return new ResponseEntity<>(phoneNumberDto, HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<PhoneNumberDto>> getAll() {
        List<PhoneNumberDto> phoneNumbers = phoneNumberService.findAll();
        return new ResponseEntity<>(phoneNumbers, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        phoneNumberService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
