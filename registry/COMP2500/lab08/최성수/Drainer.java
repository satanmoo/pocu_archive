package academy.pocu.comp2500.lab8;

public class Drainer extends SmartDevice implements IDrainable, IWaterDetectable {
    private static final int DRAIN_WATER_AMOUNT_PER_ONE_TICK = 7;
    private final int threshold;

    public Drainer(int threshold) {
        this.threshold = threshold;
    }

    @Override
    public void drain(Planter planter) {
        if (planter != null && isOn()) {
            setChangeWaterAmount(-DRAIN_WATER_AMOUNT_PER_ONE_TICK);
        }
    }

    @Override
    public void onTick() {
        upTicksSinceLastUpdate();
        detect(getPlanter().getWaterAmount());
    }

    @Override
    public void detect(int waterLevel) {
        if (waterLevel >= threshold) {

            if (!isOn()) {
                setOn();
            }

            drain(getPlanter());
        } else {

            if (isOn()) {
                setOff();
            }
        }
    }
}
