package com.ivanxc.netcracker.lab.сhapter3;

/**
 A cash register totals up sales and computes change due.
 */
public class CashRegister {
    private double purchase;
    private double taxablePurchase;
    private double taxRate;
    private double totalTax;
    private double payment;
    private int itemCount;

    /**
     Constructs a cash register with no money in it.
     */
    public CashRegister(double taxRate) {
        this.taxRate = taxRate;
    }

    /**
     Records the sale of an item.
     @param amount the price of the item
     */
    public void recordPurchase(double amount) {
        purchase = purchase + amount;
        itemCount++;
    }

    /**
     Processes a payment received from the customer.
     @param amount the amount of the payment
     */
    public void receivePayment(double amount) {
        payment = payment + amount;
    }

    /**
     Computes the change due and resets the machine for the next customer.
     @return the change due to the customer
     */
    public double giveChange() {
        double change = payment - purchase - taxablePurchase;
        totalTax += taxablePurchase * taxRate * 0.01;
        purchase = 0;
        taxablePurchase = 0;
        payment = 0;
        itemCount = 0;
        return change;
    }

    public void recordTaxablePurchase(double amount) {
        taxablePurchase += amount;
        itemCount++;
    }

    public double getTotalTax() {
        return totalTax;
    }

    public int getItemCount() {
        return itemCount;
    }
}