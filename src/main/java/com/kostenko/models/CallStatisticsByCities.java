package com.kostenko.models;

public class CallStatisticsByCities {

    private String city;
    private Long numberOfCalls;

    public CallStatisticsByCities(String city, Long numberOfCalls) {
        this.city = city;
        this.numberOfCalls = numberOfCalls;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Long getNumberOfCalls() {
        return numberOfCalls;
    }

    public void setNumberOfCalls(Long numberOfCalls) {
        this.numberOfCalls = numberOfCalls;
    }
}
