package com.eteration.simplebanking.controller;


// This class is a place holder you can change the complete implementation

import java.util.Date;

public class TransactionStatus {
    private boolean success;
    private String approvalCode;
    private Date timestamp;

    public TransactionStatus() {

    }
    public TransactionStatus(boolean success, String approvalCode) {
        this.success = success;
        this.approvalCode = approvalCode;
        this.timestamp = new Date();
    }

    public boolean isSuccess() {
        return success;
    }

    public String getApprovalCode() {
        return approvalCode;
    }

    public Date getTimestamp() {
        return timestamp;
    }
}
