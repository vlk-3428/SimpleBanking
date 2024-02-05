package com.eteration.simplebanking;

import com.eteration.simplebanking.dto.AccountRequestDto;
import com.eteration.simplebanking.model.Account;
import com.eteration.simplebanking.model.Transaction;
import com.eteration.simplebanking.repository.AccountRepository;
import com.eteration.simplebanking.repository.TransactionRepository;
import com.eteration.simplebanking.service.AccountService;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Date;
import java.util.UUID;

public class AccountServiceTest {
    private AccountRepository accountRepository;
    private TransactionRepository transactionRepository;
    private AccountService accountService;

    @BeforeEach
    void setUp(){
        accountRepository = Mockito.mock(AccountRepository.class);
        transactionRepository = Mockito.mock(TransactionRepository.class);

        accountService = new AccountService(accountRepository,transactionRepository);
    }

    @Test
    void testDepositMoney(){
        String accountNumber = "123456789";
        double amount = 100.0;
        Account account = new Account();
        account.setAccountNumber(accountNumber);
        AccountRequestDto accountRequestDto = new AccountRequestDto(amount);

        Mockito.when(accountRepository.findByAccountNumber(accountNumber)).thenReturn(account);

        String result = accountService.depositMoney(accountNumber, accountRequestDto);

        Assertions.assertEquals(result, "Success");
        Assertions.assertEquals(account.getBalance(), 100.0); // Assuming deposit is successful
        Mockito.verify(accountRepository, Mockito.times(1)).findByAccountNumber(accountNumber);
        Mockito.verify(accountRepository, Mockito.times(1)).save(account);
        Mockito.verify(transactionRepository, Mockito.times(1)).save(Mockito.any()); // Assuming transaction is saved


    }


}
