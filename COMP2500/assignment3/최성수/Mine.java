package academy.pocu.comp2500.assignment3;

import java.util.ArrayList;

public class Mine extends Unit implements ICollisionEventListener {
    protected int explosionCount;
    protected AttackIntent detectedAttackIntent;

    public Mine(IntVector2D position, int explosionCount) {
        super(position);
        this.explosionCount = explosionCount;
        super.symbol = 'N';
        super.vision = 0;
        super.aoe = 0;
        super.ap = 10;
        super.hp = 1;
    }

    @Override
    public void onSpawn() {
        SimulationManager.getInstance().registerCollisionEventListener(this);
    }

    @Override
    public void explosion() {
        // 스마트마인 탐지로 이미 터짐
        if (detectedAttackIntent != null) {
            return;
        }

        ArrayList<Unit> units = SimulationManager.getInstance().getGroundUnits();

        for (Unit unit : units) {

            if (this != unit && unit.position.getX() == position.getX() && unit.position.getY() == position.getY()) {

                explosionCount = Math.max(0, explosionCount - 1);
            }
        }

        if (explosionCount == 0) {
            hp = 0;
            AttackIntent attackIntent = new AttackIntent(this, position, ap, aoe, units);
            attackIntent.attack();
        }
    }

    @Override
    public AttackIntent attack() {
        return null;
    }
}
