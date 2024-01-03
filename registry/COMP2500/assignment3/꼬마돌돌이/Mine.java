package academy.pocu.comp2500.assignment3;

import java.util.ArrayList;

public class Mine extends Unit implements ICollisionDetectable {
    private static final char SYMBOL = 'N';
    private static final UnitMobileType UNIT_MOBILE_TYPE = UnitMobileType.GROUND;
    private static final int VISION = 0;
    private static final int AOE = 0;
    private static final int AP = 10;
    private static final int MAX_HP = 1;
    private static final UnitAttackType UNIT_ATTACK_TYPE = UnitAttackType.GROUND;
    private static final UnitVisibility UNIT_VISIBILITY = UnitVisibility.INVISIBLE;

    // threshold [쓰레시홀드] 역치값 ; 어떤 현상을 일으키게 하기 위하여 가해야 하는 물리량의 최소치
    private final int explosionThreshold;
    private final ArrayList<Unit> collisionUnits = new ArrayList<>();

    public Mine(final IntVector2D position, final int explosionThreshold) {
        super(SYMBOL, UNIT_MOBILE_TYPE, VISION, AOE, AP, MAX_HP, UNIT_ATTACK_TYPE, position, UNIT_VISIBILITY, null);
        this.explosionThreshold = explosionThreshold;
    }

    protected Mine(
            final char symbol,
            final UnitMobileType unitMobileType,
            final int vision,
            final int aoe,
            final int ap,
            final int maxHp,
            final UnitAttackType unitAttackType,
            final IntVector2D position,
            final UnitVisibility unitVisibility,
            final int explosionThreshold
    ) {
        super(symbol, unitMobileType, vision, aoe, ap, maxHp, unitAttackType, position, unitVisibility, null);
        this.explosionThreshold = explosionThreshold;
    }

    @Override
    public void collisionDetect() {
        SimulationManager sm = SimulationManager.getInstance();

        for (Unit unit : sm.getUnits()) {
            if (super.getPosition().equals(unit.getPosition())) {
                if (unit == this) {
                    continue;
                }

                if (unit.getUnitMobileType() == UnitMobileType.GROUND) {
                    collisionUnits.add(unit);
                }
            }
        }

        if (collisionUnits.size() >= explosionThreshold) {
            this.attackIntentOrNull = new AttackIntent(super.getPosition(), super.getAp(), this);
            super.actionType = ActionType.ATTACK;
            return;
        }
    }

    @Override
    public AttackIntent attack() {
        if (super.actionType == ActionType.ATTACK) {
            AttackIntent attackIntent = attackIntentOrNull;

            this.attackIntentOrNull = null;
            super.actionType = ActionType.NOTHING;
            super.hp = 0;

            return attackIntent;
        }
        return null;
    }

    @Override
    public void onSpawn() {
        SimulationManager sm = SimulationManager.getInstance();
        sm.registerCollisionEventListener(this);
    }

    @Override
    public void onDeath() {
        SimulationManager sm = SimulationManager.getInstance();
        sm.unRegisterCollisionEventListener(this);
    }
}
