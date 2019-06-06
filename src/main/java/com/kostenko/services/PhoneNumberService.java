package com.kostenko.services;

import com.kostenko.models.PhoneNumber;
import com.kostenko.repositories.PhoneNumberRepository;
import org.springframework.stereotype.Service;

@Service
public class PhoneNumberService {

    private PhoneNumberRepository phoneNumberRepository;

    public PhoneNumberService(PhoneNumberRepository phoneNumberRepository) {
        this.phoneNumberRepository = phoneNumberRepository;
    }

    public PhoneNumber save(PhoneNumber phoneNumber) {
        return phoneNumberRepository.save(phoneNumber);
    }

    public PhoneNumber findByNumber(String number) {
        return phoneNumberRepository.findByNumber(number);
    }

    public boolean existsByNumber(String number) {
        return phoneNumberRepository.existsByNumber(number);
    }
}
