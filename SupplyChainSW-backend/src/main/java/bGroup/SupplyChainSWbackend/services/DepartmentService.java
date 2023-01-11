package bGroup.SupplyChainSWbackend.services;

import bGroup.SupplyChainSWbackend.dtos.AddressDto;
import bGroup.SupplyChainSWbackend.dtos.DepartmentDto;
import bGroup.SupplyChainSWbackend.entities.Address;
import bGroup.SupplyChainSWbackend.entities.Department;
import bGroup.SupplyChainSWbackend.exceptions.ErrorMessage;
import bGroup.SupplyChainSWbackend.exceptions.ResourceNotFoundException;
import bGroup.SupplyChainSWbackend.mappers.AddressMapper;
import bGroup.SupplyChainSWbackend.mappers.DepartmentMapper;
import bGroup.SupplyChainSWbackend.repositories.AddressRepository;
import bGroup.SupplyChainSWbackend.repositories.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository repository;

    @Autowired
    private DepartmentMapper mapper;

    public List<DepartmentDto> findAll() {
        return repository.findAll()
                .stream()
                .map(department -> mapper.toDto(department))
                .toList();
    }

    public ResponseEntity<DepartmentDto> findById(@PathVariable Long id) {
        return repository.findById(id)
                .map(department -> mapper.toDto(department))
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorMessage.getDoesNotExistErrorMessage(
                        "Department", id)));
    }

    public ResponseEntity<DepartmentDto> add(@RequestBody Department department) {
        repository.save(department);
        return ResponseEntity.ok(mapper.toDto(department));
    }
}
