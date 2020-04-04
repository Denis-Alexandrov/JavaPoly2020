package ru.spbstu.main.shapes;

public interface Shape {

    float getArea();
    void info();

    default int getRotation() {
        return 0;
    }
    default void rotation(int angle) {

    }
}
