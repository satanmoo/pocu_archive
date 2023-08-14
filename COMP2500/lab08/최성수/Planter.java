package academy.pocu.comp2500.lab8;

import java.util.ArrayList;

public class Planter {
    private int waterAmount;
    private ArrayList<SmartDevice> devices = new ArrayList<>();

    public Planter(int waterAmount) {
        this.waterAmount = waterAmount;
    }

    public int getWaterAmount() {
        return waterAmount;
    }

    public void installSmartDevice(SmartDevice smartDevice) {
        devices.add(smartDevice);
    }

    public void tick() {
        int changeWater = 0;
        for (SmartDevice device : devices) {
            device.setPlanter(this);
            device.onTick();
            changeWater += device.getChangeWaterAmount();
        }
        waterAmount = Math.max(0, waterAmount + changeWater - 2);
    }
}
