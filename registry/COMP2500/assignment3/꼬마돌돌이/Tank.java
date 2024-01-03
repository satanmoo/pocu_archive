package academy.pocu.comp2500.assignment3;

import java.util.ArrayList;

public final class Tank extends ThinkableUnit implements IMovable {
    private static final char SYMBOL = 'T';
    private static final UnitMobileType UNIT_MOBILE_TYPE = UnitMobileType.GROUND;
    private static final int VISION = 3;
    private static final int AOE = 1;
    private static final int AP = 8;
    private static final int MAX_HP = 85;
    private static final UnitAttackType UNIT_ATTACK_TYPE = UnitAttackType.GROUND;
    private static final UnitVisibility UNIT_VISIBILITY = UnitVisibility.VISIBLE;
    private static final int ATTACK_VISION[][] = {{0, -2}, {1, -2}, {2, -1}, {2, 0}, {2, 1}, {1, 2}, {0, 2}, {-1, 2}, {-2, 1}, {-2, 0}, {-2, -1}, {-1, -2}};

    private Direction direction = Direction.EAST;
    private TankMode tankMode = TankMode.TANK;

    private AttackIntent attackIntentOrNull = null;
    private IntVector2D nextIntVector2DOrNull = null;


    public Tank(final IntVector2D position, final int maxHp) {
        super(SYMBOL, UNIT_MOBILE_TYPE, VISION, AOE, AP, maxHp, UNIT_ATTACK_TYPE, position, UNIT_VISIBILITY, ATTACK_VISION);
    }

    public Tank(final IntVector2D position) {
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

            // 1. 현재 공성 모드가 아닌 경우 공성 모드로 변경
            if (tankMode == TankMode.TANK) {
                super.actionType = ActionType.NOTHING;
                tankMode = TankMode.SIEGE;
                return;
            }

            // 2. 가장 약한 유닛이 있는 타일을 공격
            boolean isActionRuleEnd = super.applyAttackRuleAndGetRuleEndStatus(RuleOfAttack.LOWEST_HP_ENEMY_TILE, attackCandidates);
            if (isActionRuleEnd) {
                this.attackIntentOrNull = new AttackIntent(attackCandidates.get(0).getPosition(), super.getAp(), this);
                return;
            }

            // 3. 북쪽에 유닛이 있다면 그 타일을 공격. 그렇지 않을 경우 시계 방향으로 검색하다가 찾은 유닛의 타일을 공격
            super.applyAttackRuleAndGetRuleEndStatus(RuleOfAttack.CLOCKWISE_TILE, attackCandidates);
            this.attackIntentOrNull = new AttackIntent(attackCandidates.get(0).getPosition(), super.getAp(), this);
            attackCandidates.clear();
            return;
        }


        // ======== step 2. 공격대상을 못 찾았지만 시야 안에 적이 있는 경우 판정 ========
        final ArrayList<Unit> moveCandidates = new ArrayList<>();
        super.createMoveCandidates(moveCandidates);

        if (moveCandidates.size() > 0) {
            super.actionType = ActionType.NOTHING;
            tankMode = TankMode.SIEGE;
            return;
        }

        // ======== step 3. 끝에서 끝으로 이동 ========
        // 1. 이동하던 방향 끝까지 이동. 한번도 이동한 적이 없다면 오른쪽으로 이동
        // 2. 반대 방향 끝까지 이동
        // 3. 시야 안에서 적을 발견할 때까지 1 - 2를 반복
        if (tankMode == TankMode.SIEGE) {
            tankMode = TankMode.TANK;
            super.actionType = ActionType.NOTHING;
            return;
        }

        super.actionType = ActionType.MOVE;
        if (direction == Direction.EAST && super.getPosition().getX() == 0x0F) {
            direction = Direction.WEST;
        }

        if (direction == Direction.WEST && super.getPosition().getX() == 0x00) {
            direction = Direction.EAST;
        }

        if (direction == Direction.NORTH && super.getPosition().getY() == 0x00) {
            direction = Direction.SOUTH;
        }

        if (direction == Direction.SOUTH && super.getPosition().getY() == 0x07) {
            direction = Direction.NORTH;
        }

        switch (direction) {
            case EAST:
                this.nextIntVector2DOrNull = new IntVector2D(super.getPosition().getX() + 1, super.getPosition().getY());
                break;
            case WEST:
                this.nextIntVector2DOrNull = new IntVector2D(super.getPosition().getX() - 1, super.getPosition().getY());
                break;
            case NORTH:
                this.nextIntVector2DOrNull = new IntVector2D(super.getPosition().getX(), super.getPosition().getY() - 1);
                break;
            case SOUTH:
                this.nextIntVector2DOrNull = new IntVector2D(super.getPosition().getX(), super.getPosition().getY() + 1);
                break;
            default:
                assert false : "invalid Direction";
                return;
        }

        moveCandidates.clear();
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
    public void onAttacked(int damage) {
        if (tankMode == TankMode.SIEGE) {
            super.onAttacked(damage * 2);
        } else {
            super.onAttacked(damage);
        }

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
