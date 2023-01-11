package bGroup.SupplyChainSWbackend.dtos;

import bGroup.SupplyChainSWbackend.entities.Bank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BankAccountDto {

    private Long id;
    private Bank bank;
    private String IBAN;
}
