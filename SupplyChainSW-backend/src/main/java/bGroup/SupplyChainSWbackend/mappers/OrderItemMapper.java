package bGroup.SupplyChainSWbackend.mappers;

import bGroup.SupplyChainSWbackend.dtos.OrderItemDto;
import bGroup.SupplyChainSWbackend.entities.OrderItem;
import org.mapstruct.Mapper;

@Mapper
public interface OrderItemMapper {
    OrderItemDto toDto(OrderItem orderItem);
}
