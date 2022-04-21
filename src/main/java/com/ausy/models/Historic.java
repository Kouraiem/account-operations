package com.ausy.models;

import com.ausy.enumTypes.OperationTypeEnum;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

public final class Historic {

    private final String userID;
    private final OperationTypeEnum operationTypeEnum;

    private final Instant instant;
    private final BigDecimal amount;
    private final BigDecimal balance;

    private Historic(String userID, OperationTypeEnum operationTypeEnum, Instant instant, BigDecimal amount, BigDecimal balance) {
        this.userID = userID;
        this.operationTypeEnum = operationTypeEnum;
        this.instant = instant;
        this.amount = amount;
        this.balance = balance;
    }

    public static Historic create(String userID, OperationTypeEnum operationTypeEnum, Instant instant, BigDecimal amount, BigDecimal balance) throws Exception {
        verifyAmount(amount);
        verifyBalance(balance);
        return new Historic(userID, operationTypeEnum, instant, amount, balance);
    }

    private static void verifyBalance(BigDecimal balance) throws Exception {
        if(balance.compareTo(BigDecimal.ZERO) < 0){
            throw new Exception("Balance should be greater or equal to 0");
        }
    }

    private static void verifyAmount(BigDecimal amount) throws Exception {
        if(amount.compareTo(BigDecimal.ZERO) <= 0){
            throw new Exception("Amount should be greater than 0");
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(operationTypeEnum, instant, amount, balance);
    }

    public OperationTypeEnum getOperationType() {
        return operationTypeEnum;
    }

    public Instant getInstant() {
        return instant;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        return "Account{" +
                "userID='" + userID + '\'' +
                ", accountTypeEnum=" + operationTypeEnum +
                ", instant=" + instant +
                ", amount=" + amount +
                ", balance=" + balance +
                '}';
    }
}
