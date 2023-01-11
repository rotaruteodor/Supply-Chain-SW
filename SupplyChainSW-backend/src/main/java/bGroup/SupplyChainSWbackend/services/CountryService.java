package bGroup.SupplyChainSWbackend.services;

import bGroup.SupplyChainSWbackend.mappers.AddressMapper;
import bGroup.SupplyChainSWbackend.mappers.CountryMapper;
import bGroup.SupplyChainSWbackend.repositories.AddressRepository;
import bGroup.SupplyChainSWbackend.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CountryService {

    @Autowired
    private CountryRepository repository;

    @Autowired
    private CountryMapper mapper;
}
