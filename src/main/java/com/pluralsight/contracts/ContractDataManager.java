package com.pluralsight.contracts;

import org.apache.commons.dbcp2.BasicDataSource;

public class ContractDataManager {

    private final BasicDataSource dataSource;

    public ContractDataManager(BasicDataSource dataSource) {
        this.dataSource = dataSource;
    }


}
