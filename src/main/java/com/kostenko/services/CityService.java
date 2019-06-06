package com.kostenko.services;

import com.kostenko.models.City;
import com.kostenko.repositories.CityRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {

    private CityRepository cityRepository;

    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public City save(City city) {
        return cityRepository.save(city);
    }

    public City findById(Long id)  {
        return cityRepository.findById(id).orElse(null);
    }

    public List<City> findAll() {
        return cityRepository.findAll();
    }
}
