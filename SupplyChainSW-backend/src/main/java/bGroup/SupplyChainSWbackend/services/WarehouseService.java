package bGroup.SupplyChainSWbackend.services;

import bGroup.SupplyChainSWbackend.dtos.WarehouseDto;
import bGroup.SupplyChainSWbackend.entities.Warehouse;
import bGroup.SupplyChainSWbackend.exceptions.ErrorMessage;
import bGroup.SupplyChainSWbackend.exceptions.ResourceNotFoundException;
import bGroup.SupplyChainSWbackend.mappers.WarehouseMapper;
import bGroup.SupplyChainSWbackend.repositories.AddressRepository;
import bGroup.SupplyChainSWbackend.repositories.CompanyRepository;
import bGroup.SupplyChainSWbackend.repositories.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class WarehouseService {

    @Autowired
    private WarehouseRepository warehouseRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private WarehouseMapper warehouseMapper;

    public List<WarehouseDto> findAll() {
        return warehouseRepository.findAll()
                .stream()
                .map(warehouse -> warehouseMapper.toDto(warehouse))
                .toList();
    }

    public ResponseEntity<WarehouseDto> findById(@PathVariable Long id) {
        return warehouseRepository.findById(id)
                .map(warehouse -> warehouseMapper.toDto(warehouse))
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorMessage.getDoesNotExistErrorMessage(
                        "Warehouse", id)));
    }

    public ResponseEntity<WarehouseDto> add(@RequestBody Warehouse warehouse,
                                            @PathVariable Long companyId,
                                            @PathVariable Long addressId) {
        companyRepository.findById(companyId)
                .map(company -> {
                    warehouse.setCompany(company);
                    return company;
                })
                .orElseThrow(() -> new ResourceNotFoundException(
                        ErrorMessage.getDoesNotExistErrorMessage("Company", companyId)));

        addressRepository.findById(addressId)
                .map(address -> {
                    warehouse.setAddress(address);
                    return address;
                })
                .orElseThrow(() -> new ResourceNotFoundException(
                        ErrorMessage.getDoesNotExistErrorMessage("Address", addressId)));

        warehouseRepository.save(warehouse);
        return ResponseEntity.ok(warehouseMapper.toDto(warehouse));
    }
}
