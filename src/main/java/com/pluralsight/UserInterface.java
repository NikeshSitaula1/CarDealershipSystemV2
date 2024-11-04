package com.pluralsight;

public class UserInterface {

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
}