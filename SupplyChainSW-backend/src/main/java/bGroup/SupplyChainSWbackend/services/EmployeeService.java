package bGroup.SupplyChainSWbackend.services;

import bGroup.SupplyChainSWbackend.dtos.BankAccountDto;
import bGroup.SupplyChainSWbackend.dtos.EmployeeDto;
import bGroup.SupplyChainSWbackend.entities.BankAccount;
import bGroup.SupplyChainSWbackend.entities.Employee;
import bGroup.SupplyChainSWbackend.exceptions.ErrorMessage;
import bGroup.SupplyChainSWbackend.exceptions.ResourceNotFoundException;
import bGroup.SupplyChainSWbackend.mappers.EmployeeMapper;
import bGroup.SupplyChainSWbackend.repositories.DepartmentRepository;
import bGroup.SupplyChainSWbackend.repositories.EmployeeRepository;
import bGroup.SupplyChainSWbackend.repositories.JobTitleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private JobTitleRepository jobTitleRepository;

    @Autowired
    private EmployeeMapper employeeMapper;

    public List<EmployeeDto> findAll() {
        return employeeRepository.findAll()
                .stream()
                .map(employee -> employeeMapper.toDto(employee))
                .toList();
    }

    public ResponseEntity<EmployeeDto> findById(@PathVariable Long id) {
        return employeeRepository.findById(id)
                .map(employee -> employeeMapper.toDto(employee))
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorMessage.getDoesNotExistErrorMessage(
                        "Employee", id)));
    }

    public ResponseEntity<EmployeeDto> add(@RequestBody Employee employee,
                                           @PathVariable Long departmentId,
                                           @PathVariable Long jobTitleId) {
        departmentRepository.findById(departmentId)
                .map(department -> {
                    employee.setDepartment(department);
                    return department;
                })
                .orElseThrow(() -> new ResourceNotFoundException(
                        ErrorMessage.getDoesNotExistErrorMessage("Department", departmentId)));

        jobTitleRepository.findById(jobTitleId)
                .map(jobTitle -> {
                    employee.setJobTitle(jobTitle);
                    return jobTitle;
                })
                .orElseThrow(() -> new ResourceNotFoundException(
                        ErrorMessage.getDoesNotExistErrorMessage("JobTitle", jobTitleId)));

        employeeRepository.save(employee);
        return ResponseEntity.ok(employeeMapper.toDto(employee));
    }
}
