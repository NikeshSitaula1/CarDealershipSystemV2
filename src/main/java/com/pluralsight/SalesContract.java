package com.pluralsight;

public class SalesContract extends Contract {

    private double salesTaxAmount;
    private double recordingFee;
    private double processingFee;
    private boolean finance;


    public SalesContract(String dateOfContract, String customerName, String customerEmail,
                         Vehicle vehicleSold, double totalPrice, double monthlyPayment,
                         double salesTaxAmount, double recordingFee, double processingFee,
                         boolean finance) {
        super(dateOfContract, customerName, customerEmail, vehicleSold, totalPrice, monthlyPayment);
        this.salesTaxAmount = 0.05;
        this.recordingFee = 100;
        this.processingFee = processingFee;
        this.finance = finance;
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

        double loanRate;
        int loanMonth;

        if(finance){
            if(basePrice >= 10000) {
                loanRate = 0.0425;
                loanMonth = 48;
            }
            else {
                loanRate = 0.0525;
                loanMonth = 24;
            }
            double monthlyRate = loanRate / 12;

            return (totalPrice * monthlyRate) / (1 - Math.pow(1 + monthlyRate, - loanMonth));
        }
        return basePrice;
    }
}
