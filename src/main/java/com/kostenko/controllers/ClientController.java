package com.kostenko.controllers;

import com.kostenko.dto.CallDto;
import com.kostenko.exceptions.ValidationException;
import com.kostenko.models.Call;
import com.kostenko.models.Client;
import com.kostenko.models.PhoneNumber;
import com.kostenko.services.CallService;
import com.kostenko.services.ClientService;
import com.kostenko.services.PhoneNumberService;
import com.kostenko.validators.ClientValidator;
import org.modelmapper.ModelMapper;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
public class ClientController {

    private ClientService clientService;
    private PhoneNumberService phoneNumberService;
    private CallService callService;
    private ModelMapper modelMapper;
    private ClientValidator clientValidator;

    public ClientController(ClientService clientService, PhoneNumberService phoneNumberService,
                            CallService callService, ClientValidator clientValidator) {
        this.clientService = clientService;
        this.phoneNumberService = phoneNumberService;
        this.callService = callService;
        this.modelMapper = new ModelMapper();
        this.clientValidator = clientValidator;
    }

    @GetMapping("/clients")
    public List<Client> getAllClients() {
        return clientService.findAll();
    }

    @PostMapping("/clients")
    @ResponseStatus(HttpStatus.CREATED)
    public Client createClient(@Valid @RequestBody Client client) {
        try {
            clientValidator.validateClientPhoneNumbersExist(client);
        } catch (ValidationException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
        return clientService.save(client);
    }

    @PostMapping("clients/{clientId}/phone_numbers")
    @ResponseStatus(HttpStatus.CREATED)
    public PhoneNumber createPhoneNumber(@PathVariable Long clientId, @Valid @RequestBody PhoneNumber phoneNumber) {
        try {
            clientValidator.validateClientDoesNotExist(clientId);
        } catch (ValidationException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
        try {
            clientValidator.validateClientPhoneNumberExist(phoneNumber.getNumber());
        } catch (ValidationException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
        Client client = clientService.findById(clientId);
        phoneNumber.setClient(client);
        return phoneNumberService.save(phoneNumber);
    }

    @GetMapping("/clients/{clientId}/longest_call")
    public Call getLongestCall(@PathVariable Long clientId,
                               @RequestParam("start") @DateTimeFormat(pattern = "yyyy-MM-dd") Date start,
                               @RequestParam("end") @DateTimeFormat(pattern = "yyyy-MM-dd") Date end) {
        try {
            clientValidator.validateClientDoesNotExist(clientId);
        } catch (ValidationException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
        end.setTime(end.getTime() + 86400000 - 1);
        return callService.findLongestCall(clientId, start, end);

    }

    @PostMapping("/clients/{clientId}/calls")
    @ResponseStatus(HttpStatus.CREATED)
    public Call createCall(@PathVariable Long clientId,
                           @Valid @RequestBody CallDto callDto) {
        try {
            clientValidator.validateClientDoesNotExist(clientId);
            clientValidator.validatePhoneNumberDoesNotExist(callDto.getCallersPhoneNumber());
            clientValidator.validatePhoneNumberDoesNotExist(callDto.getRecipientsPhoneNumber());
        } catch (ValidationException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
        Call call = modelMapper.map(callDto, Call.class);
        call.setClient(clientService.findById(clientId));
        callService.save(call);
        return callService.save(call);
    }
}
