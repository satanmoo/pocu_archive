//----------------------Drainer.java----------------------

package academy.pocu.comp2500.lab8;

public class Drainer extends SmartDevice implements IDrainable, IWaterDetectable {
    private static final int DRAIN_WATER_AMOUNT = 7;
    private int operatingWaterAmount;
    private boolean using;

    public Drainer(int operatingWaterAmount) {
        this.operatingWaterAmount = operatingWaterAmount;
    }

    @Override
    public void onTick() {
        this.ticks++;

    }

    @Override
    public void operate(Planter planter) {
        detect(planter.getWaterAmount());

        if (isOn() == true) {
            drain(planter);
        }
    }

    @Override
    public void detect(final int waterLevel) {
        this.using = (this.operatingWaterAmount <= waterLevel ? true : false);

        this.ticksSwitchingPower = (this.powerOn != this.using ? this.ticks : this.ticksSwitchingPower);
        this.powerOn = (this.using == true ? true : false);
    }

    @Override
    public void drain(Planter planter) {
        planter.setWaterAmount(planter.getWaterAmount() - DRAIN_WATER_AMOUNT);
    }
}


//----------------------IDrainable.java----------------------

package academy.pocu.comp2500.lab8;

public interface IDrainable {
    void drain(Planter planter);
}


//----------------------ISprayable.java----------------------

package academy.pocu.comp2500.lab8;

public interface ISprayable {
    void spray(Planter planter);
}


//----------------------IWaterDetectable.java----------------------

package academy.pocu.comp2500.lab8;

public interface IWaterDetectable {
    void detect(final int waterLevel);
}


//----------------------Planter.java----------------------

package academy.pocu.comp2500.lab8;

import java.util.ArrayList;

public class Planter {
    private static final int USED_WATER_AMOUNT = 2;
    private int waterAmount;
    private ArrayList<SmartDevice> smartDevices = new ArrayList<>();

    public Planter(int waterAmount) {
        this.waterAmount = waterAmount;
    }

    public Planter(Planter planter) {
        this.waterAmount = planter.waterAmount;
        this.smartDevices = planter.smartDevices;
    }

    public int getWaterAmount() {
        return this.waterAmount;
    }

    public void setWaterAmount(int waterAmount) {
        this.waterAmount = waterAmount;
    }

    public void installSmartDevice(SmartDevice smartDevice) {
        this.smartDevices.add(smartDevice);
    }

    public void tick() {
        Planter copyPlanter = new Planter(this);
        int currentWaterAmount = this.waterAmount;
        int diffWaterAmount = 0;

        for (SmartDevice smartDevice : this.smartDevices) {
            smartDevice.onTick();
            smartDevice.operate(copyPlanter);

            if (copyPlanter.waterAmount != currentWaterAmount) {
                diffWaterAmount += copyPlanter.waterAmount - currentWaterAmount;
                copyPlanter.waterAmount = currentWaterAmount;
            }
        }
        currentWaterAmount = diffWaterAmount + currentWaterAmount;
        currentWaterAmount = (currentWaterAmount - USED_WATER_AMOUNT < 0 ? 0 : currentWaterAmount - USED_WATER_AMOUNT);
        this.waterAmount = currentWaterAmount;
    }
}


//----------------------Schedule.java----------------------

package academy.pocu.comp2500.lab8;

public class Schedule {
    private final int startTicks;
    private final int durationTicks;

    public Schedule(final int startTicks, final int durationTicks) {
        this.startTicks = startTicks;
        this.durationTicks = durationTicks;
    }

    public int getStartTicks() {
        return this.startTicks;
    }

    public int getDurationTicks() {
        return this.durationTicks;
    }

    public int getEndTicks() {
        return this.startTicks + this.durationTicks;
    }
}


//----------------------SmartDevice.java----------------------

package academy.pocu.comp2500.lab8;

public abstract class SmartDevice {
    protected boolean powerOn;
    protected int ticks;
    protected int ticksSwitchingPower;

    public boolean isOn() {
        return this.powerOn;
    }

    public abstract void onTick();

    public int getTicksSinceLastUpdate() {
        return (this.ticks - this.ticksSwitchingPower);
    }

    protected void updateTicksSwitchingPower() {
        this.ticksSwitchingPower = this.ticks;
    }

    public abstract void operate(Planter planter);
}


//----------------------Sprinkler.java----------------------

package academy.pocu.comp2500.lab8;

import java.util.ArrayList;

public class Sprinkler extends SmartDevice implements ISprayable {
    private static final int SPRAY_WATER_AMOUNT = 15;
    private ArrayList<Schedule> schedules = new ArrayList<>();
    private Schedule schedule;

    public Sprinkler() {
    }

    private void nextSchedule() {
        int scheduleIndex = this.schedules.indexOf(this.schedule);
        while (true) {
            if (this.schedules.size() > ++scheduleIndex) {
                this.schedule = this.schedules.get(scheduleIndex);

            } else {
                this.schedule = null;
                break;
            }

            if (this.schedule.getEndTicks() > this.ticks) {
                break;
            }
        }
    }

    @Override
    public void onTick() {
        this.ticks++;

        if (this.ticks == 0
                || this.schedules.isEmpty() == true
                || this.schedule == null) {
            return;
        }

        if (this.ticks == this.schedule.getStartTicks()
                && this.powerOn == false) {
            this.powerOn = true;
            updateTicksSwitchingPower();
        }

        if (this.ticks == this.schedule.getEndTicks()) {
            if (this.powerOn == true) {
                this.powerOn = false;
                updateTicksSwitchingPower();
            }
            nextSchedule();
        }
    }

    @Override
    public void operate(Planter planter) {
        if (isOn() == true) {
            spray(planter);
        }
    }

    @Override
    public void spray(Planter planter) {
        planter.setWaterAmount(planter.getWaterAmount() + SPRAY_WATER_AMOUNT);
    }

    public void addSchedule(Schedule schedule) {
        if (schedule.getStartTicks() != 0) {
            if (this.schedules.isEmpty() && this.schedule == null) {
                this.schedule = schedule;
            }
            this.schedules.add(schedule);
        }
    }
}


