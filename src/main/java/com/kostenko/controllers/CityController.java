package com.kostenko.controllers;

import com.kostenko.models.City;
import com.kostenko.services.CityService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class CityController {

    private CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping("/cities")
    public List<City> getAllCities() {
        return cityService.findAll();
    }

    @PostMapping("/cities")
    public City save(@Valid @RequestBody City city) {
        return cityService.save(city);
    }
}
