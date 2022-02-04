package com.thejunklab.buckets;

import java.util.Calendar;
import java.util.Date;

public class Bucket {
    String id, name;
    float balance;
    boolean isConstant;
    float incomeShare;

    public Bucket() {
    }

    public Bucket(String id, String name, float balance, boolean isConstant, float incomeShare) {
        this.id = id;
        this.name = name;
        this.balance = balance;
        this.isConstant = isConstant;
        this.incomeShare = incomeShare;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public boolean isConstant() {
        return isConstant;
    }

    public void setConstant(boolean constant) {
        isConstant = constant;
    }

    public float getIncomeShare() {
        return incomeShare;
    }

    public void setIncomeShare(float incomeShare) {
        this.incomeShare = incomeShare;
    }

    public void updateBalance(float amount) {
        setBalance(balance + amount);
    }
}
