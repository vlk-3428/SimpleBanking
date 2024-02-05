package com.eteration.simplebanking.controller;

import com.eteration.simplebanking.dto.AccountRequestDto;
import com.eteration.simplebanking.dto.AccountResponseDto;
import com.eteration.simplebanking.model.Account;
import com.eteration.simplebanking.model.DepositTransaction;
import com.eteration.simplebanking.model.WithdrawalTransaction;
import com.eteration.simplebanking.service.AccountService;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account/v1")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/credit/{accountNumber}")
    public String depositMoney(@PathVariable String accountNumber, @RequestBody AccountRequestDto accountRequestDto){
        return accountService.depositMoney(accountNumber, accountRequestDto);
    }

    @PostMapping("/debit/{accountNumber}")
    public String withdrawMoney(@PathVariable String accountNumber, @RequestBody AccountRequestDto accountRequestDto){
        return accountService.withdrawMoney(accountNumber, accountRequestDto);
    }

    @GetMapping("/{accountNumber}")
    public AccountResponseDto getAccount(@PathVariable String accountNumber){
        return accountService.getAccountNumber(accountNumber);
    }

    public Account getAccount(Account account) {
        return account;
    }


    public void credit(Account account, double amount) {
        account.post(new DepositTransaction(amount));
    }


    public void debit(Account account, double amount) {
        account.post(new WithdrawalTransaction(amount));
    }
}