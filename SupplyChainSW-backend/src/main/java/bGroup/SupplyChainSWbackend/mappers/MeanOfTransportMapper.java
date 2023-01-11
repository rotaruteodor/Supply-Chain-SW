package bGroup.SupplyChainSWbackend.mappers;

import bGroup.SupplyChainSWbackend.dtos.MeanOfTransportDto;
import bGroup.SupplyChainSWbackend.entities.MeanOfTransport;
import org.mapstruct.Mapper;

@Mapper
public interface MeanOfTransportMapper {
    MeanOfTransportDto toDto(MeanOfTransport meanOfTransport);
}
