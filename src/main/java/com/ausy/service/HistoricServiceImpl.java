package com.ausy.service;

import com.ausy.enumTypes.OperationTypeEnum;
import com.ausy.models.Historic;
import com.ausy.repository.BankRepository;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.util.List;

public class HistoricServiceImpl implements HistoricService {

    private final BankRepository bankRepository;
    private final Clock clock;

    public HistoricServiceImpl(BankRepository bankRepository, Clock clock) {
        this.bankRepository = bankRepository;
        this.clock = clock;
    }

    @Override
    synchronized  public void deposit(String userID, String accountId, BigDecimal amount) throws Exception{
        verifyAmount(amount);

        BigDecimal balance = bankRepository.getBalance(accountId);

        Historic depositAccount = Historic.create(userID, OperationTypeEnum.DEPOSIT, Instant.now(clock), amount, balance.add(amount));

        bankRepository.addOperation(accountId, depositAccount);
    }

    @Override
    synchronized public void withdraw(String userID, String accountId, BigDecimal amount) throws Exception {
        verifyAmount(amount);

        BigDecimal balance = bankRepository.getBalance(accountId);

        verifyBalance(amount, balance);

        Historic withdrawAccount = Historic.create(userID, OperationTypeEnum.WITHDRAW, Instant.now(clock), amount, balance.subtract(amount));

        bankRepository.addOperation(accountId, withdrawAccount);
    }

    private void verifyBalance(BigDecimal amount, BigDecimal balance) throws Exception {
        if(balance.compareTo(amount) < 0){
            throw new Exception("Your balance is insufficient");
        }
    }

    private void verifyAmount(BigDecimal amount) throws Exception {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new Exception("The amount is greater than your balance");
        }
    }

    @Override
    public void printAccountStatement(String accountId, HistoricDisplayInterface historicDisplayInterface) throws Exception {
        List<Historic> historic = bankRepository.getOperationsHistory(accountId);
        if(!historic.isEmpty()){
            historicDisplayInterface.printHistoric(historic);
        }
    }
}
