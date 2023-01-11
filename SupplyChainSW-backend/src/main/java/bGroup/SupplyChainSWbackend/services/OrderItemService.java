package bGroup.SupplyChainSWbackend.services;

import bGroup.SupplyChainSWbackend.dtos.OrderItemDto;
import bGroup.SupplyChainSWbackend.entities.OrderItem;
import bGroup.SupplyChainSWbackend.exceptions.ErrorMessage;
import bGroup.SupplyChainSWbackend.exceptions.ResourceNotFoundException;
import bGroup.SupplyChainSWbackend.mappers.OrderItemMapper;
import bGroup.SupplyChainSWbackend.repositories.OrderItemRepository;
import bGroup.SupplyChainSWbackend.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class OrderItemService {

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderItemMapper orderItemMapper;

    public List<OrderItemDto> findAll() {
        return orderItemRepository.findAll()
                .stream()
                .map(orderItem -> orderItemMapper.toDto(orderItem))
                .toList();
    }

    public ResponseEntity<OrderItemDto> findById(@PathVariable Long id) {
        return orderItemRepository.findById(id)
                .map(orderItem -> orderItemMapper.toDto(orderItem))
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorMessage.getDoesNotExistErrorMessage(
                        "OrderItem", id)));
    }

    public ResponseEntity<OrderItemDto> add(@RequestBody OrderItem orderItem, @PathVariable Long productId) {
        productRepository.findById(productId)
                .map(product -> {
                    orderItem.setProduct(product);
                    return product;
                })
                .orElseThrow(() -> new ResourceNotFoundException(
                        ErrorMessage.getDoesNotExistErrorMessage("Product", productId)));

        orderItemRepository.save(orderItem);
        return ResponseEntity.ok(orderItemMapper.toDto(orderItem));
    }
}

