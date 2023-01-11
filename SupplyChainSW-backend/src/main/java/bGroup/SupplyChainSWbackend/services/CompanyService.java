package bGroup.SupplyChainSWbackend.services;

import bGroup.SupplyChainSWbackend.dtos.CompanyDto;
import bGroup.SupplyChainSWbackend.entities.Company;
import bGroup.SupplyChainSWbackend.entities.Employee;
import bGroup.SupplyChainSWbackend.entities.Warehouse;
import bGroup.SupplyChainSWbackend.exceptions.ErrorMessage;
import bGroup.SupplyChainSWbackend.exceptions.ResourceNotFoundException;
import bGroup.SupplyChainSWbackend.mappers.CompanyMapper;
import bGroup.SupplyChainSWbackend.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Autowired
    private WarehouseRepository warehouseRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private CompanyMapper companyMapper;

    public List<CompanyDto> findAll() {
        return companyRepository.findAll()
                .stream()
                .map(company -> companyMapper.toDto(company))
                .toList();
    }

    public ResponseEntity<CompanyDto> findById(@PathVariable Long id) {
        return companyRepository.findById(id)
                .map(company -> companyMapper.toDto(company))
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorMessage.getDoesNotExistErrorMessage(
                        "Company", id)));
    }

    public ResponseEntity<CompanyDto> add(@RequestBody Company company,
                                          @PathVariable Long addressId,
                                          @PathVariable Long bankAccountId) {

        addressRepository.findById(addressId)
                .map(address -> {
                    company.setAddress(address);
                    return address;
                })
                .orElseThrow(() -> new ResourceNotFoundException(
                        ErrorMessage.getDoesNotExistErrorMessage("Address", addressId)));

        bankAccountRepository.findById(bankAccountId)
                .map(bankAccount -> {
                    company.setBankAccount(bankAccount);
                    return bankAccount;
                })
                .orElseThrow(() -> new ResourceNotFoundException(
                        ErrorMessage.getDoesNotExistErrorMessage("BankAccount", bankAccountId)));

        ArrayList<Warehouse> warehouses = new ArrayList<>();
        for (var warehouse : company.getWarehouses()){
            warehouseRepository.findById(warehouse.getId())
                    .map(wh -> {
                        warehouses.add(wh);
                        return wh;
                    })
                    .orElseThrow(() -> new ResourceNotFoundException(
                            ErrorMessage.getDoesNotExistErrorMessage("Warehouse", bankAccountId)));
        }
        company.setWarehouses(warehouses);

        ArrayList<Employee> employees = new ArrayList<>();
        for (var employee : company.getEmployees()){
            employeeRepository.findById(employee.getId())
                    .map(emp -> {
                        employees.add(emp);
                        return emp;
                    })
                    .orElseThrow(() -> new ResourceNotFoundException(
                            ErrorMessage.getDoesNotExistErrorMessage("Employee", bankAccountId)));
        }
        company.setEmployees(employees);


        companyRepository.save(company);
        return ResponseEntity.ok(companyMapper.toDto(company));
    }

}
