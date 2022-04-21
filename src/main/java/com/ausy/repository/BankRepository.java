package com.ausy.repository;

import com.ausy.models.Historic;

import java.math.BigDecimal;
import java.util.List;

public interface BankRepository {

    BigDecimal getBalance(String accountId) throws Exception;

    List<Historic> getOperationsHistory(String accountId) throws Exception;

    void addOperation(String accountId, Historic account) throws Exception;
}
