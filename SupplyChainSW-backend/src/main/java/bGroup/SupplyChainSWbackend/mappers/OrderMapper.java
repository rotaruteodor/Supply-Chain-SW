package bGroup.SupplyChainSWbackend.mappers;

import bGroup.SupplyChainSWbackend.dtos.OrderDto;
import bGroup.SupplyChainSWbackend.entities.Order;
import org.mapstruct.Mapper;

@Mapper
public interface OrderMapper {
    OrderDto toDto(Order order);
}
