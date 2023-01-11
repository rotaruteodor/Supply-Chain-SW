package bGroup.SupplyChainSWbackend.dtos;

import bGroup.SupplyChainSWbackend.entities.Address;
import bGroup.SupplyChainSWbackend.entities.BankAccount;
import bGroup.SupplyChainSWbackend.entities.Employee;
import bGroup.SupplyChainSWbackend.entities.Warehouse;
import bGroup.SupplyChainSWbackend.entities.enums.CompanyRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompanyDto {

    private Long id;
    private String name;
    private Address address;
    private List<Warehouse> warehouses;
    private List<Employee> employees;
    private CompanyRole role;
    private BankAccount bankAccount;
}
