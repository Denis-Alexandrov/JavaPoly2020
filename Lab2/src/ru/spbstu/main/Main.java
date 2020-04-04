package ru.spbstu.main;

import ru.spbstu.main.shapes.*;

public class Main {

    public static void maxArea(Shape shapes[]) {
        float maxArea = shapes[0].getArea();
        int index = 0;
        for (int i = 1; i < shapes.length; i++) {
            if (shapes[i].getArea() > maxArea) {
                maxArea = shapes[i].getArea();
                index = i;
            }
        }
        System.out.print("Element with maximum area: shapes[" + index + "]: ");
        shapes[index].info();
    }

    public static void main(String[] args) {

        Shape[] shapes = new Shape[10];
        shapes[0] = new Circle(new Point_t(1.0f, 1.0f), 3.0f);
        shapes[1] = new Circle(new Point_t(3.0f, 5.0f), 5.0f);
        shapes[2] = new Circle(new Point_t(-1.0f, -5.5f), 4.0f);
        shapes[3] = new Rectangle(new Point_t(30.5f, 12.5f), 5.0f, 5.0f, 50);
        shapes[4] = new Rectangle(new Point_t(2.55f, 1.54f), 3.0f, 2.0f, 180);
        shapes[5] = new Rectangle(new Point_t(30.5f, 12.5f), 2.0f, 50.5f, 720);
        shapes[6] = new Rectangle(new Point_t(0.0f, 0.0f), 1.0f, 10.0f, 3600);
        shapes[7] = new Triangle(new Point_t(0.0f, 0.0f), new Point_t(0.f, 3.0f),
                new Point_t(3.0f, 0.0f));
        shapes[8] = new Triangle(new Point_t(0.0f, 0.0f), new Point_t(0.0f, 3.0f),
                new Point_t(3.0f, 0.0f), 90);
        shapes[9] = new Triangle(new Point_t(0.0f, 0.0f), new Point_t(0.0f, 3.0f),
                new Point_t(3.0f, 0.0f), 180);
        maxArea(shapes);
    }
}
