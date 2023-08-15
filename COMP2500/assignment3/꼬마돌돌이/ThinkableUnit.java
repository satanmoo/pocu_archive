package academy.pocu.comp2500.assignment3;


import java.util.ArrayList;

public abstract class ThinkableUnit extends Unit implements IThinkable {

    protected ThinkableUnit(
            final char symbol,
            final UnitMobileType unitMobileType,
            final int vision,
            final int aoe,
            final int ap,
            final int maxHp,
            final UnitAttackType unitAttackType,
            final IntVector2D position,
            final UnitVisibility unitVisibility,
            final int[][] ATTACK_VISION
    ) {
        super(symbol, unitMobileType, vision, aoe, ap, maxHp, unitAttackType, position, unitVisibility, ATTACK_VISION);
    }

    @Override
    public abstract void think();

    protected void createAttackCandidates(final ArrayList<Unit> attackCandidates) {
        ArrayList<Unit> units = SimulationManager.getInstance().getUnits();

        final int x = super.getPosition().getX();
        final int y = super.getPosition().getY();
        final ArrayList<IntVector2D> canAttackTiles = new ArrayList<>();

        for (int[] ints : super.ATTACK_VISION) {
            canAttackTiles.add(new IntVector2D(x + ints[0], y + ints[1]));
        }

        for (IntVector2D tile : canAttackTiles) {
            for (Unit unit : units) {
                if (unit.getPosition().equals(tile)) { // 1. 타일 일치유무 확인
                    if (unit == this) { // 자기 자신을 공격할 수 없다.
                        continue;
                    }

                    if (unit.getUnitVisibility() != UnitVisibility.VISIBLE) { // 2. 보이는 유닛 유무 확인
                        continue;
                    }

                    switch (super.getUnitAttackType()) { // 3. 공격가능성 확인
                        case GROUND:
                            if (unit.getUnitMobileType() == UnitMobileType.GROUND) {
                                attackCandidates.add(unit);
                            }
                            break;
                        case SKY:
                            if (unit.getUnitMobileType() == UnitMobileType.SKY) {
                                attackCandidates.add(unit);
                            }
                            break;
                        case GROUND_AND_SKY:
                            attackCandidates.add(unit);
                            break;
                        default:
                            assert false : "invalid super.getUnitAttackType()" + super.getUnitAttackType();
                    }
                }
            }
        }
    }

    protected void createMoveCandidates(final ArrayList<Unit> moveCandidates) {
        final ArrayList<Unit> units = SimulationManager.getInstance().getUnits();
        final IntVector2D position = super.getPosition();

        int startX = Math.max(0x00, position.getX() - super.getVision());
        int endX = Math.min(0x0F, position.getX() + super.getVision());
        int startY = Math.max(0x00, position.getY() - super.getVision());
        int endY = Math.min(0x07, position.getY() + super.getVision());

        for (int i = startX; i <= endX; i++) {
            for (int j = startY; j <= endY; j++) {
                for (Unit unit : units) {
                    if (unit.getPosition().equals(new IntVector2D(i, j))) {
                        if (unit == this) {// 자기 자신의 위치로 이동은 불가능
                            continue;
                        }

                        if (unit.getUnitVisibility() != UnitVisibility.VISIBLE) { // 2. 보이는 유닛 유무 확인
                            continue;
                        }

                        switch (super.getUnitAttackType()) { // 3. 공격가능성 확인
                            case GROUND:
                                if (unit.getUnitMobileType() == UnitMobileType.GROUND) {
                                    moveCandidates.add(unit);
                                }
                                break;
                            case SKY:
                                if (unit.getUnitMobileType() == UnitMobileType.SKY) {
                                    moveCandidates.add(unit);
                                }
                                break;
                            case GROUND_AND_SKY:
                                moveCandidates.add(unit);
                                break;
                            default:
                                assert false : "invalid super.getUnitAttackType()" + super.getUnitAttackType();
                        }
                    }
                }
            }
        }
    }

    protected boolean applyAttackRuleAndGetRuleEndStatus(final RuleOfAttack ruleOfAttack, final ArrayList<Unit> candidates) {
        // pre-condition
            //  [1] : candidates.size() > 0;
            //  [2] : assert candidates != null;
            //  [3] : candidate must be added clockwise
        assert ruleOfAttack != null;
        assert candidates != null;
        assert candidates.size() > 0;

        switch (ruleOfAttack) {
            case LOWEST_HP_ENEMY_TILE:
                // 1. find Lowest hp
                int lowestHp = Integer.MAX_VALUE;
                for (Unit candidate : candidates) {
                    if (lowestHp > candidate.getHp()) {
                        lowestHp = candidate.getHp();
                    }
                }

                // 2. filter Lowest Hp Units
                int finalLowestHp = lowestHp; // Lambda function require final keyword..
                candidates.removeIf(unit -> finalLowestHp != unit.getHp());

                // 3. return RuleEndStatus
                return candidates.size() == 1 ? true : false;

            case CLOCKWISE_TILE:
            case ME_CLOCKWISE_TILE:
                final IntVector2D attackTile = candidates.get(0).intVector2D;
                candidates.removeIf(unit -> unit.getPosition().equals(attackTile) == false);
                return true;
            default:
                assert false : "invalid ruleOfAttack" + ruleOfAttack;
                return false;
        }
    }

    protected boolean applyMoveRuleAndGetRuleEndStatus(final RuleOfMove ruleOfMove, final ArrayList<Unit> candidates) {

        switch (ruleOfMove) {
            case CLOSEST_UNIT_TILE_BY_MANHATTAN_DISTANCE:
                int minDist = Integer.MAX_VALUE;
                IntVector2D minDistTile = null;

                // 1. find MinDist, minDistTile
                for (Unit unit : candidates) {
                    if (unit.getPosition().distanceManhattan(super.getPosition()) < minDist) {
                        minDist = unit.getPosition().distanceManhattan(super.getPosition());
                        minDistTile = unit.intVector2D;
                    }
                }

                // 2. filter minDist Units
                int finalMinDist1 = minDist;
                candidates.removeIf(unit -> super.getPosition().distanceManhattan(unit.getPosition()) != finalMinDist1);

                // 3. return RuleEndStatus
                return candidates.size() == 1 ? true : false;

            case LOWEST_HP_ENEMY_TILE:
                // 1. find Lowest hp
                int lowestHp = Integer.MAX_VALUE;
                for (Unit candidate : candidates) {
                    if (lowestHp > candidate.getHp()) {
                        lowestHp = candidate.getHp();
                    }
                }

                // 2. filter Lowest Hp Units
                int finalLowestHp = lowestHp; // Lambda function require final keyword..
                candidates.removeIf(unit -> finalLowestHp != unit.getHp());

                // 3. return RuleEndStatus
                return candidates.size() == 1 ? true : false;

            case CLOCKWISE_TILE:
                final int x = super.getPosition().getX();
                final int y = super.getPosition().getY();
                Unit targetOrNull = null;

                for (Unit unit : candidates) {
                    if (x == unit.intVector2D.getX() && y > unit.intVector2D.getY()) { // 북
                        candidates.removeIf(innerUnit -> innerUnit.getPosition().equals(unit.getPosition()) == false);
                        return true;
                    }
                }

                for (Unit unit : candidates) {
                    if (x < unit.intVector2D.getX() && y > unit.intVector2D.getY()) { // 북동
                        candidates.removeIf(innerUnit -> innerUnit.getPosition().equals(unit.getPosition()) == false);
                        return true;
                    }
                }


                for (Unit unit : candidates) {
                    if (x < unit.intVector2D.getX() && y == unit.intVector2D.getY()) { // 동
                        candidates.removeIf(innerUnit -> innerUnit.getPosition().equals(unit.getPosition()) == false);
                        return true;
                    }
                }
                for (Unit unit : candidates) {
                    if (x < unit.intVector2D.getX() && y < unit.intVector2D.getY()) { // 동남
                        candidates.removeIf(innerUnit -> innerUnit.getPosition().equals(unit.getPosition()));
                        return true;
                    }
                }
                for (Unit unit : candidates) {
                    if (x == unit.intVector2D.getX() && y < unit.intVector2D.getY()) { // 남
                        candidates.removeIf(innerUnit -> innerUnit.getPosition().equals(unit.getPosition()));
                        return true;
                    }
                }
                for (Unit unit : candidates) {
                    if (x == unit.intVector2D.getX() && y < unit.intVector2D.getY()) { // 남서
                        candidates.removeIf(innerUnit -> innerUnit.getPosition().equals(unit.getPosition()));
                        return true;
                    }
                }
                for (Unit unit : candidates) {
                    if (x == unit.intVector2D.getX() && y < unit.intVector2D.getY()) { // 서
                        candidates.removeIf(innerUnit -> innerUnit.getPosition().equals(unit.getPosition()));
                        return true;
                    }
                }
                for (Unit unit : candidates) {
                    if (x > unit.intVector2D.getX() && y > unit.intVector2D.getY()) { // 북서
                        candidates.removeIf(innerUnit -> innerUnit.getPosition().equals(unit.getPosition()));
                        return true;
                    }
                }
                return true;
            default:
                assert false : "invalid ruleOfMove" + ruleOfMove;
                return false;
        }
    }
}
