package com.ivanxc.netcracker.lab.chapter8;

public class ComboLock {
    private int secret1;
    private int secret2;
    private int secret3;
    private int currentNumber;
    private Status status;

    public ComboLock(int secret1, int secret2, int secret3) {
        this.secret1 = secret1;
        this.secret2 = secret2;
        this.secret3 = secret3;
        status = Status.LOCKED;
    }

    public void turnRight(int ticks) {
        currentNumber += ticks;
        currentNumber %= 40;

        if (currentNumber == secret1 && status == Status.LOCKED) {
            status = Status.INPUTED_SECRET1;
        } else if (currentNumber == secret3 && status == Status.INPUTED_SECRET2) {
            status = Status.INPUTED_SECRET3;
        }
    }

    public void turnLeft(int ticks) {
        currentNumber -= ticks % 40;
        if (currentNumber < 0) {
            currentNumber = 40 + currentNumber;
        }

        if (currentNumber == secret2 && status == Status.INPUTED_SECRET1) {
            status = Status.INPUTED_SECRET2;
        }
    }

    public boolean open() {
        if (status == Status.INPUTED_SECRET3) {
            status = Status.OPEN;
            return true;
        } else {
            reset();
            return false;
        }
    }

    public void reset() {
        status = Status.LOCKED;
        currentNumber = 0;
    }

    private enum Status {
        LOCKED,
        INPUTED_SECRET1,
        INPUTED_SECRET2,
        INPUTED_SECRET3,
        OPEN
    }
}
