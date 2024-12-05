
package com.pluralsight;

import java.io.*;
import java.util.regex.Pattern;

public class DealershipFileManager {

    public static Dealership getDealership() {

        Dealership dealership = null;

        try {
            FileReader fr = new FileReader("inventory.csv");
            BufferedReader br = new BufferedReader(fr);

            String input;

            if ((input = br.readLine()) != null) {
                String[] tokens = input.split(Pattern.quote("|"));
                String name = tokens[0];
                String address = tokens[1];
                String phone = tokens[2];

                dealership = new Dealership(name, address, phone);
            }
            //br.readLine(); //Skips line by line

            /* Read each line from the file until there are no more lines to read.
            Splits the lines into tokens using the '|' character as the delimiter.
             */
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


    // Method to save transactions to a file in a specific format
    public static void saveDealership(Dealership dealership) {
        try {
            //Creating a file writer and assigning the file writer to the buffered writer.
            FileWriter fw = new FileWriter("inventory.csv");
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write(getEncodedDealershipHeader(dealership));
            // Loop through transactions and write each one to the file
            for (Vehicle vehicle : dealership.getAllVehicles()) {
                bw.write(getEncodedVehicle(vehicle));
            }
            bw.close(); // Close the BufferedWriter

        } catch (IOException e){
            System.out.println("Error while saving Transactions: " + e.getMessage());
        }
    }

    private static String getEncodedDealershipHeader(Dealership dealership){
        return dealership.getName() + "|" + dealership.getAddress() + "|" + dealership.getPhone() + "\n";
    }

    private static String getEncodedVehicle(Vehicle vehicle){
        return new StringBuilder()
                .append(vehicle.getVin()).append("|")
                .append(vehicle.getYear()).append("|")
                .append(vehicle.getMake()).append("|")
                .append(vehicle.getModel()).append("|")
                .append(vehicle.getVehicleType()).append("|")
                .append(vehicle.getColor()).append("|")
                .append(vehicle.getOdometer()).append("|")
                .append(vehicle.getPrice()).append("\n").toString();
    }
}
