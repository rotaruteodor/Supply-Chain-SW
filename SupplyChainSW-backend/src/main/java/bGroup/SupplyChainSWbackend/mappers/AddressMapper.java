package bGroup.SupplyChainSWbackend.mappers;

import bGroup.SupplyChainSWbackend.dtos.AddressDto;
import bGroup.SupplyChainSWbackend.entities.Address;
import org.mapstruct.Mapper;

@Mapper
public interface AddressMapper {
    AddressDto toDto(Address address);
}
