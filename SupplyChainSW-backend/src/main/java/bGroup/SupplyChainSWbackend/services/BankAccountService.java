package bGroup.SupplyChainSWbackend.services;

import bGroup.SupplyChainSWbackend.dtos.BankAccountDto;
import bGroup.SupplyChainSWbackend.entities.BankAccount;
import bGroup.SupplyChainSWbackend.exceptions.ErrorMessage;
import bGroup.SupplyChainSWbackend.exceptions.ResourceNotFoundException;
import bGroup.SupplyChainSWbackend.mappers.BankAccountMapper;
import bGroup.SupplyChainSWbackend.repositories.BankAccountRepository;
import bGroup.SupplyChainSWbackend.repositories.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class BankAccountService {

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Autowired
    private BankRepository bankRepository;

    @Autowired
    private BankAccountMapper bankAccountMapper;

    public List<BankAccountDto> findAll() {
        return bankAccountRepository.findAll()
                .stream()
                .map(bankAccount -> bankAccountMapper.toDto(bankAccount))
                .toList();
    }

    public ResponseEntity<BankAccountDto> findById(@PathVariable Long id) {
        return bankAccountRepository.findById(id)
                .map(bankAccount -> bankAccountMapper.toDto(bankAccount))
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorMessage.getDoesNotExistErrorMessage(
                        "BankAccount", id)));
    }

    public ResponseEntity<BankAccountDto> add(@RequestBody BankAccount bankAccount, @PathVariable Long bankId) {
        bankRepository.findById(bankId)
                .map(bank -> {
                    bankAccount.setBank(bank);
                    return bank;
                })
                .orElseThrow(() -> new ResourceNotFoundException(
                        ErrorMessage.getDoesNotExistErrorMessage("Bank", bankId)));

        bankAccountRepository.save(bankAccount);
        return ResponseEntity.ok(bankAccountMapper.toDto(bankAccount));
    }
}
