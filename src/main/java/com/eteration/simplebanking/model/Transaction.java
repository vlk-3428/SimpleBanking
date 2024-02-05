package com.eteration.simplebanking.model;


import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "Transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getApprovalCode() {
        return approvalCode;
    }
    public void setApprovalCode(String approvalCode) {
        this.approvalCode = approvalCode;
    }
    private String type;
    private String approvalCode;



    @CreationTimestamp
    @Column(name = "created_date", nullable = false, updatable = false)
    private Date date;
    private double amount;


    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @ManyToOne
    @JoinColumn(name = "ACCOUNT_ID")
    private Account account;

    public Transaction() {

    }

    public Transaction(double amount) {
        this.amount = amount;
    }

    public Transaction(String type, String approvalCode, Date date, double amount, Account account) {
        this.type = type;
        this.approvalCode = approvalCode;
        this.date = date;
        this.amount = amount;
        this.account = account;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }


    public  void execute(Account account){

    }



}
