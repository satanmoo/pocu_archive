package academy.pocu.comp2500.assignment3;

import java.util.ArrayList;

public final class SimulationManager {
    private static SimulationManager instance;
    public static final int MAX_X_INDEX = 16 - 1;
    public static final int MAX_Y_INDEX = 8 - 1;

    private final ArrayList<Unit> units = new ArrayList<>();
    private final ArrayList<IThinkable> thinkables = new ArrayList<>();
    private final ArrayList<IMovable> movables = new ArrayList<>();
    private final ArrayList<ICollisionDetectable> collisionDetectables = new ArrayList<>();

    public static SimulationManager getInstance() {
        if (instance == null) {
            instance = new SimulationManager();
        }
        return instance;
    }

    public ArrayList<Unit> getUnits() {
        assert instance != null;
        return units;
    }


    public void spawn(Unit unit) {
        assert instance != null;
        units.add(unit);
        unit.onSpawn();
    }

    public void registerThinkable(IThinkable thinkable) {
        this.thinkables.add(thinkable);
    }

    public void registerMovable(IMovable movable) {
        this.movables.add(movable);
    }

    public void registerCollisionEventListener(ICollisionDetectable listener) {
        this.collisionDetectables.add(listener);
    }

    public void unRegisterThinkable(IThinkable thinkable) {
        this.thinkables.remove(thinkable);
    }

    public void unRegisterMovable(IMovable movable) {
        this.movables.remove(movable);
    }

    public void unRegisterCollisionEventListener(ICollisionDetectable listener) {
        this.collisionDetectables.remove(listener);
    }

    public void update() {

        // 1. 각 유닛들이 이번 프레임에서 할 행동(선택지: 공격, 이동, 아무것도 안함) 을 결정
        for (IThinkable thinkable : thinkables) {
            thinkable.think();
        }

        // 2. 움직일 수 있는 각 유닛에게 이동할 기회를 줌.
        for (IMovable movable : movables) {
            movable.move();
        }

        // 3. 이동 후 충돌처리
        for (ICollisionDetectable collisionDetectable : collisionDetectables) {
            collisionDetectable.collisionDetect();
        }

        // 4. 각 유닛에게 공격할 기회를 줌.
        ArrayList<AttackIntent> attackIntents = new ArrayList<>();
        for (Unit unit : units) {
            AttackIntent attackIntentOrNull = unit.attack();
            if (attackIntentOrNull != null) {
                attackIntents.add(attackIntentOrNull);
            }
        }

        // 5. 피해를 입어야하는 각 유닛에게 피해를 입힘
        for (AttackIntent attackIntent : attackIntents) {
            final int damage = attackIntent.getDamage();
            final Unit attacker = attackIntent.getAttacker();
            final IntVector2D attackTile = attackIntent.getIntVector2D();

            if (attacker.getSymbol() == 'D') { // 파괴자 전용
                for (Unit unit : units) {
                    if (unit == attacker) {
                        continue;
                    }
                    unit.onAttacked(attacker.getAp());
                }
                continue;
            }


            for (Unit unit : units) {
                if (attacker == unit) {
                    continue;
                }

                if (attackTile.equals(unit.getPosition()) == false) {
                    continue;
                }

                // unit != attacker && unit.getPosition == attackTile
                switch (attacker.getUnitAttackType()) {
                    case GROUND:
                        if (unit.getUnitMobileType() == UnitMobileType.GROUND) {
                            unit.onAttacked(damage);
                        }
                        break;
                    case SKY:
                        if (unit.getUnitMobileType() == UnitMobileType.SKY) {
                            unit.onAttacked(damage);
                        }
                        break;
                    case GROUND_AND_SKY:
                        unit.onAttacked(damage);
                        break;
                    default:
                        assert false : "invalid getUnitAttackType" + attacker.getUnitAttackType();
                }
            }

            if (attacker.getAoe() > 0) {
                // 이 부분은 제일 후회되는 부분이다.
                // 그 이유는 ?
                // -- attack 단계는 이미 think 단계에서 정리된 "공격의도에 따라" "그저 공격을 하는" 단계여야 함.
                // -- 하지만 나는 여기서 aoe List 들을 생성해서 만들고 있음.
                // -- think에서 [1] aoe 범위공격을 맞는 타일 리스트 생성, [2] aoe 데미지계산 로직을 처리 해줬어야하는데 아쉽다.

                final ArrayList<IntVector2D> aoePosList = new ArrayList<>();
                final int aoe[][] = {{0, -1}, {1, -1}, {1, 0}, {1, 1}, {0, 1}, {-1, 1}, {-1, 0}, {-1, -1}};
                // 위 로직도 마치 정적메모리 처럼 보이지만 사실 내부적으로는 그냥 배열의배열 동적할당코드이기 때문에 비효율적임.
                // 매 update 호출때 마다 비효율적으로 메모리 생성하는 꼴임 ㅠ 아쉽 ㅠ
                // 아 진짜 아쉽다 aoe !!

                for (int[] ints : aoe) {
                    aoePosList.add(new IntVector2D(attackTile.getX() + ints[0], attackTile.getY() + ints[1]));
                }

                int aoeDamage = (int) ((double) attackIntent.getDamage() * (1 - attackTile.aoeDistance(aoePosList.get(0)) / (double) (attacker.getAoe() + 1)));

                for (IntVector2D aoePos : aoePosList) {
                    for (Unit unit1 : units) {
                        if (aoePos.equals(unit1.getPosition())) {
                            switch (attacker.getUnitAttackType()) {
                                case GROUND:
                                    if (unit1.getUnitMobileType() == UnitMobileType.GROUND) {
                                        unit1.onAttacked(aoeDamage);
                                    }
                                    break;
                                case SKY:
                                    if (unit1.getUnitMobileType() == UnitMobileType.SKY) {
                                        unit1.onAttacked(aoeDamage);
                                    }
                                    break;
                                case GROUND_AND_SKY:
                                    unit1.onAttacked(aoeDamage);
                                    break;
                                default:
                                    assert false : "invalid getUnitAttackType" + attacker.getUnitAttackType();
                            }
                        }
                    }
                }
            }
        }

        // 6. 죽은 유닛들을 모두 게임에서 제거함.
        for (Unit unit : units) {
            if (unit.getHp() <= 0) {
                unit.onDeath();
            }
        }

        units.removeIf((unit -> unit.getHp() <= 0));
    }
}