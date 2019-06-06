package com.kostenko.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import java.time.Duration;
import java.util.Date;

@Entity
public class Call {

    @Id
    @GeneratedValue
    private Long id;
    @OneToOne
    @NotNull
    private PhoneNumber callersPhoneNumber;
    @OneToOne
    @NotNull
    private PhoneNumber recipientsPhoneNumber;
    @NotNull
    private Date date;
    @NotNull
    private Duration duration;
    @OneToOne
    @NotNull
    private City city;

    public Call() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PhoneNumber getCallersPhoneNumber() {
        return callersPhoneNumber;
    }

    public void setCallersPhoneNumber(PhoneNumber callersPhoneNumber) {
        this.callersPhoneNumber = callersPhoneNumber;
    }

    public PhoneNumber getRecipientsPhoneNumber() {
        return recipientsPhoneNumber;
    }

    public void setRecipientsPhoneNumber(PhoneNumber recipientsPhoneNumber) {
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

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}
