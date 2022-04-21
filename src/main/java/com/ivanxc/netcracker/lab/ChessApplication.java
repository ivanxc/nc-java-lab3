package com.ivanxc.netcracker.lab;

import com.ivanxc.netcracker.lab.chapter8.ComboLock;
import com.ivanxc.netcracker.lab.chapter9.programmingprojects.chess.Chessboard;
import com.ivanxc.netcracker.lab.chapter9.programmingprojects.clock.Clock;
import com.ivanxc.netcracker.lab.chapter9.programmingprojects.clock.WorldClock;
import com.ivanxc.netcracker.lab.chapter9.workedexample.Employee;
import com.ivanxc.netcracker.lab.chapter9.workedexample.HourlyEmployee;
import com.ivanxc.netcracker.lab.chapter9.workedexample.Manager;
import com.ivanxc.netcracker.lab.chapter9.workedexample.SalariedEmployee;
import com.ivanxc.netcracker.lab.сhapter3.Battery;
import com.ivanxc.netcracker.lab.сhapter3.CashRegister;
import java.util.Scanner;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ChessApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(
            getClass().getResource("view/game.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 500);
        stage.setTitle("Шахматы на двоих");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();

        Chessboard chessboard = new Chessboard();
        System.out.println(chessboard);

//        cashRegisterTest();
//        StudentTester.test();
//        batteryTest();
//        comboLockTest();
//        clockTest();
    }

    public static void cashRegisterTest() {
        CashRegister cashRegister = new CashRegister(20);
        cashRegister.recordPurchase(100);
        System.out.println(cashRegister.getItemCount());
        cashRegister.recordPurchase(200);
        System.out.println(cashRegister.getItemCount());
        cashRegister.receivePayment(310);
        System.out.println(cashRegister.giveChange());
        System.out.println(cashRegister.getTotalTax());
        System.out.println(cashRegister.getItemCount());
        System.out.println("-===");
        cashRegister.recordPurchase(100);
        cashRegister.recordTaxablePurchase(200);
        cashRegister.receivePayment(290);
        System.out.println(cashRegister.giveChange());
        System.out.println(cashRegister.getTotalTax());
    }

    public static void batteryTest() {
        Battery battery = new Battery(4000);
        System.out.println(battery.getRemainingCapacity());
        battery.drain(200);
        System.out.println(battery.getRemainingCapacity());
        battery.drain(3400);
        System.out.println(battery.getRemainingCapacity());
        battery.charge();
        System.out.println(battery.getRemainingCapacity());
    }

    public static void comboLockTest() {
        ComboLock comboLock = new ComboLock(22, 14, 30);
        System.out.println(comboLock.open());
        comboLock.turnRight(22);
        comboLock.turnLeft(8);
        comboLock.turnRight(16);
        System.out.println(comboLock.open());
    }

    public static void employeeTest()  {
        Employee[] staff = new Employee[3];
        staff[0] = new HourlyEmployee("Morgan, Harry", 30);
        staff[1] = new SalariedEmployee("Lin, Sally", 52000);
        staff[2] = new Manager("Smith, Mary", 104000, 50);
        Scanner in = new Scanner(System.in);
        for (Employee e : staff) {
            System.out.print("Hours worked by " + e.getName() + ": ");
            int hours = in.nextInt();
            System.out.println("Salary: " + e.weeklyPay(hours));
        }
    }

    public static void clockTest() {
        Clock clock = new Clock();
        clock.setAlarm(21, 20);
        System.out.println(clock.getHours());
        System.out.println(clock.getMinutes());
        System.out.println(clock.getTime());
        WorldClock worldClock = new WorldClock(3);
        worldClock.setAlarm(21, 20);
        System.out.println(worldClock.getHours());
        System.out.println(worldClock.getMinutes());
        System.out.println(worldClock.getTime());
    }
}