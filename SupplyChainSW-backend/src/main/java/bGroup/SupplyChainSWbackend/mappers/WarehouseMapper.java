package bGroup.SupplyChainSWbackend.mappers;

import bGroup.SupplyChainSWbackend.dtos.WarehouseDto;
import bGroup.SupplyChainSWbackend.entities.Warehouse;
import org.mapstruct.Mapper;

@Mapper
public interface WarehouseMapper {
    WarehouseDto toDto(Warehouse warehouse);
}
