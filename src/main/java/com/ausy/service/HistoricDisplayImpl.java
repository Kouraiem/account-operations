package com.ausy.service;

import com.ausy.models.Historic;

import java.util.List;

public class HistoricDisplayImpl implements HistoricDisplayInterface {

    @Override
    public void printHistoric(List<Historic> accounts) {
        accounts.forEach(it -> System.out.println(it));
    }
}
