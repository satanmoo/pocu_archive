package academy.pocu.comp2500.assignment3;

import java.util.ArrayList;

public final class Marine extends ThinkableUnit implements IMovable {
    private static final char SYMBOL = 'M';
    private static final UnitMobileType UNIT_MOBILE_TYPE = UnitMobileType.GROUND;
    private static final int VISION = 2;
    private static final int AOE = 0;
    private static final int AP = 6;
    private static final int MAX_HP = 35;
    private static final UnitAttackType UNIT_ATTACK_TYPE = UnitAttackType.GROUND_AND_SKY;
    private static final UnitVisibility UNIT_VISIBILITY = UnitVisibility.VISIBLE;
    private static final int ATTACK_VISION[][] = {{0, 0}, {0, -1}, {1, 0}, {0, 1}, {-1, 0}};// 현재위치, 북, 동, 남, 서

    private AttackIntent attackIntentOrNull = null;
    private IntVector2D nextIntVector2DOrNull = null;

    public Marine(final IntVector2D position) {
        super(SYMBOL, UNIT_MOBILE_TYPE, VISION, AOE, AP, MAX_HP, UNIT_ATTACK_TYPE, position, UNIT_VISIBILITY, ATTACK_VISION);
    }

    public Marine(final IntVector2D position, final int maxHp) {
        super(SYMBOL, UNIT_MOBILE_TYPE, VISION, AOE, AP, maxHp, UNIT_ATTACK_TYPE, position, UNIT_VISIBILITY, ATTACK_VISION);
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
                System.out.println(attackCandidates.get(0).getPosition().toString());
                this.attackIntentOrNull = new AttackIntent(attackCandidates.get(0).getPosition(), super.getAp(), this);
                return;
            }

            // 2. 자신의 위치에 유닛이 있다면 그 타일을 공격. 그렇지 않을 경우 북쪽(위쪽)에 유닛이 있다면 그 타일을 공격. 그렇지 않을 경우 시계 방향으로 검색하다 찾은 유닛의 타일을 공격
            super.applyAttackRuleAndGetRuleEndStatus(RuleOfAttack.ME_CLOCKWISE_TILE, attackCandidates);
            this.attackIntentOrNull = new AttackIntent(attackCandidates.get(0).getPosition(), super.getAp(), this);
            attackCandidates.clear();
            return;
        }

        // ======== step 2. 이동가능성 판정 ========
        final ArrayList<Unit> moveCandidates = new ArrayList<>();
        super.createMoveCandidates(moveCandidates);

        if (moveCandidates.size() > 0) {
            super.actionType = ActionType.MOVE;

            // 1. 가장 가까이 있는 유닛 쪽으로 이동. 가장 가까운 유닛은 맨해튼 거리를 사용하여 판단합니다.
            boolean isActionRuleEnd = super.applyMoveRuleAndGetRuleEndStatus(RuleOfMove.CLOSEST_UNIT_TILE_BY_MANHATTAN_DISTANCE, moveCandidates);

            if (isActionRuleEnd) {
                this.nextIntVector2DOrNull = super.intVector2D.getNextIntVector2D(moveCandidates.get(0).getPosition());
                return;
            }

            // 2. 가장 약한 유닛 쪽으로 이동
            isActionRuleEnd = super.applyMoveRuleAndGetRuleEndStatus(RuleOfMove.LOWEST_HP_ENEMY_TILE, moveCandidates);

            if (isActionRuleEnd) {
                this.nextIntVector2DOrNull = super.intVector2D.getNextIntVector2D(moveCandidates.get(0).getPosition());
                return;
            }

            // 3. 북쪽에 있는 유닛 쪽으로 이동, 북쪽에 유닛이 없다면 시계 방향으로 검색하다 찾은 유닛쪽으로 이동함.
            super.applyMoveRuleAndGetRuleEndStatus(RuleOfMove.CLOCKWISE_TILE, moveCandidates);

            this.nextIntVector2DOrNull = super.intVector2D.getNextIntVector2D(moveCandidates.get(0).getPosition());
            return;
        }

        // ======== step 3. 아무것도 안함 ========
        super.actionType = ActionType.NOTHING;
    }

    @Override
    public void move() {
        if (super.actionType == ActionType.MOVE) {
            super.intVector2D = nextIntVector2DOrNull;
            nextIntVector2DOrNull = null;
            super.actionType = ActionType.NOTHING;
        }
    }

    @Override
    public AttackIntent attack() {
        if (super.actionType == ActionType.ATTACK) {
            super.actionType = ActionType.NOTHING;
            return attackIntentOrNull;
        }
        return null;
    }

    @Override
    public void onSpawn() {
        SimulationManager sm = SimulationManager.getInstance();
        sm.registerThinkable(this);
        sm.registerMovable(this);
    }

    @Override
    public void onDeath() {
        SimulationManager sm = SimulationManager.getInstance();
        sm.unRegisterThinkable(this);
        sm.unRegisterMovable(this);
    }
}
