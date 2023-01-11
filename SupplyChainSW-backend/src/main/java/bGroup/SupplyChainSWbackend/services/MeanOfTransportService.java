package bGroup.SupplyChainSWbackend.services;

import bGroup.SupplyChainSWbackend.dtos.BankAccountDto;
import bGroup.SupplyChainSWbackend.dtos.MeanOfTransportDto;
import bGroup.SupplyChainSWbackend.entities.BankAccount;
import bGroup.SupplyChainSWbackend.entities.MeanOfTransport;
import bGroup.SupplyChainSWbackend.exceptions.ErrorMessage;
import bGroup.SupplyChainSWbackend.exceptions.ResourceNotFoundException;
import bGroup.SupplyChainSWbackend.mappers.MeanOfTransportMapper;
import bGroup.SupplyChainSWbackend.repositories.AddressRepository;
import bGroup.SupplyChainSWbackend.repositories.MeanOfTransportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class MeanOfTransportService {

    @Autowired
    private MeanOfTransportRepository meanOfTransportRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private MeanOfTransportMapper meanOfTransportMapper;

    public List<MeanOfTransportDto> findAll() {
        return meanOfTransportRepository.findAll()
                .stream()
                .map(meanOfTransport -> meanOfTransportMapper.toDto(meanOfTransport))
                .toList();
    }

    public ResponseEntity<MeanOfTransportDto> findById(@PathVariable Long id) {
        return meanOfTransportRepository.findById(id)
                .map(meanOfTransport -> meanOfTransportMapper.toDto(meanOfTransport))
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorMessage.getDoesNotExistErrorMessage(
                        "MeanOfTransport", id)));
    }

    public ResponseEntity<MeanOfTransportDto> add(@RequestBody MeanOfTransport meanOfTransport, @PathVariable Long addressId) {
        addressRepository.findById(addressId)
                .map(address -> {
                    meanOfTransport.setAddress(address);
                    return address;
                })
                .orElseThrow(() -> new ResourceNotFoundException(
                        ErrorMessage.getDoesNotExistErrorMessage("Address", addressId)));

        meanOfTransportRepository.save(meanOfTransport);
        return ResponseEntity.ok(meanOfTransportMapper.toDto(meanOfTransport));
    }
}
