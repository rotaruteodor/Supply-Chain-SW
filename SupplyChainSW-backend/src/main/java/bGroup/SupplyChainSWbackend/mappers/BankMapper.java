package bGroup.SupplyChainSWbackend.mappers;

import bGroup.SupplyChainSWbackend.dtos.BankDto;
import bGroup.SupplyChainSWbackend.entities.Bank;
import org.mapstruct.Mapper;

@Mapper
public interface BankMapper {
    BankDto toDto(Bank bank);
}
