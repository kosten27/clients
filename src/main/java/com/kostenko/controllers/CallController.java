package com.kostenko.controllers;

import com.kostenko.models.Call;
import com.kostenko.models.CallIdentity;
import com.kostenko.services.CallService;
import com.kostenko.services.CityService;
import com.kostenko.services.PhoneNumberService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class CallController {

    private CallService callService;
    private PhoneNumberService phoneNumberService;
    private CityService cityService;

    public CallController(CallService callService, PhoneNumberService phoneNumberService, CityService cityService) {
        this.callService = callService;
        this.phoneNumberService = phoneNumberService;
        this.cityService = cityService;
    }

    @PostMapping("/calls")
    public Call createCall(@Valid @RequestBody CallIdentity callIdentity) {
        Call call = new Call();
        call.setCallersPhoneNumber(phoneNumberService.findByNumber(callIdentity.getCallersPhoneNumber()));
        call.setRecipientsPhoneNumber(phoneNumberService.findByNumber(callIdentity.getRecipientsPhoneNumber()));
        call.setDate(callIdentity.getDate());
        call.setDuration(callIdentity.getDuration());
        call.setCity(cityService.findById(callIdentity.getCityId()));
        callService.save(call);
        return callService.save(call);
    }

    @GetMapping("/calls")
    public List<Call> getAllCalls() {
        return callService.findAll();
    }
}
