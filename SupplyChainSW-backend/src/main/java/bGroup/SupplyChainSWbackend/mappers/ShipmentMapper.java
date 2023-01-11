package bGroup.SupplyChainSWbackend.mappers;

import bGroup.SupplyChainSWbackend.dtos.ShipmentDto;
import bGroup.SupplyChainSWbackend.entities.Shipment;
import org.mapstruct.Mapper;

@Mapper
public interface ShipmentMapper {
    ShipmentDto toDto(Shipment shipment);
}
