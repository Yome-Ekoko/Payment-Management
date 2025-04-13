package com.yomeekoko.tredbase_payment_system.service.interfaces;

import com.yomeekoko.tredbase_payment_system.utils.dto.AccountResponse;

public interface AccountService {

    AccountResponse createAccountForParent(Long parentId);

    AccountResponse createAccountForStudent(Long studentId);

    AccountResponse getAccountForParent(Long parentId);

    AccountResponse getAccountForStudent(Long studentId);

    Double getAccountBalanceForParent(Long parentId);

    Double getAccountBalanceForStudent(Long studentId);
}

