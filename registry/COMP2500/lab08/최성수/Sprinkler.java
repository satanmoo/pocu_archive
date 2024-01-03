

package academy.pocu.comp2500.lab8;

import java.util.LinkedList;
import java.util.Queue;

public class Sprinkler extends SmartDevice implements ISprayable {
    private static final int SPRAY_WATER_AMOUNT_PER_TICK = 15;
    private int tick;
    private final Queue<Schedule> schedules = new LinkedList<>();

    public Sprinkler() {
    }

    @Override
    public void spray(Planter planter) {
        if (planter != null && isOn()) {
            setChangeWaterAmount(SPRAY_WATER_AMOUNT_PER_TICK);
        }
    }

    @Override
    public void onTick() {
        ++tick;
        upTicksSinceLastUpdate();

        Schedule cur = schedules.peek();

        if (isOn()) {
            assert cur != null;
            if (getTicksSinceLastUpdate() == cur.getOffsetTick()) {

                setOff();
                schedules.remove();
                while (schedules.peek() != null && schedules.peek().getStartTick() + schedules.peek().getOffsetTick() <= tick) {
                    schedules.remove();
                }
            }
        } else {

            if (cur != null) {

                while (true) {
                    if (cur.getStartTick() == 0 || cur.getStartTick() + cur.getOffsetTick() <= tick) {
                        schedules.remove();
                        cur = schedules.peek();
                        if (cur == null) {
                            return;
                        }
                    } else {
                        break;
                    }
                }



                if (cur.getStartTick() == tick) {
                    setOn();
                }
            }
        }
        spray(getPlanter());
    }

    public void addSchedule(Schedule schedule) {
        schedules.add(schedule);
    }
}

