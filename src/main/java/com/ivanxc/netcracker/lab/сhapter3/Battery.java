package com.ivanxc.netcracker.lab.сhapter3;

public class Battery {
    private double capacity;
    private double remainingCapacity;

    public Battery(double capacity) {
        this.capacity = capacity;
        remainingCapacity = capacity;
    }

    public void drain(double amount) {
        if (Double.compare(amount,remainingCapacity) > 0) {
            throw new IllegalArgumentException("There is not enough charge");
        }
        remainingCapacity -= amount;
    }

    public void charge() {
        remainingCapacity = capacity;
    }

    public double getRemainingCapacity() {
        return remainingCapacity;
    }
}
