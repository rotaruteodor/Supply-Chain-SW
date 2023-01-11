package bGroup.SupplyChainSWbackend.services;

import bGroup.SupplyChainSWbackend.dtos.BankAccountDto;
import bGroup.SupplyChainSWbackend.dtos.UserDto;
import bGroup.SupplyChainSWbackend.entities.BankAccount;
import bGroup.SupplyChainSWbackend.entities.User;
import bGroup.SupplyChainSWbackend.exceptions.ErrorMessage;
import bGroup.SupplyChainSWbackend.exceptions.ResourceNotFoundException;
import bGroup.SupplyChainSWbackend.mappers.UserMapper;
import bGroup.SupplyChainSWbackend.repositories.EmployeeRepository;
import bGroup.SupplyChainSWbackend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private UserMapper userMapper;

    public List<UserDto> findAll() {
        return userRepository.findAll()
                .stream()
                .map(user -> userMapper.toDto(user))
                .toList();
    }

    public ResponseEntity<UserDto> findById(@PathVariable Long id) {
        return userRepository.findById(id)
                .map(user -> userMapper.toDto(user))
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorMessage.getDoesNotExistErrorMessage(
                        "User", id)));
    }

    public ResponseEntity<UserDto> add(@RequestBody User user, @PathVariable Long employeeId) {
        employeeRepository.findById(employeeId)
                .map(employee -> {
                    user.setEmployee(employee);
                    return employee;
                })
                .orElseThrow(() -> new ResourceNotFoundException(
                        ErrorMessage.getDoesNotExistErrorMessage("Employee", employeeId)));

        userRepository.save(user);
        return ResponseEntity.ok(userMapper.toDto(user));
    }
}
