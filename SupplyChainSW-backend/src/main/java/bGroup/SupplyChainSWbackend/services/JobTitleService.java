package bGroup.SupplyChainSWbackend.services;

import bGroup.SupplyChainSWbackend.dtos.AddressDto;
import bGroup.SupplyChainSWbackend.dtos.JobTitleDto;
import bGroup.SupplyChainSWbackend.entities.Address;
import bGroup.SupplyChainSWbackend.entities.JobTitle;
import bGroup.SupplyChainSWbackend.exceptions.ErrorMessage;
import bGroup.SupplyChainSWbackend.exceptions.ResourceNotFoundException;
import bGroup.SupplyChainSWbackend.mappers.AddressMapper;
import bGroup.SupplyChainSWbackend.mappers.JobTitleMapper;
import bGroup.SupplyChainSWbackend.repositories.AddressRepository;
import bGroup.SupplyChainSWbackend.repositories.JobTitleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class JobTitleService {

    @Autowired
    private JobTitleRepository repository;

    @Autowired
    private JobTitleMapper mapper;

    public List<JobTitleDto> findAll() {
        return repository.findAll()
                .stream()
                .map(jobTitle -> mapper.toDto(jobTitle))
                .toList();
    }

    public ResponseEntity<JobTitleDto> findById(@PathVariable Long id) {
        return repository.findById(id)
                .map(jobTitle -> mapper.toDto(jobTitle))
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorMessage.getDoesNotExistErrorMessage(
                        "JobTitle", id)));
    }

    public ResponseEntity<JobTitleDto> add(@RequestBody JobTitle jobTitle) {
        repository.save(jobTitle);
        return ResponseEntity.ok(mapper.toDto(jobTitle));
    }
}
