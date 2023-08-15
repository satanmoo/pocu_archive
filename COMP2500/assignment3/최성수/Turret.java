package academy.pocu.comp2500.assignment3;

import java.util.ArrayList;

public class Turret extends Unit implements IThinkable {
    public Turret(IntVector2D position) {
        super(position);
        super.symbol = 'U';
        super.vision = 2;
        super.aoe = 0;
        super.ap = 7;
        super.hp = 99;
    }

    private ArrayList<IntVector2D> getAttackablePositions() {
        ArrayList<IntVector2D> positions = new ArrayList<>();
        int x = position.getX();
        int y = position.getY();

        positions.add(new IntVector2D(x, y));
        if (y - 1 >= 0) {
            positions.add(new IntVector2D(x, y - 1));
        }
        if (y - 1 >= 0 && x + 1 < 16) {
            positions.add(new IntVector2D(x + 1, y - 1));
        }
        if (x + 1 < 16) {
            positions.add(new IntVector2D(x + 1, y));
        }
        if (y + 1 < 8 && x + 1 < 16) {
            positions.add(new IntVector2D(x + 1, y + 1));
        }
        if (y + 1 < 8) {
            positions.add(new IntVector2D(x, y + 1));
        }
        if (y + 1 < 8 && x - 1 >= 0) {
            positions.add(new IntVector2D(x - 1, y + 1));
        }
        if (x - 1 >= 0) {
            positions.add(new IntVector2D(x - 1, y));
        }
        if (y - 1 >= 0 && x - 1 >= 0) {
            positions.add(new IntVector2D(x - 1, y - 1));
        }

        return positions;
    }

    @Override
    public void onSpawn() {
        SimulationManager.getInstance().registerGroundUnit(this);
        SimulationManager.getInstance().registerThinkable(this);
    }

    @Override
    public void determineAction() {
        ArrayList<Unit> units = SimulationManager.getInstance().getAirUnits();
        ArrayList<Unit> targets = findUnitsToAttack(units, getAttackablePositions());

        super.canAttackUnits = units;

        if (targets.size() != 0) {
            super.targets = targets;
        }
    }

    @Override
    public AttackIntent attack() {
        if (targets == null) {
            return null;
        }

        IntVector2D attackPosition = super.findWeakestUnitVector(targets);
        return new AttackIntent(this, attackPosition, ap, aoe, canAttackUnits);
    }


}
