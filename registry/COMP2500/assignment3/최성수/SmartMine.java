package academy.pocu.comp2500.assignment3;

import java.util.ArrayList;

public class SmartMine extends Mine implements IThinkable {
    private final int detectedExplosionCount;

    public SmartMine(IntVector2D position, int explosionCount, int detectedExplosionCount) {
        super(position, explosionCount);
        super.explosionCount = explosionCount;
        super.symbol = 'A';
        super.vision = 1;
        super.aoe = 1;
        super.ap = 15;
        super.hp = 1;

        this.detectedExplosionCount = detectedExplosionCount;
    }

    @Override
    public void onSpawn() {
        SimulationManager.getInstance().registerCollisionEventListener(this);
        SimulationManager.getInstance().registerThinkable(this);
    }

    @Override
    public void determineAction() {
        ArrayList<Unit> visibleUnits = SimulationManager.getInstance().getVisibleGroundUnits();
        ArrayList<Unit> detectedUnits = getTargetsInVisible(visibleUnits);

        ArrayList<Unit> canAttackUnits = SimulationManager.getInstance().getGroundUnits();

        if (detectedExplosionCount <= detectedUnits.size()) {
            detectedAttackIntent = new AttackIntent(this, position, ap, aoe, canAttackUnits);
            hp = 0;
        }
    }

    @Override
    public AttackIntent attack() {
        if (detectedAttackIntent != null) {
            return detectedAttackIntent;
        }

        return null;
    }
}
