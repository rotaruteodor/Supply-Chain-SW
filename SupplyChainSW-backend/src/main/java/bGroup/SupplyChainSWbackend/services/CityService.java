package bGroup.SupplyChainSWbackend.services;

import bGroup.SupplyChainSWbackend.dtos.CityDto;
import bGroup.SupplyChainSWbackend.entities.City;
import bGroup.SupplyChainSWbackend.exceptions.ErrorMessage;
import bGroup.SupplyChainSWbackend.exceptions.ResourceNotFoundException;
import bGroup.SupplyChainSWbackend.mappers.CityMapper;
import bGroup.SupplyChainSWbackend.repositories.CityRepository;
import bGroup.SupplyChainSWbackend.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private CityMapper cityMapper;

    public List<CityDto> findAll() {
        return cityRepository.findAll()
                .stream()
                .map(city -> cityMapper.toDto(city))
                .toList();
    }

    public ResponseEntity<CityDto> findById(@PathVariable Long id) {
        return cityRepository.findById(id)
                .map(city -> cityMapper.toDto(city))
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorMessage.getDoesNotExistErrorMessage(
                        "City", id)));
    }

    public ResponseEntity<CityDto> add(@RequestBody City city, @PathVariable Long countryId) {
        countryRepository.findById(countryId)
                .map(country -> {
                    city.setCountry(country);
                    return country;
                })
                .orElseThrow(() -> new ResourceNotFoundException(
                        ErrorMessage.getDoesNotExistErrorMessage("Country", countryId)));

        cityRepository.save(city);
        return ResponseEntity.ok(cityMapper.toDto(city));
    }
}
