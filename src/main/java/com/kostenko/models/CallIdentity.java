package com.kostenko.models;

import javax.validation.constraints.NotNull;
import java.time.Duration;
import java.util.Date;

public class CallIdentity {

    @NotNull
    private String callersPhoneNumber;
    @NotNull
    private String recipientsPhoneNumber;
    @NotNull
    private Date date;
    @NotNull
    private Duration duration;
    @NotNull
    private String city;

    public String getCallersPhoneNumber() {
        return callersPhoneNumber;
    }

    public void setCallersPhoneNumber(String callersPhoneNumber) {
        this.callersPhoneNumber = callersPhoneNumber;
    }

    public String getRecipientsPhoneNumber() {
        return recipientsPhoneNumber;
    }

    public void setRecipientsPhoneNumber(String recipientsPhoneNumber) {
        this.recipientsPhoneNumber = recipientsPhoneNumber;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
