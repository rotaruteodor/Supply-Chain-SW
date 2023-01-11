package bGroup.SupplyChainSWbackend.dtos;

import bGroup.SupplyChainSWbackend.entities.Company;
import bGroup.SupplyChainSWbackend.entities.OrderItem;
import bGroup.SupplyChainSWbackend.entities.Shipment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDto {

    private Long id;
    private LocalDateTime creationDate;
    private Company company;
    List<OrderItem> items;
    private Shipment shipment;
}
