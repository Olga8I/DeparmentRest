package org.example.repository.dao;

import org.example.exception.NotFoundException;
import org.example.model.PhoneNumber;
import org.example.repository.PhoneNumberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PhoneNumberRepositoryDao {
    private final PhoneNumberRepository phoneNumberRepository;

    @Autowired
    public PhoneNumberRepositoryDao (PhoneNumberRepository phoneNumberRepository){
        this.phoneNumberRepository = phoneNumberRepository;
    }

    public void add(PhoneNumber phoneNumber){
        phoneNumberRepository.save(phoneNumber);
    }

    public void update(PhoneNumber phoneNumber) {
        phoneNumberRepository.save(phoneNumber);
    }


    public void delete(Long id) {
        phoneNumberRepository.deleteById(id);
    }
    public Optional<PhoneNumber> findById(Long id) {
        return Optional.ofNullable(phoneNumberRepository.getReferenceById(id));
    }

    public List<PhoneNumber> findAll(){
        return phoneNumberRepository.findAll();
    }

    public boolean existsByNumber(String number) {
        return phoneNumberRepository.existsByNumber(number);
    }
    public boolean  existsById (Long id){ return phoneNumberRepository.existsById(id);}

    public PhoneNumber findByNumber(String number) throws NotFoundException {
        Optional<PhoneNumber> phoneNumber = phoneNumberRepository.findByNumber(number);
        if (phoneNumber.isPresent()){
            return phoneNumber.get();
        }
        else throw new NotFoundException("Phone number not found");
    }

}
