
package com.pluralsight.contract;

import com.pluralsight.Vehicle;

import java.io.*;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class ContractFileManager {

    public static ArrayList<Contract> getContracts(String filename) {


        ArrayList<Contract> results = new ArrayList<>();

        try {
            FileReader fr = new FileReader("contracts.csv");
            BufferedReader br = new BufferedReader(fr);

            String input;

            while ((input = br.readLine()) != null) {
                String[] tokens = input.split(Pattern.quote("|"));
                if (tokens.length >= 16) {
                    if (tokens[0].equalsIgnoreCase("SALE")) {

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
                        Vehicle vehicleSold = new Vehicle(vin, year, make, model, vehicleType,
                                color, odometer, price);

                        double salesTaxAmount = Double.parseDouble(tokens[12]);
                        double recordingFee = Double.parseDouble(tokens[13]);
                        double processingFee = Double.parseDouble(tokens[14]);
                        double totalPrice = Double.parseDouble(tokens[15]);
                        boolean finance = Boolean.parseBoolean(tokens[16]);
                        double monthlyPayment = Double.parseDouble(tokens[17]);

                        SalesContract contract = new SalesContract(dateOfContract, customerName, customerEmail,
                                vehicleSold, salesTaxAmount, recordingFee, processingFee,  totalPrice, finance,  monthlyPayment);

                        results.add(contract);

                    } else if (tokens[0].equalsIgnoreCase("LEASE")) {
                        // Parse common fields for LeaseContract
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
                        Vehicle vehicleLease = new Vehicle(vin, year, make, model, vehicleType, color, odometer, price);

                        double endingValue = Double.parseDouble(tokens[12]);
                        double leaseFeeRate = Double.parseDouble(tokens[13]);
                        double totalPrice = Double.parseDouble(tokens[14]);
                        double monthlyPayment = Double.parseDouble(tokens[15]);

                        // Create and add LeaseContract to results
                        LeaseContract contract = new LeaseContract(dateOfContract, customerName, customerEmail,
                                vehicleLease, endingValue, leaseFeeRate);
                        results.add(contract);
                    }
                }
                else {
                    System.out.println("Invalid");
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return results;
    }
}
