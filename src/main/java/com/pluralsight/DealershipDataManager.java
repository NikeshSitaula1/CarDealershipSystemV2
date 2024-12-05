package com.pluralsight;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class DealershipDataManager {

    private final BasicDataSource dataSource;

    public DealershipDataManager(BasicDataSource dataSource) {
        this.dataSource = dataSource;
    }


    public ArrayList<Vehicle> getVehiclesAll(int dealership_id){

        ArrayList<Vehicle> vehicles = new ArrayList<>();

        try(
                Connection connection = dataSource.getConnection();)
        {
            try
                    (PreparedStatement pStatement = connection.prepareStatement(
                            "select *\n" +
                                    "FROM vehicles\n" +
                                    "join inventory on inventory.vin= vehicles.vin\n" +
                                    "where inventory.dealership_id = ?;"
                    ))

            {
                pStatement.setInt(1,dealership_id);
                try(ResultSet results = pStatement.executeQuery()){

                    while(results.next()){
                        vehicles.add(new Vehicle(
                                results.getInt("vin"),
                                results.getInt("year"),
                                results.getString("make"),
                                results.getString("model"),
                                results.getString("vehicleType"),
                                results.getString("color"),
                                results.getInt("odometer"),
                                results.getDouble("price")
                                )
                        );
                    }
                }
            }
            return vehicles;
        }
        catch (SQLException e){
            e.printStackTrace();

        }
        return vehicles;

    }


    public ArrayList<Vehicle> getVehiclesByPrice(int dealership_id, double min, double max){
        return null;
    }

    public ArrayList<Vehicle> getVehiclesByVin(int dealership_id, int VIN){
        return null;
    }



    public void addVehicle(int dealership_id, Vehicle vehicle){
        //insert to database
    }

    public void removeVehicle(int dealership_id, Vehicle vehicle){
        // delete from database
    }



}
