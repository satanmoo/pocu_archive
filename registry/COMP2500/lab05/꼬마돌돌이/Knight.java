package academy.pocu.comp2500.lab5;

public class Knight extends Gladiator {
    private Pet petOrNull;

    public Knight(final String name, final int maxHp, final int attack, final int defense) {
        super(name, maxHp, attack, defense);
        petOrNull = null;
    }

    public void setPet(final Pet petOrNull) {
        this.petOrNull = petOrNull;
    }

    public void attackTogether(final Barbarian target) {
        assert target != null;

        if (super.isAlive() == false || target == this || petOrNull == null) {
            return;
        }

        // clamp ; damage >= MIN_DAMAGE
        double a = super.attackPoint + petOrNull.getAttack() - target.defensePoint;
        double b = Barbarian.CALC_DAMAGE_DIVIDE;
        int damage = (int) (a / b);
        damage = Math.max(Barbarian.MIN_DAMAGE, damage);

        // clamp ; hp >= DIE_HP
        target.hp = Math.max(Barbarian.DIE_HP, target.hp - damage);
    }
}
