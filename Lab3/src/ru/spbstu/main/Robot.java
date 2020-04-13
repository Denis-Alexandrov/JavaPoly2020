package ru.spbstu.main;

public class Robot implements Runnable, Communication{

    private final int PERFORMANCE = 5;

    private Cabinet cabinet;
    private SubjectType type;

    private Console console;
    private String progress;


    public Robot(Cabinet cabinet, SubjectType type, Console console) {
        this.cabinet = cabinet;
        this.type = type;
        this.console = console;
        progress = "-/-";
    }

    @Override
    public void run() {
        while (true) {
            try {
                Student student = cabinet.getStudent(type);
                if (student != null) {
                    while (student.checkProgress()) {
                        Thread.sleep(500);
                        student.progress(PERFORMANCE);
                        progress = student.getProgress() + "/" + student.getLoad();
                        console.print();
                    }
                    progress = "-/-";
                    console.print();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String getColor() {
        if (progress != "-/-") {
            float number1 = Float.parseFloat(progress.substring(0, progress.indexOf('/')));
            float number2 = Float.parseFloat(progress.substring(progress.indexOf('/') + 1));
            if (number1 / number2 < 0.5) {
                return ColorType.RED.getColor();
            } else if (number1 / number2 < 0.75) {
                return ColorType.YELLOW.getColor();
            } else {
                return ColorType.GREEN.getColor();
            }
        }
        return "";
    }

    @Override
    public StatusType getStatus() {
        if (progress != "-/-") {
            return StatusType.WORKS;
        } else {
            return StatusType.COMPLETED;
        }
    }

    @Override
    public String info() {
        String name = "";
        switch (type) {
            case HIGH_MATH: name = "|Robot_" + type + "|"; break;
            case PHYSICS: name = "| Robot_" + type + " |"; break;
            case OOP: name = "|   Robot_" + type + "   |"; break;
        }
        String state = "";
        switch (getStatus()) {
            case WORKS: state = "    " + ColorType.RED.getColor() + getStatus() + ColorType.RESET.getColor() +
                    "     |"; break;
            case COMPLETED: state = "  "  + ColorType.GREEN.getColor() +  getStatus() + ColorType.RESET.getColor() +
                    "   |"; break;
        }
        String space = "   ";
        while ((space + progress + space).length() < 13) {
            space += ' ';
        }
        if ((space + progress + space + '|').length() == 14) {
            return name + state + space + getColor() + progress + ColorType.RESET.getColor() + space + " |";
        }
        return name + state + space + getColor() + progress + ColorType.RESET.getColor() + space + '|';
    }
}
