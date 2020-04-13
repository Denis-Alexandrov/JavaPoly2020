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

    private void clear() {
        for (int i = 0; i < 25; i++) {
            System.out.println();
        }
    }

}
