package com.eteration.simplebanking.dto;

import com.eteration.simplebanking.model.Account;
import com.eteration.simplebanking.model.Transaction;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class AccountResponseDto {
    private String accountNumber;
    private String owner;
    private double balance;
    private List<TransactionSummaryDto> transactions;




    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public List<TransactionSummaryDto> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<TransactionSummaryDto> transactions) {
        this.transactions = transactions;
    }

    public static AccountResponseDto fromAccount(Account account) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        AccountResponseDto responseDto = new AccountResponseDto();
        responseDto.setAccountNumber(account.getAccountNumber());
        responseDto.setOwner(account.getOwner());
        responseDto.setBalance(account.getBalance());

        List<TransactionSummaryDto> transactionSummaries = new ArrayList<>();
        for (Transaction transaction : account.getTransactions()) {
            TransactionSummaryDto summaryDto = new TransactionSummaryDto();
            summaryDto.setId(transaction.getId());
            summaryDto.setType(transaction.getType());
            summaryDto.setApprovalCode(transaction.getApprovalCode());
            summaryDto.setDate(transaction.getDate());
            summaryDto.setAmount(transaction.getAmount());

            transactionSummaries.add(summaryDto);
        }

        responseDto.setTransactions(transactionSummaries);
        return responseDto;
    }
}

