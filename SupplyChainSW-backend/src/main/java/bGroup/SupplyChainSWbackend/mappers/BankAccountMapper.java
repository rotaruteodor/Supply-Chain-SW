package bGroup.SupplyChainSWbackend.mappers;

import bGroup.SupplyChainSWbackend.dtos.BankAccountDto;
import bGroup.SupplyChainSWbackend.entities.BankAccount;
import org.mapstruct.Mapper;

@Mapper
public interface BankAccountMapper {
    BankAccountDto toDto(BankAccount bankAccount);
}
