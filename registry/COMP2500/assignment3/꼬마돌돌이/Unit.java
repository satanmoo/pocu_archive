package academy.pocu.comp2500.assignment3;

public abstract class Unit {
    private final char symbol;
    private final UnitMobileType unitMobileType;
    private final int vision;
    private final int aoe;
    private final int ap;
    private final int maxHp;
    private final UnitAttackType unitAttackType;
    private UnitVisibility unitVisibility;

    protected final int[][] ATTACK_VISION;
    protected ActionType actionType = ActionType.NOTHING;
    protected AttackIntent attackIntentOrNull = null;
    protected IntVector2D intVector2D;
    protected int hp;

    protected Unit(
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
        assert unitMobileType != null;
        assert vision >= 0;
        assert aoe >= 0;
        assert ap > 0;
        assert maxHp > 0;
        assert unitAttackType != null;
        assert position != null;
        assert position.getX() >= 0x00 && position.getX() <= 0x0F;
        assert position.getY() >= 0x00 && position.getY() <= 0x0F;
        assert unitVisibility != null;

        this.symbol = symbol;
        this.unitMobileType = unitMobileType;
        this.vision = vision;
        this.aoe = aoe;
        this.ap = ap;
        this.maxHp = this.hp = maxHp;
        this.unitAttackType = unitAttackType;
        this.intVector2D = position;
        this.unitVisibility = unitVisibility;
        this.ATTACK_VISION = ATTACK_VISION;
    }

    public IntVector2D getPosition() {
        return intVector2D;
    }

    public int getHp() {
        return this.hp;
    }

    public abstract AttackIntent attack();

    public void onAttacked(int damage) {
        this.hp = Math.max(0, this.hp - damage);
    }

    public abstract void onSpawn();

    public abstract void onDeath();

    public char getSymbol() {
        return this.symbol;
    }

    public int getVision() {
        return vision;
    }

    public UnitMobileType getUnitMobileType() {
        return unitMobileType;
    }

    public UnitVisibility getUnitVisibility() {
        return unitVisibility;
    }

    public int getAoe() {
        return aoe;
    }

    public int getAp() {
        return ap;
    }

    public UnitAttackType getUnitAttackType() {
        return unitAttackType;
    }

}
