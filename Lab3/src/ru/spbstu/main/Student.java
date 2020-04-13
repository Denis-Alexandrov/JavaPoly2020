package ru.spbstu.main;

import java.util.Random;

public class Student {

    private SubjectType type;
    private int load;
    private int progress;

    public Student() {
        Random random = new Random();
        int[] load = {10, 20, 100};
        this.type = SubjectType.values()[random.nextInt(SubjectType.values().length)];
        this.load = load[random.nextInt(load.length)];
        progress = 0;
    }

    public SubjectType getType() {
        return type;
    }

    public int getLoad() {
        return load;
    }

    public int getProgress() {
        return progress;
    }

    public void progress(int value) {
        this.progress += value;
    }

    public boolean checkProgress() {
        return progress < load;
    }
}
