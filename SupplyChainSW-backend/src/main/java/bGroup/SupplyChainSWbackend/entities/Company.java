package bGroup.SupplyChainSWbackend.entities;

import bGroup.SupplyChainSWbackend.entities.enums.CompanyRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "companies")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToOne(targetEntity = Address.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;

    @OneToMany(targetEntity = Warehouse.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "company_id")
    private List<Warehouse> warehouses;

    @OneToMany(targetEntity = Employee.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "company_id")
    private List<Employee> employees;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private CompanyRole role;

    @OneToOne(targetEntity = BankAccount.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "bank_account_id")
    private BankAccount bankAccount;

}