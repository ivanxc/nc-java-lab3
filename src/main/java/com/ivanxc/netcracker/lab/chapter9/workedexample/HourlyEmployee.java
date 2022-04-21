package com.ivanxc.netcracker.lab.chapter9.workedexample;

public class HourlyEmployee extends Employee {
    private double hourlyWage;
    public HourlyEmployee(String name, double wage) {
        this.name = name;
        hourlyWage = wage;
    }

    @Override
    public double weeklyPay(int hoursWorked) {
        double pay = hoursWorked * hourlyWage;
        if (hoursWorked > 40) {
            // Add overtime
            pay = pay + ((hoursWorked - 40) * 0.5) * hourlyWage;
        }
        return pay;
    }
}
