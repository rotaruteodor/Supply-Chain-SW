package bGroup.SupplyChainSWbackend.mappers;

import bGroup.SupplyChainSWbackend.dtos.CountryDto;
import bGroup.SupplyChainSWbackend.entities.Country;
import org.mapstruct.Mapper;

@Mapper
public interface CountryMapper {
    CountryDto toDto(Country country);
}
