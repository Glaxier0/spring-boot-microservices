package com.glaxier.accountservice.service;

import com.glaxier.accountservice.model.Account;
import com.glaxier.accountservice.repository.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AccountService {
    AccountRepository accountRepository;

    public Account save(Account account) {
        return accountRepository.save(account);
    }
}
