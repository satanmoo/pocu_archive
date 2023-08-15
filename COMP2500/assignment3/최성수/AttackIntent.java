package academy.pocu.comp2500.assignment3;

import java.util.ArrayList;

public class AttackIntent {
    private final Unit attacker;
    private IntVector2D position;
    private ArrayList<IntVector2D> positions;
    private final int ap;
    private final int aoe;
    private final ArrayList<Unit> units;

    public AttackIntent(Unit attacker, IntVector2D position, int ap, int aoe, ArrayList<Unit> units) {
        this.attacker = attacker;
        this.position = position;
        this.ap = ap;
        this.aoe = aoe;
        this.units = units;
    }

    public AttackIntent(Unit attacker, ArrayList<IntVector2D> positions, int ap, int aoe, ArrayList<Unit> units) {
        this.attacker = attacker;
        this.positions = positions;
        this.ap = ap;
        this.aoe = aoe;
        this.units = units;
    }

    private ArrayList<IntVector2D> getSquareVectors(IntVector2D center, int num) {
        int startRow = center.getY() - num;
        int startColumn = center.getX() - num;
        int endRow = center.getY() + num;
        int endColumn = center.getX() + num;

        ArrayList<IntVector2D> vectors = new ArrayList<>();

        for (int y = startRow; y <= endRow; ++y) {
            for (int x = startColumn; x <= endColumn; ++x) {
                if (y < 0 || x < 0 || y >= 8 || x >= 16) {
                    continue;
                }
                if (y == startRow || x == startColumn || y == endRow || x == endColumn) {
                    vectors.add(new IntVector2D(x, y));
                }
            }
        }
        return vectors;
    }

    private void attackToPosition(IntVector2D position) {
        for (int i = 0; i <= aoe; ++i) {
            ArrayList<IntVector2D> vectors = getSquareVectors(position, i);
            for (IntVector2D vector : vectors) {
                int d = i;
                units.forEach(unit -> {
                    if (attacker != unit && unit.position.equals(vector)) {
                        int damage = (int) (ap * (1 - d / (double) (aoe + 1)));
                        unit.onAttacked(damage);
                    }
                });
            }
        }
    }

    public void attack() {

        if (positions != null) {
            for (IntVector2D position : positions) {
                attackToPosition(position);
            }
        } else {
            attackToPosition(position);
        }

        attacker.setTargetsAsNull();
    }

}