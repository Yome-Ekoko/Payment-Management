package com.yomeekoko.tredbase_payment_system.service.implementation;

import com.yomeekoko.tredbase_payment_system.persistence.models.Account;
import com.yomeekoko.tredbase_payment_system.persistence.models.Parent;
import com.yomeekoko.tredbase_payment_system.persistence.models.Payment;
import com.yomeekoko.tredbase_payment_system.persistence.models.Student;
import com.yomeekoko.tredbase_payment_system.persistence.repository.AccountRepository;
import com.yomeekoko.tredbase_payment_system.persistence.repository.ParentRepository;
import com.yomeekoko.tredbase_payment_system.persistence.repository.PaymentRepository;
import com.yomeekoko.tredbase_payment_system.persistence.repository.StudentRepository;
import com.yomeekoko.tredbase_payment_system.service.interfaces.PaymentService;
import com.yomeekoko.tredbase_payment_system.service.interfaces.RateService;
import com.yomeekoko.tredbase_payment_system.utils.dto.PaymentResponse;
import com.yomeekoko.tredbase_payment_system.persistence.mapper.PaymentMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private ParentRepository parentRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private PaymentMapper paymentMapper;

    @Autowired
    private RateService rateService;

    @Transactional
    public PaymentResponse processPayment(Long parentId, Long studentId, Double paymentAmount) {

        Double dynamicRate = rateService.getCurrentRate();

        Parent parent = parentRepository.findById(parentId)
                .orElseThrow(() -> new RuntimeException("Parent not found"));
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Double adjustedAmount = paymentAmount * (1 + dynamicRate);

        Account parentAccount = parent.getAccount();
        parentAccount.setBalance(parentAccount.getBalance() - adjustedAmount);
        accountRepository.save(parentAccount);

        if (student.getParents().size() > 1) {
            for (Parent sharedParent : student.getParents()) {
                if (!sharedParent.equals(parent)) {
                    Account sharedParentAccount = sharedParent.getAccount();
                    sharedParentAccount.setBalance(sharedParentAccount.getBalance() - adjustedAmount);
                    accountRepository.save(sharedParentAccount);
                }
            }
        }

        Account studentAccount = student.getAccount();
        studentAccount.setBalance(studentAccount.getBalance() + adjustedAmount);
        accountRepository.save(studentAccount);

        Payment payment = new Payment(null, parent, student, adjustedAmount, LocalDateTime.now(), dynamicRate);
        payment = paymentRepository.save(payment);

        return paymentMapper.toDTO(payment); // Map to PaymentResponse
    }

    @Override
    public PaymentResponse getPayment(Long id) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found"));
        return paymentMapper.toDTO(payment);
    }

    @Override
    public List<PaymentResponse> getAllPayments() {
        return paymentRepository.findAll().stream()
                .map(paymentMapper::toDTO)
                .collect(Collectors.toList());
    }
}