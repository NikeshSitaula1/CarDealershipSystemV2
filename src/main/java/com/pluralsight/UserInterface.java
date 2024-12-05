
package com.pluralsight;

import com.pluralsight.contract.Contract;
import com.pluralsight.contract.LeaseContract;
import com.pluralsight.contract.SalesContract;

public class UserInterface {

    public static String file_dealership = "inventory.csv";
    public static  String  file_contracts = "contracts.csv";
    static Dealership deal;


    public UserInterface(){
        deal = DealershipFileManager.getDealership();

        do {
            try {
                System.out.println("-----------------Welcome to Car Dealership-------------------");
                System.out.println("1. [Display] all vehicles");
                System.out.println("2. [Add] a vehicle");
                System.out.println("3. [Remove] a vehicle");
                System.out.println("4. Filter by Price [Range]");
                System.out.println("5. Sale or Lease");
                System.out.println("0. [Exit]");
                System.out.print(">> ");

                String option = Console.PromptForString();

                if (option.equals("1")) {
                    displayAll();
                }
                else if (option.equals("2")) {
                    addVehicle();
                }
                else if (option.equals("3")) {
                    removeVehicle();
                }
                else if (option.equals("4")){
                    byPrice();
                }
                else if (option.equals("5")){

                }
                else if (option.equals("0")){
                    return;
                }
                else {
                    System.out.println("Invalid entry");
                }
            } catch (Exception e){
                System.out.println("Invalid entry");
            }
        }while (true);
    }

    public void displayAll(){
        for(Vehicle vehicle : deal.getAllVehicles()){
            System.out.println(vehicle);
        }
    }


    public void addVehicle(){
        //get values from the user...
        int vin = Console.PromptForInt("Enter Vin: ");
        int year = Console.PromptForInt("Enter year: ");
        String make = Console.PromptForString("Enter make: ");
        String model = Console.PromptForString("Enter model: ");
        String vehicleType = Console.PromptForString("Enter vehicle type: ");
        String color = Console.PromptForString("Enter color:  ");
        int odometer = Console.PromptForInt("Enter odometer: ");
        double price = Console.PromptForDouble("Enter price: ");

        //create an instance of a Vehicle class from those values...
        Vehicle v = new Vehicle(vin,year, make, model, vehicleType, color, odometer, price);

        //call the dealerships addVehicle method, passing it the vehicle you just created.
        deal.addVehicle(v);
        DealershipFileManager.saveDealership(deal);
    }


    public void removeVehicle(){
        int vin = Console.PromptForInt("Enter vin: ");

        for(Vehicle vehicle : deal.getAllVehicles()){
            if(vin == vehicle.getVin() ){
                deal.removeVehicle(vehicle);
                DealershipFileManager.saveDealership(deal);
                break;
            }
        }
    }

    public void byPrice(){
        double min = Console.PromptForDouble("Enter min: ");
        double max = Console.PromptForDouble("Enter max: ");

        for (Vehicle vehicle : deal.getVehicleByPrice(min, max)){
            System.out.println(vehicle);
        }
    }

    public void sellOrLease(){
        int vin = 0;
        String input;
        // Get all the info we need from the user
        // Get VIN
        do {
            input = Console.PromptForString("Enter VIN of the vehicle to sell/lease (or 'v' to view all vehicles or 'q' to cancel): ");
            if (input.equalsIgnoreCase("q")) return;
            if (input.equalsIgnoreCase("v")) {
                displayAll();
                input = "";
                continue;
            }

            try {
                vin = Integer.parseInt(input);

                Vehicle vehicleToSell = deal.getVehicleByVIN(vin);
                if (vehicleToSell == null) {
                    System.out.println("Vehicle not found. Please try again.");
                    input = ""; // Reset input
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number. or v to list vehicles, or q to return to main menu.");
                input = ""; // Reset input
            }
        } while (input.isEmpty());

        System.out.println(vin);
        System.out.println(deal.getVehicleByVIN(vin));
        //at this point we should have a vin...


        // Get contract type
        String contractType;
        do {
            contractType = Console.PromptForString("Enter contract type (sale/lease) (or 'q' to cancel): ");
            if (contractType.equalsIgnoreCase("q")) return;
            if (!contractType.equalsIgnoreCase("sale") && !contractType.equalsIgnoreCase("lease")) {
                System.out.println("Invalid contract type. Please enter 'sale' or 'lease'.");
                contractType = ""; // Reset input
            }
        } while (contractType.isEmpty());

        // Get customer name
        String customerName;
        do {
            customerName = Console.PromptForString("Enter customer name (or 'q' to cancel): ");
            if (customerName.equalsIgnoreCase("q")) return;
        } while (customerName.isEmpty());

        // Get customer email
        String customerEmail;
        do {
            customerEmail = Console.PromptForString("Enter customer email (or 'q' to cancel): ");
            if (customerEmail.equalsIgnoreCase("q")) return;
        } while (customerEmail.isEmpty());

        // Get date
        String date;
        do {
            date = Console.PromptForString("Enter date (YYYYMMDD) (or 'q' to cancel): ");
            if (date.equalsIgnoreCase("q")) return;

            // Validate date format
            if (date.length() != 8 || !date.matches("\\d{8}")) {
                System.out.println("Invalid date format. Please use YYYYMMDD (e.g., 20210928)");
                date = ""; // Reset input
                continue;
            }
        } while (date.isEmpty());

        Vehicle vehicle = deal.getVehicleByVIN(vin);

        Contract contract = null;

        // Create appropriate contract type
        if (contractType.equalsIgnoreCase("sale")) {
            String financeInput;
            boolean isFinanced;
            do {
                financeInput = Console.PromptForString("Will this be financed? (yes/no) (or 'q' to cancel): ");
                if (financeInput.equalsIgnoreCase("q")) return;
                if (financeInput.equalsIgnoreCase("yes")) {
                    isFinanced = true;
                    break;
                } else if (financeInput.equalsIgnoreCase("no")) {
                    isFinanced = false;
                    break;
                }
                System.out.println("Please enter 'yes' or 'no'.");
            } while (true);

            contract = new SalesContract(date, customerName, customerEmail, vehicle);
        } else {
            contract = new LeaseContract(date,customerName,customerEmail,vehicle);
        }

        System.out.println(contract);
        assert contract != null;
        System.out.println(contract.getTotalPrice());
        System.out.println(contract.getMonthlyPayment());

    }
}
