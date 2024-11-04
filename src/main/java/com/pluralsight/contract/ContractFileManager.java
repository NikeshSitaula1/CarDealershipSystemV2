package com.pluralsight.contract;

import com.pluralsight.Vehicle;
import com.pluralsight.contract.Contract;

import java.io.*;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class ContractFileManager {

    public static ArrayList<Contract> getContracts() {


        ArrayList<Contract> contracts;

        try {
            FileReader fr = new FileReader("contracts.csv");
            BufferedReader br = new BufferedReader(fr);

            String input;

            if ((input = br.readLine()) != null) {
                String[] tokens = input.split(Pattern.quote("|"));
                String dateOfContract = tokens[1];
                String customerName = tokens[2];
                String customerEmail = tokens[3];


                int vin = Integer.parseInt(tokens[4]);
                int year = Integer.parseInt(tokens[5]);
                String make = tokens[6];
                String model = tokens[7];
                String vehicleType = tokens[8];
                String color = tokens[9];
                int odometer = Integer.parseInt(tokens[10]);
                double price = Double.parseDouble(tokens[11]);
                Vehicle vehicleSold = new Vehicle(vin,year,make,model,vehicleType,color,odometer,price);


//                if token sales list
//                        print token [0] as sale
//                contract = new Contract(dateOfContract, customerName, customerEmail, vehicleSold);

            }
            //br.readLine(); //Skips line by line


            while ((input = br.readLine()) != null) {

                Vehicle vehicle = new Vehicle(input);
                assert dealership != null;
                dealership.addVehicle(vehicle);
            }
            br.close(); //Closes the BufferedReader

        } catch (Exception e) {
            System.out.println("Error while saving Transactions: " + e.getMessage());
        }
        return dealership; //Returns the list

    }
}
