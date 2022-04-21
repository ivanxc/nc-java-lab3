package com.ivanxc.netcracker.lab.chapter9.workedexample;

public class SalariedEmployee extends Employee {
    private double annualSalary;

    public SalariedEmployee(String name, double salary)
    {
        setName(name);
        annualSalary = salary;
    }

    @Override
    public double weeklyPay(int hoursWorked)
    {
        final int WEEKS_PER_YEAR = 52;
        return annualSalary / WEEKS_PER_YEAR;
    }
}
