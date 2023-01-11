package bGroup.SupplyChainSWbackend.dtos;

import bGroup.SupplyChainSWbackend.entities.MeanOfTransport;
import bGroup.SupplyChainSWbackend.entities.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShipmentDto {

    private Long id;
    private Order order;
    private LocalDateTime departureDate;
    private LocalDateTime deliverDate;
    private MeanOfTransport meanOfTransport;
}
