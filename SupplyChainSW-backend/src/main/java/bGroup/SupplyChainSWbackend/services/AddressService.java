package bGroup.SupplyChainSWbackend.services;

import bGroup.SupplyChainSWbackend.dtos.AddressDto;
import bGroup.SupplyChainSWbackend.entities.Address;
import bGroup.SupplyChainSWbackend.exceptions.ErrorMessage;
import bGroup.SupplyChainSWbackend.exceptions.ResourceNotFoundException;
import bGroup.SupplyChainSWbackend.mappers.AddressMapper;
import bGroup.SupplyChainSWbackend.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class AddressService {

    @Autowired
    private AddressRepository repository;

    @Autowired
    private AddressMapper mapper;

    public List<AddressDto> findAll() {
        return repository.findAll()
                .stream()
                .map(address -> mapper.toDto(address))
                .toList();
    }

    public ResponseEntity<AddressDto> findById(@PathVariable Long id) {
        return repository.findById(id)
                .map(address -> mapper.toDto(address))
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorMessage.getDoesNotExistErrorMessage(
                        "Address", id)));
    }

    public ResponseEntity<AddressDto> add(@RequestBody Address address) {
        repository.save(address);
        return ResponseEntity.ok(mapper.toDto(address));
    }
}
