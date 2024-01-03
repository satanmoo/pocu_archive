package academy.pocu.comp2500.lab5;

public class Move {
    private static final int REST = 1;
    private static final int MIN_POWERPOINT = 0;
    private static final int DECREASE_PER_MOVE = 1;

    private final String moveName;
    private final int power;

    private final int maxPowerpoint;
    private int powerPoint;

    public Move(final String moveName, final int power, final int maxPowerpoint) {
        assert moveName != null;
        assert power > 0;
        assert maxPowerpoint > 0;

        this.moveName = moveName;
        this.power = power;
        this.maxPowerpoint = maxPowerpoint;
        this.powerPoint = maxPowerpoint;
    }

    public boolean isMovable() {
        return powerPoint != Move.MIN_POWERPOINT;
    }

    public int moveAndGetPower() {
        assert powerPoint > Move.MIN_POWERPOINT;

        // clamp ; powerPoint >= Move.MIN_POWERPOINT
        powerPoint = Math.max(Move.MIN_POWERPOINT, powerPoint - Move.DECREASE_PER_MOVE);
        return power;
    }

    public String getMoveName() {
        return moveName;
    }

    void rest() {
        // clamp ; powerPoint <= maxPowerpoint
        powerPoint = Math.min(maxPowerpoint, powerPoint + Move.REST);
    }
}
