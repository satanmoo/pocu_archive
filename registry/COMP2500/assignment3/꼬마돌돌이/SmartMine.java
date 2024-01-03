package academy.pocu.comp2500.assignment3;

import java.util.ArrayList;

public final class SmartMine extends Mine implements IThinkable {
    private static final char SYMBOL = 'A';
    private static final UnitMobileType UNIT_MOBILE_TYPE = UnitMobileType.GROUND;
    private static final int VISION = 1;
    private static final int AOE = 1;
    private static final int AP = 15;
    private static final int MAX_HP = 1;
    private static final UnitAttackType UNIT_ATTACK_TYPE = UnitAttackType.GROUND;
    private static final UnitVisibility UNIT_VISIBILITY = UnitVisibility.INVISIBLE;

    // threshold [쓰레시홀드] 역치값 ; 어떤 현상을 일으키게 하기 위하여 가해야 하는 물리량의 최소치
    private final int detectUnitThreshold;

    public SmartMine(final IntVector2D position, final int explosionThreshold, final int detectUnitThreshold) {
        super(SYMBOL, UNIT_MOBILE_TYPE, VISION, AOE, AP, MAX_HP, UNIT_ATTACK_TYPE, position, UNIT_VISIBILITY, explosionThreshold);
        this.detectUnitThreshold = detectUnitThreshold;
    }

    @Override
    public void think() {

        // 명세내용
        // 스마트 지뢰는 지뢰의 상위 호환 버전입니다.
        // 스마트 지뢰는 지뢰와 동일하게 작동하는 것 외에도 초특급 울트라 레이더를 탑재한 덕분에 근처에 있는 유닛들을 감지하는 능력을 갖추고 있습니다.
        // 만약 시야 안에서 몇 명 이상의 적 유닛이 감지되면, 스마트 지뢰가 폭발합니다. 폭발에 필요한 최소 적 유닛 수는 스마트 지뢰마다 다르게 지정할 수 있습니다.
        // 초특급 울트라 레이더 기술 덕분에 스마트 지뢰는 시야 안에 있는 모든 지상 유닛을 감지할 수 있습니다. 하지만 공중 유닛과 볼 수 없는 유닛은 감지할 수 없답니다.

        // smartMine이 ThinkableUnit을 상속받을 수 없는 이유?
        // -- Mine을 부모로 가지기 때문에!!
        // -- 그래서 ThinkableUnit의 다중상속을 흉내낼 수 있는 interface가 있기 때문에 문제해결에는 이상 없음
        // -- 그러나 코드중복발생 ( ThinkableUnit의 createMoveCandidates 로직중복.. )

        final ArrayList<Unit> candidate = new ArrayList<>();
        final ArrayList<Unit> units = SimulationManager.getInstance().getUnits();
        final IntVector2D position = super.getPosition();

        int startX = Math.max(0x00, position.getX() - super.getVision());
        int endX = Math.min(0x0F, position.getX() + super.getVision());
        int startY = Math.max(0x00, position.getY() - super.getVision());
        int endY = Math.min(0x07, position.getY() + super.getVision());

        for (int i = startX; i <= endX; i++) {
            for (int j = startY; j <= endY; j++) {
                for (Unit unit : units) {
                    if (unit.getPosition().equals(new IntVector2D(i, j))) { // 1. 타일일치여부
                        if (unit == this) {// 자기 자신의 위치로 이동은 불가능
                            continue;
                        }

                        if (unit.getUnitVisibility() != UnitVisibility.VISIBLE) { // 2. 보이는 유닛 유무 확인
                            continue;
                        }

                        if (unit.getUnitMobileType() == UnitMobileType.GROUND) {
                            candidate.add(unit);
                        }
                    }
                }
            }
        }

        if (candidate.size() >= this.detectUnitThreshold) {
            super.attackIntentOrNull = new AttackIntent(super.getPosition(), super.getAp(), this);
            actionType = ActionType.ATTACK;
            candidate.clear();
            return;
        }
    }

    @Override
    public void collisionDetect() {
        if (actionType == ActionType.ATTACK) {
            return;
        }
        super.collisionDetect();
    }

    @Override
    public void onSpawn() {
        SimulationManager sm = SimulationManager.getInstance();
        sm.registerThinkable(this);
        super.onSpawn();
    }


    @Override
    public void onDeath() {
        SimulationManager sm = SimulationManager.getInstance();
        sm.unRegisterThinkable(this);
        super.onDeath();
    }


}
