package academy.pocu.comp2500.lab8;

public class Schedule {
    private int startTick;
    private int offsetTick;

    public Schedule(int startTick, int offsetTick) {
        this.startTick = startTick;
        this.offsetTick = offsetTick;
    }

    public int getStartTick() {
        return startTick;
    }

    public int getOffsetTick() {
        return offsetTick;
    }
}
