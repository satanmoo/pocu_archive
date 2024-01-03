package academy.pocu.comp2500.assignment3;

import java.util.ArrayList;

public final class Turret extends ThinkableUnit {
    private static final char SYMBOL = 'U';
    private static final UnitMobileType UNIT_MOBILE_TYPE = UnitMobileType.GROUND;
    private static final int VISION = 2;
    private static final int AOE = 0;
    private static final int AP = 7;
    private static final int MAX_HP = 99;
    private static final UnitAttackType UNIT_ATTACK_TYPE = UnitAttackType.SKY;
    private static final UnitVisibility UNIT_VISIBILITY = UnitVisibility.VISIBLE;
    private static final int ATTACK_VISION[][] = {{0, 0}, {0, -1}, {1, -1}, {1, 0}, {1, 1}, {0, 1}, {-1, 1}, {-1, 0}, {-1, -1}};

    private AttackIntent attackIntentOrNull = null;

    public Turret(final IntVector2D position, final int maxHp) {
        super(SYMBOL, UNIT_MOBILE_TYPE, VISION, AOE, AP, maxHp, UNIT_ATTACK_TYPE, position, UNIT_VISIBILITY, ATTACK_VISION);
    }

    public Turret(final IntVector2D position) {
        super(SYMBOL, UNIT_MOBILE_TYPE, VISION, AOE, AP, MAX_HP, UNIT_ATTACK_TYPE, position, UNIT_VISIBILITY, ATTACK_VISION);
    }

    @Override
    public void think() {

        // ======== step 1. 공격가능성 판정 ========
        // 현재 공격가능한 유닛들 리스트에 추가
        final ArrayList<Unit> attackCandidates = new ArrayList<>();
        super.createAttackCandidates(attackCandidates);

        if (attackCandidates.size() > 0) {
            super.actionType = ActionType.ATTACK;

            // 1. 가장 약한 유닛(HP가 가장 낮은 유닛)이 있는 타일을 공격
            boolean isActionRuleEnd = super.applyAttackRuleAndGetRuleEndStatus(RuleOfAttack.LOWEST_HP_ENEMY_TILE, attackCandidates);
            if (isActionRuleEnd) {
                this.attackIntentOrNull = new AttackIntent(attackCandidates.get(0).getPosition(), super.getAp(), this);
                return;
            }

            // 2. 자신의 위치에 유닛이 있다면 그 타일을 공격. 그렇지 않을 경우 북쪽(위쪽)에 유닛이 있다면 그 타일을 공격. 그렇지 않을 경우 시계 방향으로 검색하다 찾은 유닛의 타일을 공격
            super.applyAttackRuleAndGetRuleEndStatus(RuleOfAttack.ME_CLOCKWISE_TILE, attackCandidates);
            this.attackIntentOrNull = new AttackIntent(attackCandidates.get(0).getPosition(), super.getAp(), this);
            attackCandidates.clear();
            return;
        }

        // ======== step 2. 대기 ========
        actionType = ActionType.NOTHING;
    }

    @Override
    public AttackIntent attack() {
        if (actionType == ActionType.ATTACK) {
            actionType = ActionType.NOTHING;
            return attackIntentOrNull;
        }
        return null;
    }

    @Override
    public void onAttacked(int damage) {
        super.onAttacked(damage);
    }

    @Override
    public void onSpawn() {
        SimulationManager sm = SimulationManager.getInstance();
        sm.registerThinkable(this);
    }

    @Override
    public void onDeath() {
        SimulationManager sm = SimulationManager.getInstance();
        sm.unRegisterThinkable(this);
    }

}
