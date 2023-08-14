package academy.pocu.comp2500.lab5;

import java.util.HashMap;

public class Gladiator extends Barbarian {
    private static final int MAX_MOVE_SIZE = 4;
    private static final int REST = 10;

    private final HashMap<String, Move> moves;

    public Gladiator(final String name, final int maxHp, final int attack, final int defense) {
        super(name, maxHp, attack, defense);
        this.moves = new HashMap<>(Gladiator.MAX_MOVE_SIZE);
    }

    public boolean addMove(final Move move) {
        assert move != null;

        if (moves.containsKey(move.getMoveName())) {
            return false;
        }

        if (moves.size() == Gladiator.MAX_MOVE_SIZE) {
            return false;
        }

        moves.put(move.getMoveName(), move);
        return true;
    }

    public boolean removeMove(final String moveName) {
        assert moveName != null;

        if (!moves.containsKey(moveName)) {
            return false;
        }

        moves.remove(moveName);
        return true;
    }

    public void attack(final String moveName, final Barbarian target) {
        assert moveName != null;
        assert target != null;

        if (!super.isAlive() || target == this || !moves.containsKey(moveName)) {
            return;
        }

        Move move = moves.get(moveName);

        if (!move.isMovable()) {
            return;
        }

        // clamp ; damage >= MIN_DAMAGE
        double a = super.attackPoint * move.moveAndGetPower();
        double b = target.defensePoint;
        double c = Barbarian.CALC_DAMAGE_DIVIDE;
        int damage = Math.max(Barbarian.MIN_DAMAGE, (int) ((a / b) / c));

        // clamp ; hp >= DIE_HP
        target.hp = Math.max(Barbarian.DIE_HP, target.hp - damage);
    }

    public void rest() {
        assert super.isAlive();

        // clamp ; super.hp <= super.maxHp
        super.hp = Math.min(super.maxHp, super.hp + REST);

        for (String moveName : moves.keySet()) {
            Move move = moves.get(moveName);
            move.rest();
        }

    }
}
