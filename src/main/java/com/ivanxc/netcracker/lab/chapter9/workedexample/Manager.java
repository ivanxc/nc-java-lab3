package com.ivanxc.netcracker.lab.chapter9.workedexample;

public class Manager extends SalariedEmployee {
    private double weeklyBonus;

    public Manager(String name, double salary, double bonus) {
        super(name, salary);
        weeklyBonus = bonus;
    }

    @Override
    public double weeklyPay(int hours) {
        return super.weeklyPay(hours) + weeklyBonus;
    }
}