package com.yomeekoko.tredbase_payment_system.persistence.mapper;

import com.yomeekoko.tredbase_payment_system.persistence.models.Account;
import com.yomeekoko.tredbase_payment_system.utils.dto.AccountResponse;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {

    public AccountResponse toDTO(Account account) {
        return new AccountResponse(
                account.getId(),
                account.getBalance(),
                account.getParent() != null ? account.getParent().getId() : null,
                account.getStudent() != null ? account.getStudent().getId() : null
        );
    }
}
