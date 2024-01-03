package academy.pocu.comp2500.assignment3;

import java.util.ArrayList;

public final class SimulationManager {
    private static SimulationManager instance;
    private final ArrayList<IThinkable> thinkableUnits = new ArrayList<>();
    private final ArrayList<IMovable> movableUnits = new ArrayList<>();
    private final ArrayList<ICollisionEventListener> collisionEventListeners = new ArrayList<>();
    private final ArrayList<Unit> groundUnits = new ArrayList<>();
    private final ArrayList<Unit> airUnits = new ArrayList<>();
    private Unit destoryer;

    public static SimulationManager getInstance() {
        if (instance == null) {
            instance = new SimulationManager();
        }
        return instance;
    }

    public ArrayList<Unit> getUnits() {
        ArrayList<Unit> units = new ArrayList<>();
        thinkableUnits.forEach(unit -> units.add((Unit) unit));

        collisionEventListeners.forEach(unit -> {
            if (!thinkableUnits.contains(unit)) {
                units.add((Unit) unit);
            }
        });
        return units;
    }

    public void registerGroundUnit(Unit unit) {
        groundUnits.add(unit);
    }


    public void registerAirUnit(Unit unit) {
        airUnits.add(unit);
    }

    public ArrayList<Unit> getVisibleGroundUnits() {
        return groundUnits;
    }

    public ArrayList<Unit> getGroundUnits() {
        ArrayList<Unit> units = new ArrayList<>(groundUnits);
        collisionEventListeners.forEach(unit -> units.add((Unit) unit));
        return units;
    }

    public ArrayList<Unit> getAirUnits() {
        return airUnits;
    }

    public ArrayList<Unit> getVisibleUnits() {
        ArrayList<Unit> units = new ArrayList<>();
        units.addAll(groundUnits);
        units.addAll(airUnits);
        if (destoryer != null) {
            units.add(destoryer);
        }
        return units;
    }

    public void spawn(Unit unit) {
        unit.onSpawn();
    }

    public void registerThinkable(IThinkable thinkable) {
        thinkableUnits.add(thinkable);
    }

    public void registerMovable(IMovable movable) { // 해병, 전차, 망령
        movableUnits.add(movable);
    }

    public void registerCollisionEventListener(ICollisionEventListener listener) { // 마인
        collisionEventListeners.add(listener);
    }

    public void setDestoryer(Unit destoryer) {
        this.destoryer = destoryer;
    }

    public void update() {
        for (IThinkable thinkable : thinkableUnits) {
            thinkable.determineAction();
        }


        //

        // 1. 유닛 행동 결정
        ArrayList<AttackIntent> attackIntents = new ArrayList<>();
        ArrayList<MoveIntent> moveIntents = new ArrayList<>();

        for (IThinkable thinkableUnit : thinkableUnits) {
            Unit unit = (Unit) thinkableUnit;
            AttackIntent attackIntent = unit.attack();
            if (attackIntent != null) {
                attackIntents.add(attackIntent);
            }
        }

        for (IMovable movableUnit : movableUnits) {
            MoveIntent moveIntent = movableUnit.move();
            if (moveIntent != null) {
                moveIntents.add(moveIntent);
            }
        }

        // 2. 움직임
        for (MoveIntent moveIntent : moveIntents) {
            moveIntent.move();
        }

        // 3. 마인 처리 (데미지 뺀다는 말) + 터지면 마인 hp 0으로 변경
        // listener 에게 전달
        // attack 바로할지, attackIntent 만들지?

        for (ICollisionEventListener collisionEventListener : collisionEventListeners) {
            collisionEventListener.explosion();
        }

        // 4. 공격
        for (AttackIntent attackIntent : attackIntents) {
            attackIntent.attack();
        }

        // 5. 죽은 것 처리
        thinkableUnits.removeIf(unit -> ((Unit) unit).hp == 0);
        movableUnits.removeIf(unit -> ((Unit) unit).hp == 0);
        collisionEventListeners.removeIf(unit -> ((Unit) unit).hp == 0);

        groundUnits.removeIf(unit -> (unit.hp == 0));
        airUnits.removeIf(unit -> (unit.hp == 0));
    }
}