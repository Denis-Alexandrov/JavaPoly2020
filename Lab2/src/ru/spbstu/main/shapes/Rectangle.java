package ru.spbstu.main.shapes;

public class Rectangle implements Polygon, Shape {

    private Point_t center;
    private float width;
    private float height;
    private int angle;

    public Rectangle(Point_t center, float width, float height, int angle) {
        if (width <= 0) {
            throw new IllegalArgumentException("Incorrect radius. Width = " + width + ". Width must be greater than zero.");
        }
        if (height <= 0) {
            throw new IllegalArgumentException("Incorrect radius. Height = " + height + ". Height must be greater than zero.");
        }
        this.center = center;
        this.width = width;
        this.height = height;
        this.angle = angle;
        if (this.angle >= 360) {
            rotation(-360);
        } else if (this.angle <= -360) {
            rotation(360);
        }
    }

    @Override
    public float getPerimeter() {
        return (width + height) * 2;
    }

    @Override
    public float getArea() {
        return width * height;
    }

    @Override
    public void info() {
        System.out.println("Rectangle. | POS: " + center.string());
        System.out.println("   Area: " + getArea() + " | Angle: " + getRotation());
        System.out.println("   Width: " + width + " | Height: " + height + " | Perimeter: " +
                getPerimeter() + "\n");
    }

    @Override
    public int getRotation() {
        return angle;
    }

    @Override
    public void rotation(int angle) {
        this.angle += angle;
        if (this.angle >= 360) {
            rotation(-360);
        } else if (this.angle <= -360) {
            rotation(360);
        }
    }
}
