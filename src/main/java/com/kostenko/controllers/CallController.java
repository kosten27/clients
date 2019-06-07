package com.kostenko.controllers;

import com.kostenko.models.CallStatisticsByCities;
import com.kostenko.services.CallService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CallController {

    private CallService callService;

    public CallController(CallService callService) {
        this.callService = callService;
    }

    @GetMapping("/calls")
    public List<CallStatisticsByCities> getNumberOfCalls() {
        return callService.getNumberOfCalls();
    }
}
