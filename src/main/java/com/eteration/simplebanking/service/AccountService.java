package com.eteration.simplebanking.service;


import com.eteration.simplebanking.dto.AccountRequestDto;
import com.eteration.simplebanking.dto.AccountResponseDto;
import com.eteration.simplebanking.model.Account;
import com.eteration.simplebanking.model.Transaction;
import com.eteration.simplebanking.repository.AccountRepository;
import com.eteration.simplebanking.repository.TransactionRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository, TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    private final TransactionRepository transactionRepository;

    @Transactional
    public String depositMoney(String accountNumber, AccountRequestDto accountRequestDto){
        try {
            Account account = accountRepository.findByAccountNumber(accountNumber);
            account.credit(accountRequestDto.getAmount());

            accountRepository.save(account);

            Transaction transaction = new Transaction();
            transaction.setAmount(accountRequestDto.getAmount());
            transaction.setAccount(account);
            transaction.setType("DepositTransaction");
            UUID uuid = UUID.randomUUID();
            String approvalCode = uuid.toString();
            transaction.setApprovalCode(approvalCode);
            transactionRepository.save(transaction);
            return "Success";
        }
        catch (Exception e){
            e.printStackTrace();
            return "Failed";
        }
    }

    @Transactional
    public String withdrawMoney(String accountNumber, AccountRequestDto accountRequestDto){
        try{
            Account account = accountRepository.findByAccountNumber(accountNumber);
            account.debit(accountRequestDto.getAmount());
            accountRepository.save(account);

            Transaction transaction = new Transaction();
            transaction.setAmount(accountRequestDto.getAmount());
            transaction.setAccount(account);
            transaction.setType("WithdrawalTransaction");
            UUID uuid = UUID.randomUUID();
            String approvalCode = uuid.toString();
            transaction.setApprovalCode(approvalCode);
            transactionRepository.save(transaction);
            return "Success";
        }
        catch (Exception e){
            e.printStackTrace();
            return "Failed";
        }
    }


    public AccountResponseDto getAccountNumber(String accountNumber){
        Account account = accountRepository.findByAccountNumber(accountNumber);
        AccountResponseDto responseDto = AccountResponseDto.fromAccount(account);
        return responseDto;
    }


}
