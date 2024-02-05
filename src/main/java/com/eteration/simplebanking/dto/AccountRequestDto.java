package com.eteration.simplebanking.dto;


public class AccountRequestDto {


    private double amount;

    public AccountRequestDto(double amount) {
        this.amount = amount;
    }

    public AccountRequestDto() {

    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
