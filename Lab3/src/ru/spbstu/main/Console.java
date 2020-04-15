package ru.spbstu.main;

import java.util.ArrayList;
import java.util.List;

public class Console {

    public List<Communication> elements;

    public Console() {
        elements = new ArrayList<>();
    }

    public synchronized void print() {
        //clear();
        System.out.println("|_____DEVICE____|____STATUS____|___PROGRESS___|");
        for (Communication element : elements) {
            System.out.println(element.info());
        }
    }

    //Метод проверят есть ли еще рабочие генераторы.
    public synchronized boolean checkGenerators() {
        for (Communication element : elements) {
            if (element.getClass().getSimpleName() == Generator.class.getSimpleName()) {
                if (element.getStatus() == StatusType.WORKS) {
                    return true;
                }
            }
        }
        return false;
    }

    //Метод возвращает количество роботов которые еще работают.
    public synchronized int getCountWorkingRobots() {
        int countWorkingRobots = 0;
        for (Communication element : elements) {
            if (element.getClass().getSimpleName() == Robot.class.getSimpleName()) {
                if (element.getStatus() == StatusType.WORKS) {
                    countWorkingRobots++;
                }
            }
        }
        return countWorkingRobots;
    }

    private void clear() {
        for (int i = 0; i < 25; i++) {
            System.out.println();
        }
    }

}
