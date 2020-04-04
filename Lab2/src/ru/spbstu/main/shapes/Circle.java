package ru.spbstu.main.shapes;

public class Circle implements Ellipse, Shape {

    private Point_t center;
    private float radius;

    public Circle(Point_t center, float radius) {
        if (radius <= 0) {
            throw new IllegalArgumentException("Incorrect radius. Radius = " + radius + ". radius must be greater than zero.");
        }
        this.center = center;
        this.radius = radius;
    }

    @Override
    public float getLength() {
        return (float) Math.PI * radius * 2;
    }

    @Override
    public float getArea() {
        return (float) Math.PI * radius * radius;
    }

    @Override
    public void info() {
        System.out.println("Circle. | POS: " + center.string());
        System.out.println("   Area: " + getArea() + " | Angle: " + getRotation());
        System.out.println("   Radius: " + radius + " | Length: " + getLength() + "\n");
    }
}
