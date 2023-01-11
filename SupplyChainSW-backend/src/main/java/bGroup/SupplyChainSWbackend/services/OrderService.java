package bGroup.SupplyChainSWbackend.services;

import bGroup.SupplyChainSWbackend.dtos.CompanyDto;
import bGroup.SupplyChainSWbackend.dtos.OrderDto;
import bGroup.SupplyChainSWbackend.entities.Company;
import bGroup.SupplyChainSWbackend.entities.Employee;
import bGroup.SupplyChainSWbackend.entities.Order;
import bGroup.SupplyChainSWbackend.entities.Warehouse;
import bGroup.SupplyChainSWbackend.exceptions.ErrorMessage;
import bGroup.SupplyChainSWbackend.exceptions.ResourceNotFoundException;
import bGroup.SupplyChainSWbackend.mappers.OrderMapper;
import bGroup.SupplyChainSWbackend.repositories.CompanyRepository;
import bGroup.SupplyChainSWbackend.repositories.OrderRepository;
import bGroup.SupplyChainSWbackend.repositories.ShipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private ShipmentRepository shipmentRepository;

    @Autowired
    private OrderMapper orderMapper;

    public List<OrderDto> findAll() {
        return orderRepository.findAll()
                .stream()
                .map(order -> orderMapper.toDto(order))
                .toList();
    }

    public ResponseEntity<OrderDto> findById(@PathVariable Long id) {
        return orderRepository.findById(id)
                .map(order -> orderMapper.toDto(order))
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorMessage.getDoesNotExistErrorMessage(
                        "Order", id)));
    }

    // todo after frontend
//    public ResponseEntity<OrderDto> add(@RequestBody Order order,
//                                          @PathVariable Long companyId,
//                                          @PathVariable Long shipmentId) {
//
//        companyRepository.findById(companyId)
//                .map(company -> {
//                    order.setCompany(company);
//                    return company;
//                })
//                .orElseThrow(() -> new ResourceNotFoundException(
//                        ErrorMessage.getDoesNotExistErrorMessage("Company", companyId)));
//
//        shipmentRepository.findById(shipmentId)
//                .map(shipment -> {
//                    order.setShipment(shipment);
//                    return shipment;
//                })
//                .orElseThrow(() -> new ResourceNotFoundException(
//                        ErrorMessage.getDoesNotExistErrorMessage("Shipment", shipmentId)));
//    }
}
