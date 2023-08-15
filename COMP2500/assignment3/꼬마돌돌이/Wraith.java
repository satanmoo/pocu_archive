package academy.pocu.comp2500.assignment3;

import java.util.ArrayList;

public final class Wraith extends ThinkableUnit implements IMovable {
    private static final char SYMBOL = 'W';
    private static final UnitMobileType UNIT_MOBILE_TYPE = UnitMobileType.SKY;
    private static final int VISION = 4;
    private static final int AOE = 0;
    private static final int AP = 6;
    private static final int MAX_HP = 80;
    private static final UnitAttackType UNIT_ATTACK_TYPE = UnitAttackType.GROUND_AND_SKY;
    private static final UnitVisibility UNIT_VISIBILITY = UnitVisibility.VISIBLE;

    private static final int ATTACK_VISION[][] = {{0, 0}, {0, -1}, {1, 0}, {0, 1}, {-1, 0}};// 현위치, 북, 동, 남, 서
    private BarrierMode barrierMode = BarrierMode.INACTIVE;
    private final IntVector2D spawnPosition;

    private AttackIntent attackIntentOrNull = null;
    private IntVector2D nextIntVector2DOrNull = null;

    public Wraith(final IntVector2D position, final int maxHp) {
        super(SYMBOL, UNIT_MOBILE_TYPE, VISION, AOE, AP, maxHp, UNIT_ATTACK_TYPE, position, UNIT_VISIBILITY, ATTACK_VISION);
        this.spawnPosition = position;
    }

    public Wraith(final IntVector2D position) {
        super(SYMBOL, UNIT_MOBILE_TYPE, VISION, AOE, AP, MAX_HP, UNIT_ATTACK_TYPE, position, UNIT_VISIBILITY, ATTACK_VISION);
        this.spawnPosition = position;
    }


    @Override
    public void think() {
        if (barrierMode == BarrierMode.ACTIVE) {
            barrierMode = BarrierMode.USED;
        }

        // ======== step 1. 공격가능성 판정 ========
        // 현재 공격가능한 유닛들 리스트에 추가
        final ArrayList<Unit> attackCandidates = new ArrayList<>();
        super.createAttackCandidates(attackCandidates);


        if (attackCandidates.size() > 0) {
            super.actionType = ActionType.ATTACK;

            // 1. 공중 유닛들을 공격할 후보로 선택. 선택할 공중 유닛이 없다면 지상 유닛들을 선택
            boolean isSkyUnitsExist = false;
            for (Unit attackCandidate : attackCandidates) {
                if (attackCandidate.getUnitMobileType() == UnitMobileType.SKY) {
                    isSkyUnitsExist = true;
                    break;
                }
            }
            if (isSkyUnitsExist) {
                attackCandidates.removeIf(unit -> unit.getUnitMobileType() != UnitMobileType.SKY);
            }

            // 2. 가장 약한 유닛이 있는 타일을 공격
            boolean isActionRuleEnd = super.applyAttackRuleAndGetRuleEndStatus(RuleOfAttack.LOWEST_HP_ENEMY_TILE, attackCandidates);
            if (isActionRuleEnd) {
                this.attackIntentOrNull = new AttackIntent(attackCandidates.get(0).getPosition(), super.getAp(), this);
                return;
            }

            // 3. 자신의 위치에 유닛이 있다면 그 타일을 공격. 그렇지 않을 경우 북쪽(위쪽)에 유닛이 있다면 그 타일을 공격. 그렇지 않을 경우 시계 방향으로 검색하다 찾은 유닛의 타일을 공격
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

            // 1. 공중 유닛들을 따라갈 후보로 선택. 선택할 공중 유닛이 없다면 지상 유닛들을 선택
            boolean isSkyUnitsExist = false;
            for (Unit attackCandidate : attackCandidates) {
                if (attackCandidate.getUnitMobileType() == UnitMobileType.SKY) {
                    isSkyUnitsExist = true;
                    break;
                }
            }
            if (isSkyUnitsExist) {
                attackCandidates.removeIf(unit -> unit.getUnitMobileType() != UnitMobileType.SKY);
            }

            // 2. 가장 가까이 있는 유닛 쪽으로 이동
            boolean isActionRuleEnd = super.applyMoveRuleAndGetRuleEndStatus(RuleOfMove.CLOSEST_UNIT_TILE_BY_MANHATTAN_DISTANCE, moveCandidates);
            if (isActionRuleEnd) {
                this.nextIntVector2DOrNull = super.intVector2D.getNextIntVector2D(moveCandidates.get(0).getPosition());
                return;
            }

            // 3. 가장 약한 유닛 쪽으로 이동
            isActionRuleEnd = super.applyMoveRuleAndGetRuleEndStatus(RuleOfMove.LOWEST_HP_ENEMY_TILE, moveCandidates);
            if (isActionRuleEnd) {
                this.nextIntVector2DOrNull = super.intVector2D.getNextIntVector2D(moveCandidates.get(0).getPosition());
                return;
            }

            // 4. 북쪽에 있는 유닛 쪽으로 이동. 북쪽에 유닛이 없다면 시계 방향으로 검색하다 찾은 유닛 쪽으로 이동
            super.applyMoveRuleAndGetRuleEndStatus(RuleOfMove.CLOCKWISE_TILE, moveCandidates);
            this.nextIntVector2DOrNull = super.intVector2D.getNextIntVector2D(moveCandidates.get(0).getPosition());

            moveCandidates.clear();
            return;

        }

        // ======== step 3. 내 자리로 돌아가기 ========
            // 시야 안에서 적을 찾지 못하면 다음의 이동 규칙을 따릅니다.
            // 자신의 처음 위치 쪼그올 이동해야 합니다. 이때 역시 Y 축을 따라 먼저 이동합니다.
        if (super.getPosition().equals(this.spawnPosition)) {
            actionType = ActionType.NOTHING;
            this.nextIntVector2DOrNull = super.getPosition();
        } else {
            actionType = ActionType.MOVE;
            this.nextIntVector2DOrNull = super.getPosition().getNextIntVector2D(this.spawnPosition);
        }
        return;
    }


    @Override
    public void move() {
        if (actionType == ActionType.MOVE) {
            super.intVector2D = nextIntVector2DOrNull;
            nextIntVector2DOrNull = null;
            actionType = ActionType.NOTHING;
        }
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
    public void onSpawn() {
        SimulationManager sm = SimulationManager.getInstance();
        sm.registerThinkable(this);
        sm.registerMovable(this);
    }

    @Override
    public void onAttacked(int damage) {
        if (barrierMode == BarrierMode.INACTIVE) {
            barrierMode = BarrierMode.ACTIVE;
        }
        if (barrierMode == BarrierMode.ACTIVE) {
            // avoid Attack
            return;
        } else {
            super.onAttacked(damage);
        }
    }


    @Override
    public void onDeath() {
        SimulationManager sm = SimulationManager.getInstance();
        sm.unRegisterThinkable(this);
        sm.unRegisterMovable(this);
    }
}
