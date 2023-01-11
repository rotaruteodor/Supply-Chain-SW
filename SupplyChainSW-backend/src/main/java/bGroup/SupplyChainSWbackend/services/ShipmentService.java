package bGroup.SupplyChainSWbackend.services;

import bGroup.SupplyChainSWbackend.dtos.EmployeeDto;
import bGroup.SupplyChainSWbackend.dtos.ShipmentDto;
import bGroup.SupplyChainSWbackend.entities.Employee;
import bGroup.SupplyChainSWbackend.entities.Shipment;
import bGroup.SupplyChainSWbackend.exceptions.ErrorMessage;
import bGroup.SupplyChainSWbackend.exceptions.ResourceNotFoundException;
import bGroup.SupplyChainSWbackend.mappers.ShipmentMapper;
import bGroup.SupplyChainSWbackend.repositories.MeanOfTransportRepository;
import bGroup.SupplyChainSWbackend.repositories.OrderRepository;
import bGroup.SupplyChainSWbackend.repositories.ShipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class ShipmentService {

    @Autowired
    private ShipmentRepository shipmentRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private MeanOfTransportRepository meanOfTransportRepository;

    @Autowired
    private ShipmentMapper shipmentMapper;

    public List<ShipmentDto> findAll() {
        return shipmentRepository.findAll()
                .stream()
                .map(shipment -> shipmentMapper.toDto(shipment))
                .toList();
    }

    public ResponseEntity<ShipmentDto> findById(@PathVariable Long id) {
        return shipmentRepository.findById(id)
                .map(shipment -> shipmentMapper.toDto(shipment))
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorMessage.getDoesNotExistErrorMessage(
                        "Shipment", id)));
    }

    public ResponseEntity<ShipmentDto> add(@RequestBody Shipment shipment,
                                           @PathVariable Long orderId,
                                           @PathVariable Long meanOfTransportId) {
        orderRepository.findById(orderId)
                .map(order -> {
                    shipment.setOrder(order);
                    return order;
                })
                .orElseThrow(() -> new ResourceNotFoundException(
                        ErrorMessage.getDoesNotExistErrorMessage("Order", orderId)));

        meanOfTransportRepository.findById(meanOfTransportId)
                .map(meanOfTransport -> {
                    shipment.setMeanOfTransport(meanOfTransport);
                    return meanOfTransport;
                })
                .orElseThrow(() -> new ResourceNotFoundException(
                        ErrorMessage.getDoesNotExistErrorMessage("MeanOfTransport", meanOfTransportId)));

        shipmentRepository.save(shipment);
        return ResponseEntity.ok(shipmentMapper.toDto(shipment));
    }
}
