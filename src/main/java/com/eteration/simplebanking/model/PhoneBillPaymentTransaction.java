package com.eteration.simplebanking.model;

public class PhoneBillPaymentTransaction extends Transaction {
    private String serviceProvider;
    private String phoneNumber;

    public PhoneBillPaymentTransaction( String serviceProvider, String phoneNumber, double amount) {
        super(amount);
        this.serviceProvider = serviceProvider;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public void execute(Account account) {
        account.debit(getAmount());
    }
}
