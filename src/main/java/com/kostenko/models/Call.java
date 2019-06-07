package com.kostenko.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

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
    @JsonBackReference
    private Client client;
    @OneToOne
    @NotNull
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "number")
    @JsonIdentityReference(alwaysAsId = true)
    private PhoneNumber callersPhoneNumber;
    @OneToOne
    @NotNull
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "number")
    @JsonIdentityReference(alwaysAsId = true)
    private PhoneNumber recipientsPhoneNumber;
    @NotNull
    private Date date;
    @NotNull
    private Duration duration;
    @NotNull
    private String city;

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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
