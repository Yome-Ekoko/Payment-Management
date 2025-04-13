package com.yomeekoko.tredbase_payment_system.service.implementation;

import com.yomeekoko.tredbase_payment_system.persistence.models.Account;
import com.yomeekoko.tredbase_payment_system.persistence.models.Parent;
import com.yomeekoko.tredbase_payment_system.persistence.models.Student;
import com.yomeekoko.tredbase_payment_system.persistence.repository.AccountRepository;
import com.yomeekoko.tredbase_payment_system.persistence.repository.ParentRepository;
import com.yomeekoko.tredbase_payment_system.persistence.repository.StudentRepository;
import com.yomeekoko.tredbase_payment_system.service.interfaces.AccountService;
import com.yomeekoko.tredbase_payment_system.utils.dto.AccountResponse;
import com.yomeekoko.tredbase_payment_system.persistence.mapper.AccountMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ParentRepository parentRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private AccountMapper accountMapper;

    @Transactional
    public AccountResponse createAccountForParent(Long parentId) {
        if (accountRepository.findByParentId(parentId).isPresent()) {
            throw new RuntimeException("This parent already has an account.");
        }

        Parent parent = parentRepository.findById(parentId)
                .orElseThrow(() -> new RuntimeException("Parent not found"));

        Account account = new Account();
        account.setBalance(0.0);
        account.setParent(parent);

        parent.setAccount(account); // Link the parent to the account


        return accountMapper.toDTO(accountRepository.save(account));
    }

    @Transactional
    public AccountResponse createAccountForStudent(Long studentId) {
        if (accountRepository.findByStudentId(studentId).isPresent()) {
            throw new RuntimeException("This student already has an account.");
        }

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Account account = new Account();
        account.setBalance(0.0);
        account.setStudent(student);

        student.setAccount(account); // Link the student to the account


        return accountMapper.toDTO(accountRepository.save(account));
    }
@Override
    public AccountResponse getAccountForParent(Long parentId) {
        Account account = accountRepository.findByParentId(parentId)
                .orElseThrow(() -> new RuntimeException("Account not found for this parent"));
        return accountMapper.toDTO(account);
    }
@Override
    public AccountResponse getAccountForStudent(Long studentId) {
        Account account = accountRepository.findByStudentId(studentId)
                .orElseThrow(() -> new RuntimeException("Account not found for this student"));
        return accountMapper.toDTO(account);
    }
@Override
    public Double getAccountBalanceForParent(Long parentId) {
        Account account = accountRepository.findByParentId(parentId)
                .orElseThrow(() -> new RuntimeException("Account not found for this parent"));
        return account.getBalance();
    }
@Override
    public Double getAccountBalanceForStudent(Long studentId) {
        Account account = accountRepository.findByStudentId(studentId)
                .orElseThrow(() -> new RuntimeException("Account not found for this student"));
        return account.getBalance();
    }
}
