package ru.spbstu.main.shapes;

public class Triangle implements Polygon, Shape{

    private Point_t vertex1;
    private Point_t vertex2;
    private Point_t vertex3;
    private Point_t center;
    private int angle;

    public Triangle(Point_t vertex1, Point_t vertex2, Point_t vertex3, int angle) {
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
        this.vertex3 = vertex3;
        if (getArea() == 0.0f) {
            throw new IllegalArgumentException("Points of triangle lie on one line.");
        }
        this.center = new Point_t((vertex1.getX() + vertex2.getX() + vertex3.getX()) / 3.0f,
                                  (vertex1.getY() + vertex2.getY() + vertex3.getY()) / 3.0f);
        this.angle = angle;
        if (this.angle >= 360) {
            rotation(-360);
        } else if (this.angle <= -360) {
            rotation(360);
        } else {
            rotation(this.angle);
        }
    }

    public Triangle(Point_t vertex1, Point_t vertex2, Point_t vertex3) {
        this(vertex1, vertex2, vertex3, 0);
    }

    @Override
    public float getPerimeter() {
        var side12 = (float) (Math.sqrt(Math.pow(vertex1.getX() - vertex2.getX(), 2)
                + Math.pow(vertex1.getY() - vertex2.getY(), 2)));
        var side23 = (float) (Math.sqrt(Math.pow(vertex2.getX() - vertex3.getX(), 2)
                + Math.pow(vertex2.getY() - vertex3.getY(), 2)));
        var side31 = (float) (Math.sqrt(Math.pow(vertex3.getX() - vertex1.getX(), 2)
                + Math.pow(vertex3.getY() - vertex1.getY(), 2)));

        return side12 + side23 + side31;
    }

    @Override
    public float getArea() {
        return Math.abs((vertex1.getX() - vertex3.getX()) * (vertex2.getY() - vertex3.getY()) -
                        (vertex2.getX() - vertex3.getX()) * (vertex1.getY() - vertex3.getY())) / 2;
    }

    @Override
    public void info() {
        System.out.println("Triangle. | POS: " + center.string());
        System.out.println("   Area: " + getArea() + " | Angle: " + getRotation());
        System.out.println("   vertex1: " + vertex1.string() + " | vertex2: " + vertex2.string() +
                " | vertex3: " + vertex3.string() + " | Perimeter: " + getPerimeter() + "\n");
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
        } else {
            float cosine = (float) Math.cos(angle * Math.PI / 180);
            float sinus = (float) Math.sin(angle * Math.PI / 180);
            float vertex1dx = (vertex1.getX() - center.getX()) * cosine - (vertex1.getY() - center.getY()) * sinus;
            float vertex1dy = (vertex1.getY() - center.getY()) * cosine + (vertex1.getX() - center.getX()) * sinus;
            float vertex2dx = (vertex2.getX() - center.getX()) * cosine - (vertex2.getY() - center.getY()) * sinus;
            float vertex2dy = (vertex2.getY() - center.getY()) * cosine + (vertex2.getX() - center.getX()) * sinus;
            float vertex3dx = (vertex3.getX() - center.getX()) * cosine - (vertex3.getY() - center.getY()) * sinus;
            float vertex3dy = (vertex3.getY() - center.getY()) * cosine + (vertex3.getX() - center.getX()) * sinus;
            vertex1.setX(vertex1dx + center.getX());
            vertex1.setY(vertex1dy + center.getY());
            vertex2.setX(vertex2dx + center.getX());
            vertex2.setY(vertex2dy + center.getY());
            vertex3.setX(vertex3dx + center.getX());
            vertex3.setY(vertex3dy + center.getY());
        }
    }
}
