package academy.pocu.comp2500.assignment3;

import java.util.ArrayList;

public class Marine extends Unit implements IMovable, IThinkable {
    public Marine(IntVector2D position) {
        super(position);
        super.symbol = 'M';
        super.vision = 2;
        super.aoe = 0;
        super.ap = 6;
        super.hp = 35;
    }

    @Override
    public void onSpawn() {
        SimulationManager.getInstance().registerThinkable(this);
        SimulationManager.getInstance().registerMovable(this);
        SimulationManager.getInstance().registerGroundUnit(this);
    }

    private ArrayList<IntVector2D> getAttackablePositions() {
        ArrayList<IntVector2D> positions = new ArrayList<>();
        int x = position.getX();
        int y = position.getY();
        positions.add(new IntVector2D(x, y));
        if (y - 1 >= 0) {
            positions.add(new IntVector2D(x, y - 1));
        }
        if (x + 1 < 16) {
            positions.add(new IntVector2D(x + 1, y));
        }
        if (y + 1 < 8) {
            positions.add(new IntVector2D(x, y + 1));
        }
        if (x - 1 >= 0) {
            positions.add(new IntVector2D(x - 1, y));
        }
        return positions;
    }

    @Override
    public void determineAction() {
        ArrayList<Unit> visibleUnits = SimulationManager.getInstance().getVisibleUnits();
        ArrayList<Unit> targets = findUnitsToAttack(visibleUnits, getAttackablePositions());

        super.canAttackUnits = SimulationManager.getInstance().getUnits();

        if (targets.size() == 0) {
            canMove = true;
            super.targets = null;
        } else {
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

    @Override
    public MoveIntent move() {

        if (!canMove) {
            return null;
        }

        canMove = false;

        ArrayList<Unit> units = SimulationManager.getInstance().getVisibleUnits();

        ArrayList<Unit> targets = super.getTargetsInVisible(units);
        if (targets.size() == 0) {
            return null;
        }

        ArrayList<Unit> nearestUnits = super.findNearestUnits(targets);
        ArrayList<Unit> weakestUnits = super.findWeakestUnits(nearestUnits);
        IntVector2D vectorToMove = super.findVectorToMove(weakestUnits);
        return new MoveIntent(this, vectorToMove);
    }


}
