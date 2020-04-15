package ru.spbstu.main;

public class Generator implements Runnable, Communication {

    private final int MAX_COUNT = 100;

    private Cabinet cabinet;
    private int count;

    private Console console;

    public Generator(Cabinet cabinet, Console console) {
        this.cabinet = cabinet;
        this.console = console;
    }

    @Override
    public void run() {
        count = 0;
        while (count < MAX_COUNT) {
            count++;
            cabinet.addStudent(new Student());
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String getColor() {
        if (count < MAX_COUNT/2) {
            return ColorType.RED.getColor();
        } else if (count < MAX_COUNT - (MAX_COUNT/10)) {
            return ColorType.YELLOW.getColor();
        } else {
            return ColorType.GREEN.getColor();
        }
    }

    @Override
    public StatusType getStatus() {
        if (count != MAX_COUNT) {
            return StatusType.WORKS;
        } else {
            return StatusType.COMPLETED;
        }
    }

    @Override
    public String info() {
        String state = "";
        switch (getStatus()) {
            case WORKS: state = "    " + ColorType.RED.getColor() + getStatus() + ColorType.RESET.getColor() +
                    "     |"; break;
            case COMPLETED: state = "  "  + ColorType.GREEN.getColor() +  getStatus() + ColorType.RESET.getColor() +
                    "   |"; break;
        }
        String space = "   ";
        String progress = count + "/" + MAX_COUNT;
        while ((space + progress + space).length() < 13) {
            space += ' ';
        }
        if ((space + progress + space + '|').length() == 14) {
            return "|   Generator   |" + state + getColor() + space + progress + space +
                    ColorType.RESET.getColor() + " |";
        }
        return "|   Generator   |" + state + getColor() + space + progress + space +
                ColorType.RESET.getColor() + '|';
    }
}