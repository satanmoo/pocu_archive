package academy.pocu.comp2500.lab5;

public class Barbarian {
    protected static final int MIN_DAMAGE = 1;
    protected static final int DIE_HP = 0;
    protected static final int CALC_DAMAGE_DIVIDE = 2;

    protected final int maxHp;
    private final String name;

    protected int hp;
    protected int attackPoint;
    protected int defensePoint;

    public Barbarian(final String name, final int maxHp, final int attackPoint, final int defensePoint) {
        assert maxHp > 0;
        assert name != null;
        assert attackPoint > 0; // 공격력은 그래도 1 이상이어야...
        assert defensePoint >= 0; // 실오라기 걸친 것 없는 나체 캐릭터는 방어력 0 일까..?

        this.maxHp = maxHp;
        this.hp = maxHp;
        this.name = name;
        this.attackPoint = attackPoint;
        this.defensePoint = defensePoint;
    }

    public int getHp() {
        return this.hp;
    }

    public void attack(final Barbarian target) {
        assert target != null;

        if (isAlive() == false || target == this) {
            return;
        }

        // clamp ; damage >= MIN_DAMAGE(1)
        double a = (double) (this.attackPoint - target.defensePoint);
        double b = (double) Barbarian.CALC_DAMAGE_DIVIDE;
        int damage = Math.max(Barbarian.MIN_DAMAGE, (int) (a / b));

        // clamp ; hp >= MIN_HP(0)
        target.hp = Math.max(Barbarian.DIE_HP, target.hp - damage);
    }

    public boolean isAlive() {
        return hp != Barbarian.DIE_HP;
    }

}
