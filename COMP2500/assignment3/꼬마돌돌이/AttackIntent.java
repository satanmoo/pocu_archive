package academy.pocu.comp2500.assignment3;

import java.util.ArrayList;

public final class AttackIntent {
    private final IntVector2D intVector2D;
    private final int damage;
    private final Unit attacker;

    public AttackIntent(final IntVector2D intVector2D, final int damage, final Unit attacker) {
        this.intVector2D = intVector2D;
        this.damage = damage;
        this.attacker = attacker;
    }

    public IntVector2D getIntVector2D() {
        return intVector2D;
    }

    public int getDamage() {
        return damage;
    }

    public Unit getAttacker() {
        return attacker;
    }
}
