package academy.pocu.comp2500.lab8;

public abstract class SmartDevice {
    private boolean on;
    private int ticksSinceLastUpdate;
    private Planter planter;
    private int changeWaterAmount;

    public int getChangeWaterAmount() {
        return on ? changeWaterAmount : 0;
    }

    public void setChangeWaterAmount(int changeWaterAmount) {
        this.changeWaterAmount = changeWaterAmount;
    }

    public boolean isOn() {
        return on;
    }

    protected void setOn() {
        on = true;
        ticksSinceLastUpdate = 0;
    }

    protected void setOff() {
        on = false;
        ticksSinceLastUpdate = 0;
    }


    public abstract void onTick();

    public int getTicksSinceLastUpdate() {
        return ticksSinceLastUpdate;
    }

    protected void upTicksSinceLastUpdate() {
        ticksSinceLastUpdate++;
    }

    public Planter getPlanter() {
        return planter;
    }

    public void setPlanter(Planter planter) {
        this.planter = planter;
    }
}
