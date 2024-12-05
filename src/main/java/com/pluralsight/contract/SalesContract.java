
package com.pluralsight.contract;

import com.pluralsight.Vehicle;

public class SalesContract extends Contract {

    private double salesTaxAmount;
    private double recordingFee;
    private double processingFee;
    private boolean finance;


    public SalesContract(String dateOfContract, String customerName, String customerEmail, Vehicle vehicleSold) {
        super(dateOfContract, customerName, customerEmail, vehicleSold);
        this.salesTaxAmount = 0.05;
        this.recordingFee = 100;
    }

    public SalesContract(String dateOfContract, String customerName, String customerEmail,
                         Vehicle vehicleSold, double salesTaxAmount, double recordingFee,
                         double processingFee, double totalPrice, boolean finance,  double monthlyPayment) {
        super(dateOfContract, customerName, customerEmail, vehicleSold);
        this.salesTaxAmount = salesTaxAmount;
        this.recordingFee = recordingFee;
        this.processingFee = processingFee;
        this.finance = finance;
    }

    public void setSalesTaxAmount(double salesTaxAmount) {
        this.salesTaxAmount = salesTaxAmount;
    }

    public void setRecordingFee(double recordingFee) {
        this.recordingFee = recordingFee;
    }

    public void setProcessingFee(double processingFee) {
        this.processingFee = processingFee;
    }

    public SalesContract(String dateOfContract, String customerName, String customerEmail){
    }

    public double getSalesTaxAmount() {
        return salesTaxAmount;
    }

    public double getRecordingFee() {
        return recordingFee;
    }

    public double getProcessingFee() {
        Vehicle vehicle = super.getVehicleSold();

        if (vehicle.getPrice() > 10000){
            return 495;
        }
        else {
            return 295;
        }
    }

    public boolean isFinance() {
        return finance;
    }

    public void setFinance(boolean finance) {
        this.finance = finance;
    }


    @Override
    public double getTotalPrice(){

        Vehicle vehicle = super.getVehicleSold();
        double basePrice = vehicle.getPrice();
        double salesTax = basePrice * salesTaxAmount;

        return basePrice + salesTax + this.recordingFee + getProcessingFee();
    }

    @Override
    public double getMonthlyPayment(){

        Vehicle vehicle = super.getVehicleSold();
        double basePrice = vehicle.getPrice();
        double totalPrice = getTotalPrice();

        double loanMonthlyRate;
        int loanMonthlyTerm;

        if(finance){
            if(basePrice >= 10000) {
                loanMonthlyRate = 0.0425;
                loanMonthlyTerm = 48;
            }
            else {
                loanMonthlyRate = 0.0525;
                loanMonthlyTerm = 24;
            }
            double monthlyRate = loanMonthlyRate / 12;

            return (totalPrice * monthlyRate) / (1 - Math.pow(1 + monthlyRate, - loanMonthlyTerm));
        }
        return basePrice;
    }
}
