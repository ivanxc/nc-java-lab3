package com.ivanxc.netcracker.lab.сhapter3;

public class Sheet {
    private String name;
    private int height;
    private int width;

    public Sheet() {
        this.name = "A0";
        this.width = 841;
        this.height = 1189;
    }

    public Sheet cutInHalf() {
        if (name.equals("A10")) {
            throw new IllegalStateException("No way to cut A10");
        }

        Sheet cutSheet = new Sheet();
        int newNumber = Integer.parseInt(name.substring(1)) + 1;
        cutSheet.name = "A" + newNumber;

        if (width > height) {
            cutSheet.width = width / 2;
            cutSheet.height = height;
        } else {
            cutSheet.height = height / 2;
            cutSheet.width = width;
        }
        return cutSheet;
    }

    public String name() {
        return name;
    }

    public int height() {
        return height;
    }

    public int width() {
        return width;
    }
}
