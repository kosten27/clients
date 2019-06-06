package com.kostenko.controllers;

import com.kostenko.models.Client;
import com.kostenko.models.PhoneNumber;
import com.kostenko.services.ClientService;
import com.kostenko.services.PhoneNumberService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ClientController {

    private ClientService clientService;
    private PhoneNumberService phoneNumberService;

    public ClientController(ClientService clientService, PhoneNumberService phoneNumberService) {
        this.clientService = clientService;
        this.phoneNumberService = phoneNumberService;
    }

    @GetMapping("/clients")
    public List<Client> getAllClients() {
        return clientService.findAll();
    }

    @PostMapping("/clients")
    public Client createClient(@Valid @RequestBody Client client) {
        return clientService.save(client);
    }

    @PostMapping("clients/{clientId}/phone_numbers")
    public PhoneNumber createPhoneNumber(@PathVariable Long clientId, @Valid @RequestBody PhoneNumber phoneNumber) {
        if (phoneNumberService.existsByNumber(phoneNumber.getNumber())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Phone number already exists");
        } else {
            Client client = clientService.findById(clientId);
            phoneNumber.setClient(client);
            return phoneNumberService.save(phoneNumber);
        }
    }

//    @PutMapping("/clients/{id}")
//    public Client updateClient(@PathVariable("id") Long id, @Valid @RequestBody Client clientDetails) {
//        Client client = clientService.findById(id);
//        client.setFirstName(clientDetails.getFirstName());
//        client.setLastName(clientDetails.getLastName());
//        client.setBirthday(clientDetails.getBirthday());
//        client.setGender(clientDetails.getGender());
//        return clientService.save(client);
//    }

}
