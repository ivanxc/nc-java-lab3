package com.ivanxc.netcracker.lab.chapter9.workedexample;

public abstract class Employee {
    protected String name;
    protected int hoursWorked;

    public abstract double weeklyPay(int hoursWorked);

    public String getName() {
        return name;
    }

    public void setName(String employeeName) {
        name = employeeName;
    }
}
