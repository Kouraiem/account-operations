package com.ausy.service;

import java.math.BigDecimal;

public interface HistoricService {

    void printAccountStatement(String accountId, HistoricDisplayInterface historicDisplayInterface) throws Exception;

    void deposit(String userID, String accountId, BigDecimal amount) throws Exception;

    void withdraw(String userID, String accountId, BigDecimal amount) throws Exception;
}

