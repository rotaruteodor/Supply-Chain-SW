package bGroup.SupplyChainSWbackend.services;

import bGroup.SupplyChainSWbackend.dtos.BankDto;
import bGroup.SupplyChainSWbackend.entities.Bank;
import bGroup.SupplyChainSWbackend.exceptions.ErrorMessage;
import bGroup.SupplyChainSWbackend.exceptions.ResourceNotFoundException;
import bGroup.SupplyChainSWbackend.mappers.AddressMapper;
import bGroup.SupplyChainSWbackend.mappers.BankMapper;
import bGroup.SupplyChainSWbackend.repositories.AddressRepository;
import bGroup.SupplyChainSWbackend.repositories.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class BankService {

    @Autowired
    private BankRepository repository;

    @Autowired
    private BankMapper mapper;

    public List<BankDto> findAll() {
        return repository.findAll()
                .stream()
                .map(bank -> mapper.toDto(bank))
                .toList();
    }

    public ResponseEntity<BankDto> findById(@PathVariable Long id) {
        return repository.findById(id)
                .map(bank -> mapper.toDto(bank))
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorMessage.getDoesNotExistErrorMessage(
                        "Bank", id)));
    }

    public ResponseEntity<BankDto> add(
            @RequestBody Bank bank) {
        repository.save(bank);
        return ResponseEntity.ok(mapper.toDto(bank));
    }
}
