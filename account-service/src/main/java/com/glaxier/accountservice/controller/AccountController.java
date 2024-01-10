package com.glaxier.accountservice.controller;

import com.glaxier.accountservice.model.Account;
import com.glaxier.accountservice.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accounts")
@AllArgsConstructor
public class AccountController {
    AccountService accountService;

    @PostMapping
    public Account addAccount(@RequestBody Account account) {
        return accountService.save(account);
    }
}
