package bGroup.SupplyChainSWbackend.dtos;

import bGroup.SupplyChainSWbackend.entities.Address;
import bGroup.SupplyChainSWbackend.entities.Company;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WarehouseDto {

    private Long id;
    private Company company;
    private Address address;
}
