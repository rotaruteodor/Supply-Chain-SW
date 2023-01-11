package bGroup.SupplyChainSWbackend.mappers;

import bGroup.SupplyChainSWbackend.dtos.CompanyDto;
import bGroup.SupplyChainSWbackend.entities.Company;
import org.mapstruct.Mapper;

@Mapper
public interface CompanyMapper {
    CompanyDto toDto(Company company);
}
