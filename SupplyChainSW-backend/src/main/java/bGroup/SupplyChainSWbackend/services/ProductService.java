package bGroup.SupplyChainSWbackend.services;

import bGroup.SupplyChainSWbackend.dtos.AddressDto;
import bGroup.SupplyChainSWbackend.dtos.ProductDto;
import bGroup.SupplyChainSWbackend.entities.Address;
import bGroup.SupplyChainSWbackend.entities.Product;
import bGroup.SupplyChainSWbackend.exceptions.ErrorMessage;
import bGroup.SupplyChainSWbackend.exceptions.ResourceNotFoundException;
import bGroup.SupplyChainSWbackend.mappers.AddressMapper;
import bGroup.SupplyChainSWbackend.mappers.ProductMapper;
import bGroup.SupplyChainSWbackend.repositories.AddressRepository;
import bGroup.SupplyChainSWbackend.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Autowired
    private ProductMapper mapper;

    public List<ProductDto> findAll() {
        return repository.findAll()
                .stream()
                .map(product -> mapper.toDto(product))
                .toList();
    }

    public ResponseEntity<ProductDto> findById(@PathVariable Long id) {
        return repository.findById(id)
                .map(product -> mapper.toDto(product))
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorMessage.getDoesNotExistErrorMessage(
                        "Product", id)));
    }

    public ResponseEntity<ProductDto> add(@RequestBody Product product) {
        repository.save(product);
        return ResponseEntity.ok(mapper.toDto(product));
    }
}
