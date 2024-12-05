package com.pluralsight;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.SQLException;
import java.sql.SQLOutput;

public class program {

    public static void main(String[] args) throws SQLException {

        DealershipDataManager dm;
        try (
                BasicDataSource dataSource = getConfiguredDataSource(args);
                ){
            dm = new DealershipDataManager(dataSource);
            UserInterface ui = new UserInterface(dm);
            ui.display();

        }catch (Exception e){
            System.out.println("main");
            e.printStackTrace();
        }
    }


    public static BasicDataSource getConfiguredDataSource(String[] args){
        String username = args[0];
        String password = args[1];


        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/cardealership");
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;

    }
}
