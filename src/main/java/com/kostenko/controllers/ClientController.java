package com.kostenko.controllers;

import com.kostenko.models.Call;
import com.kostenko.models.CallIdentity;
import com.kostenko.models.Client;
import com.kostenko.models.PhoneNumber;
import com.kostenko.services.CallService;
import com.kostenko.services.ClientService;
import com.kostenko.services.PhoneNumberService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@RestController
public class ClientController {

    private ClientService clientService;
    private PhoneNumberService phoneNumberService;
    private CallService callService;

    public ClientController(ClientService clientService, PhoneNumberService phoneNumberService, CallService callService) {
        this.clientService = clientService;
        this.phoneNumberService = phoneNumberService;
        this.callService = callService;
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

    @GetMapping("/clients/{clientId}/longest_call")
    public Call getLongestCall(@PathVariable Long clientId,
                               @RequestParam("start") @DateTimeFormat(pattern = "yyyy-MM-dd") Date start,
                               @RequestParam("end") @DateTimeFormat(pattern = "yyyy-MM-dd") Date end) {
        end.setTime(end.getTime() + 86400000 - 1);
        return callService.findLongestCall(clientId, start, end);

    }

    @PostMapping("/clients/{clientId}/calls")
    public Call createCall(@PathVariable Long clientId,
                           @Valid @RequestBody CallIdentity callIdentity) {
        Call call = new Call();
        call.setClient(clientService.findById(clientId));
        call.setCallersPhoneNumber(phoneNumberService.findByNumber(callIdentity.getCallersPhoneNumber()));
        call.setRecipientsPhoneNumber(phoneNumberService.findByNumber(callIdentity.getRecipientsPhoneNumber()));
        call.setDate(callIdentity.getDate());
        call.setDuration(callIdentity.getDuration());
        call.setCity(callIdentity.getCity());
        callService.save(call);
        return callService.save(call);
    }
}
