package com.kostenko.services;

import com.kostenko.models.Call;
import com.kostenko.models.CallStatisticsByCities;
import com.kostenko.repositories.CallRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
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

    public List<CallStatisticsByCities> getNumberOfCalls() {
        return callRepository.getNumberOfCalls();
    }

    public Call findLongestCall(Long clientId, Date start, Date end) {
        return callRepository.findFirstByClient_IdAndDateBetweenOrderByDurationDesc(clientId, start, end);
    }
}
