package ru.spbstu.main.shapes;

public interface Point {

    float getX();
    float getY();
    void setX(float x);
    void setY(float y);

    default String string() {
        return ("[" + getX() + ", " + getY() + "]");
    }
}
