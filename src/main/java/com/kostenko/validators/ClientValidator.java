package com.kostenko.validators;

import com.kostenko.exceptions.ValidationException;
import com.kostenko.models.Client;
import com.kostenko.models.PhoneNumber;
import com.kostenko.services.ClientService;
import com.kostenko.services.PhoneNumberService;
import org.springframework.stereotype.Component;

@Component
public class ClientValidator {

    private ClientService clientService;
    private PhoneNumberService phoneNumberService;

    public ClientValidator(ClientService clientService, PhoneNumberService phoneNumberService) {
        this.clientService = clientService;
        this.phoneNumberService = phoneNumberService;
    }

    public void validateClientPhoneNumbersExist(Client client) throws ValidationException {
        for (PhoneNumber phoneNumber : client.getPhoneNumbers()) {
            validateClientPhoneNumberExist(phoneNumber.getNumber());
        }
    }

    public void validateClientPhoneNumberExist(String number) throws ValidationException {
        if (phoneNumberService.existsByNumber(number)) {
            throw new ValidationException(String.format("Phone number %s already exist", number));
        }
    }

    public void validatePhoneNumberDoesNotExist(String number) throws ValidationException {
        if (!phoneNumberService.existsByNumber(number)) {
            throw new ValidationException(String.format("Phone number %s does not exist", number));
        }
    }

    public void validateClientDoesNotExist(Long clientId) throws ValidationException {
        if (!clientService.existsById(clientId)) {
            throw new ValidationException(String.format("Client with id %s does not exist", clientId));
        }
    }
}
