package bGroup.SupplyChainSWbackend.mappers;

import bGroup.SupplyChainSWbackend.dtos.CityDto;
import bGroup.SupplyChainSWbackend.entities.City;
import org.mapstruct.Mapper;

@Mapper
public interface CityMapper {
    CityDto toDto(City city);
}
