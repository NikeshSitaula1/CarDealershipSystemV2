package com.pluralsight;

import java.util.ArrayList;

public class Dealership {

    private String name;
    private String address;
    private String phone;
    private ArrayList<Vehicle> inventory;

    public Dealership(){};

    public Dealership(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        inventory = new ArrayList<Vehicle>();
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }


    public void addVehicle(Vehicle vehicle){
        inventory.add(vehicle);
    }

    public ArrayList<Vehicle> getAllVehicles(){
        return inventory;
    }

    public void removeVehicle(Vehicle vehicle){
        inventory.remove(vehicle);
    }

    public ArrayList<Vehicle> getVehicleByPrice(double min, double max){
        ArrayList<Vehicle> vehicleRange = new ArrayList<Vehicle>();

        for (Vehicle vehicle : inventory){
            if(vehicle.getPrice() >= min && vehicle.getPrice() <= max){
                vehicleRange.add(vehicle);
            }
        }
        return vehicleRange;
    }

    public Vehicle getVehicleByVIN(int vin) {
        for (Vehicle vehicle : getAllVehicles()) {
            if (vehicle.getVin() == vin) {
                return vehicle;
            }
        }
        return null;
    }

}
