package com.mycompany.tennis.core;

import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;

public class DataSourceProvider {
    private static BasicDataSource singleDataSource;

    public static DataSource getSingleDataSourceInstance() {
        if(singleDataSource == null) {
            singleDataSource = new BasicDataSource();
            singleDataSource.setUrl("jdbc:mysql://localhost:3306/TENNIS?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=Europe/Paris&allowPublicKeyRetrieval=true");
            singleDataSource.setInitialSize(5);
            singleDataSource.setUsername("EURKODEV");
            singleDataSource.setPassword("Bouftou80@");
        }
        return singleDataSource;
    };

}
