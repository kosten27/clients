package com.kostenko.services;

import com.kostenko.models.Call;
import com.kostenko.repositories.CallRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CallService {

    private CallRepository callRepository;

    public CallService(CallRepository callRepository) {
        this.callRepository = callRepository;
    }

    public Call save(Call call) {
        return callRepository.save(call);
    }

    public List<Call> findAllByClientId(Long clientId) {
        return callRepository.findAll();
    }

    public List<Call> findAll() {
        return callRepository.findAll();
    }
}
