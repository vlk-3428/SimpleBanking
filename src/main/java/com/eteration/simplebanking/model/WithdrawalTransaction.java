package com.eteration.simplebanking.model;


// This class is a place holder you can change the complete implementation
public class WithdrawalTransaction extends Transaction {
    private double amount;

    public WithdrawalTransaction(double amount) {
        super(amount);
        this.amount = amount;
    }

    @Override
    public void execute(Account account) {
        account.debit(getAmount());
    }
}


