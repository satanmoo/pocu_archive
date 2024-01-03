package academy.pocu.comp2500.assignment3;

import java.util.ArrayList;

public class Tank extends Unit implements IMovable, IThinkable {
    private TankMode mode = TankMode.TANK;
    private int preMoveDirection = 1;

    public Tank(IntVector2D position) {
        super(position);
        super.symbol = 'T';
        super.vision = 3;
        super.aoe = 1;
        super.ap = 8;
        super.hp = 85;
    }

    @Override
    public void onSpawn() {
        SimulationManager.getInstance().registerMovable(this);
        SimulationManager.getInstance().registerThinkable(this);
        SimulationManager.getInstance().registerGroundUnit(this);
    }

    @Override
    public void onAttacked(int damage) {
        if (mode == TankMode.SIEGE) {
            hp = Math.max(0, hp - damage * 2);
            return;
        }
        hp = Math.max(0, hp - damage);
    }

    private ArrayList<IntVector2D> getAttackablePositions() {
        ArrayList<IntVector2D> positions = new ArrayList<>();
        int x = position.getX();
        int y = position.getY();
        if (y - 2 >= 0) {
            positions.add(new IntVector2D(x, y - 2));
        }
        if (y - 2 >= 0 && x + 1 < 16) {
            positions.add(new IntVector2D(x + 1, y - 2));
        }
        if (y - 1 >= 0 && x + 2 < 16) {
            positions.add(new IntVector2D(x + 2, y - 1));
        }
        if (x + 2 < 16) {
            positions.add(new IntVector2D(x + 2, y));
        }
        if (y + 1 < 8 && x + 2 < 16) {
            positions.add(new IntVector2D(x + 2, y + 1));
        }
        if (y + 2 < 8 && x + 1 < 16) {
            positions.add(new IntVector2D(x + 1, y + 2));
        }
        if (y + 2 < 8) {
            positions.add(new IntVector2D(x, y + 2));
        }
        if (y + 2 < 8 && x - 1 >= 0) {
            positions.add(new IntVector2D(x - 1, y + 2));
        }
        if (y + 1 < 8 && x - 2 >= 0) {
            positions.add(new IntVector2D(x - 2, y + 1));
        }
        if (x - 2 >= 0) {
            positions.add(new IntVector2D(x - 2, y));
        }
        if (y - 1 >= 0 && x - 2 >= 0) {
            positions.add(new IntVector2D(x - 2, y - 1));
        }
        if (y - 2 >= 0 && x - 1 >= 0) {
            positions.add(new IntVector2D(x - 1, y - 2));
        }

        return positions;
    }

    @Override
    public void determineAction() {
        ArrayList<Unit> visibleGroundUnits = SimulationManager.getInstance().getVisibleGroundUnits(); // 자기 자신은?
        ArrayList<Unit> targets = findUnitsToAttack(visibleGroundUnits, getAttackablePositions());

        super.canAttackUnits = SimulationManager.getInstance().getGroundUnits();

        if (!isTargetInVisible(visibleGroundUnits)) {
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

        if (mode == TankMode.TANK) {
            mode = TankMode.SIEGE;
            return null;
        }

        // 시야는 있지만 공격 범위에 적이 없음
        if (targets.size() == 0) {
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

        if (mode == TankMode.SIEGE) {
            mode = TankMode.TANK;
            return null;
        }

        if (preMoveDirection == 1 && position.getX() + 1 < 16 || position.getX() - 1 < 0) {
            preMoveDirection = 1;
            return new MoveIntent(this, new IntVector2D(position.getX() + 1, position.getY()));
        }

        preMoveDirection = -1;
        return new MoveIntent(this, new IntVector2D(position.getX() - 1, position.getY()));
    }


}
