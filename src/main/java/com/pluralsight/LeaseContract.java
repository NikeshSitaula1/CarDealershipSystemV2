package com.pluralsight;

public class LeaseContract extends Contract {

    private double endingValue;
    private double leaseFee;

    public LeaseContract(String dateOfContract, String customerName, String customerEmail,
                         String vehicleSold, String totalPrice, String monthlyPayment,
                         double endingValue, double leaseFee) {
        super(dateOfContract, customerName, customerEmail, vehicleSold, totalPrice, monthlyPayment);
        this.endingValue = 0.5;
        this.leaseFee = 0.07;
    }

    @Override
    public double getTotalPrice(){
        return 0;
    }

    @Override
    public double getMonthlyPayment(){
        return 0;
    }
}
