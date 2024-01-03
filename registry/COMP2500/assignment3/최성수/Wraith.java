package academy.pocu.comp2500.assignment3;

import java.util.ArrayList;

public class Wraith extends Unit implements IMovable, IThinkable {
    private final IntVector2D initialPosition;
    private int frame;
    private int beBeatFrame = Integer.MIN_VALUE;
    private boolean isUnbeatable = true;


    public Wraith(IntVector2D position) {
        super(position);
        initialPosition = position.copy();
        super.symbol = 'W';
        super.vision = 4;
        super.aoe = 0;
        super.ap = 6;
        super.hp = 80;
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

    public void onAttacked(int damage) {
        if (isUnbeatable) {
            beBeatFrame = frame;
            return;
        }
        hp = Math.max(0, hp - damage);
    }

    @Override
    public void onSpawn() {
        SimulationManager.getInstance().registerThinkable(this);
        SimulationManager.getInstance().registerMovable(this);
        SimulationManager.getInstance().registerAirUnit(this);
    }

    @Override
    public void determineAction() {
        if (isUnbeatable && frame == beBeatFrame) {
            isUnbeatable = false;
        }
        ++frame;

        super.canAttackUnits = SimulationManager.getInstance().getUnits();

        ArrayList<Unit> airUnits = SimulationManager.getInstance().getAirUnits();
        ArrayList<Unit> groundUnits = SimulationManager.getInstance().getVisibleGroundUnits();

        ArrayList<Unit> airTargets = findUnitsToAttack(airUnits, getAttackablePositions());
        ArrayList<Unit> groundTargets = findUnitsToAttack(groundUnits, getAttackablePositions());
        ArrayList<Unit> targets = airTargets;
        if (targets.size() == 0) {
            targets = groundTargets;
        }

        if (targets.size() == 0) {
            canMove = true;
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

        ArrayList<Unit> airUnits = SimulationManager.getInstance().getAirUnits();
        ArrayList<Unit> groundUnits = SimulationManager.getInstance().getVisibleGroundUnits();

        ArrayList<Unit> units = null;
        if (isTargetInVisible(airUnits)) {
            units = airUnits;
        } else if (isTargetInVisible(groundUnits)) {
            units = groundUnits;
        }

        if (units == null) {
            // initialPosition
            int y = initialPosition.getY() - position.getY();
            int x = initialPosition.getX() - position.getX();

            if (y != 0) {
                int flag = y > 0 ? 1 : -1;
                return new MoveIntent(this, new IntVector2D(position.getX(), position.getY() + flag));
            }
            if (x != 0) {
                int flag = x > 0 ? 1 : -1;
                return new MoveIntent(this, new IntVector2D(position.getX() + flag, position.getY()));
            }

            return null;
        }

        ArrayList<Unit> inVisionUnits = super.getTargetsInVisible(units);
        ArrayList<Unit> nearestUnits = super.findNearestUnits(inVisionUnits);
        ArrayList<Unit> weakestUnits = super.findWeakestUnits(nearestUnits);
        IntVector2D vectorToMove = super.findVectorToMove(weakestUnits);
        return new MoveIntent(this, vectorToMove);
    }
}
