package ru.spbstu.main;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {

        Console console = new Console();
        Cabinet cabinet = new Cabinet(console);
        Generator studentGenerator = new Generator(cabinet, console);
        Robot robot1 = new Robot(cabinet, SubjectType.HIGH_MATH, console);
        Robot robot2 = new Robot(cabinet, SubjectType.PHYSICS, console);
        Robot robot3 = new Robot(cabinet, SubjectType.OOP, console);
        console.elements.add(cabinet);
        console.elements.add(studentGenerator);
        console.elements.add(robot1);
        console.elements.add(robot2);
        console.elements.add(robot3);
        ExecutorService service = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        service.execute(studentGenerator);
        service.execute(robot1);
        service.execute(robot2);
        service.execute(robot3);
    }
}
