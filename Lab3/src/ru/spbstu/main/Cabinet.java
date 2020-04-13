package ru.spbstu.main;

import java.util.ArrayList;
import java.util.List;

public class Cabinet implements Communication {

    private final int MAX_CAPACITY = 5;
    private final int MIN_CAPACITY = 0;

    private List<Student> list;

    private Console console;

    public Cabinet(Console console) {
        list = new ArrayList<>();
        this.console = console;
    }

    public synchronized void addStudent(Student student) {
        if (list.size() < MAX_CAPACITY) {
            list.add(student);
            notifyAll();
        } else {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized Student getStudent(SubjectType type) {
        if (list.size() > MIN_CAPACITY) {
            for (Student student : list) {
                if (type == student.getType()) {
                    list.remove(student);
                    notifyAll();
                    return student;
                }
            }
        }
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getColor() {
        if (list.size() == MAX_CAPACITY) {
            return ColorType.RED.getColor();
        } else if (list.size() != MIN_CAPACITY) {
            return ColorType.YELLOW.getColor();
        } else {
            return ColorType.GREEN.getColor();
        }
    }

    @Override
    public StatusType getStatus() {
        if (list.size() == MAX_CAPACITY) {
            return StatusType.FULL;
        } else if (list.size() != MIN_CAPACITY) {
            return StatusType.NOT_EMPTY;
        } else {
            return StatusType.EMPTY;
        }
    }

    @Override
    public String info() {
        String state = "";
        switch (getStatus()) {
            case FULL: state = "     " + ColorType.RED.getColor() + getStatus() + ColorType.RESET.getColor() +
                    "     |"; break;
            case NOT_EMPTY: state = "  "  + ColorType.YELLOW.getColor() +  getStatus() + ColorType.RESET.getColor() +
                    "   |"; break;
            case EMPTY: state = "    " + ColorType.GREEN.getColor() + getStatus() + ColorType.RESET.getColor() +
                    "     |"; break;
        }
        return "|    Cabinet    |" + state + getColor() + "     " + list.size() + "/" + MAX_CAPACITY
                + ColorType.RESET.getColor() + "      |";
    }
}
