package ru.spbstu.main;

public enum ColorType {
    RESET("\u001B[0m"),
    RED("\u001B[31m"),
    GREEN("\u001B[32m"),
    YELLOW("\u001B[33m");

    private String color;

    ColorType(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }
}
