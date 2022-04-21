package com.ivanxc.netcracker.lab.сhapter3;

public class Person {
    private String name;
    private String friendNames;
    private int friendCount;

    public Person(String name) {
        this.name = name;
    }

    public void befriend(Person p) {
        friendNames += " " + p.name;
        friendNames = friendNames.trim();
        friendCount++;
    }

    public void unfriend (Person p) {
        friendNames = friendNames
            .replace(p.name, "")
            .replace("  ", " ")
            .trim();
        friendCount--;
    }

    public String getFriendNames() {
        return friendNames;
    }

    public int getFriendCount() {
        return friendCount;
    }
}
