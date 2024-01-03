package academy.pocu.comp2500.assignment3;

import java.util.ArrayList;

public abstract class Unit {
    protected char symbol;
    protected int vision;
    protected int aoe;
    protected int hp;
    protected int ap;
    protected boolean canMove;
    protected ArrayList<Unit> targets;
    protected ArrayList<Unit> canAttackUnits;
    protected final IntVector2D position;

    public Unit(IntVector2D position) {
        this.position = position;
    }

    public IntVector2D getPosition() {
        return position;
    }

    public int getHp() {
        return hp;
    }

    public abstract AttackIntent attack();

    public void onAttacked(int damage) {
        hp = Math.max(0, hp - damage);
    }

    public abstract void onSpawn();

    public char getSymbol() {
        return symbol;
    }

    public void setTargetsAsNull() {
        this.targets = null;
    }

    protected IntVector2D findWeakestUnitVector(ArrayList<Unit> units) {
        int weakestHp = Integer.MAX_VALUE;

        for (Unit unit : units) {
            if (unit.hp < weakestHp) {
                weakestHp = unit.hp;
            }
        }

        IntVector2D weakestVector = null;
        for (Unit unit : units) {
            if (unit.hp == weakestHp) {
                weakestVector = unit.position.copy();
                break;
            }
        }
        assert weakestVector != null;
        return weakestVector;
    }

    protected boolean isTargetInVisible(ArrayList<Unit> units) {
        boolean isTarget = false;

        Loop:
        for (int y = position.getY() - vision; y <= position.getY() + vision; ++y) {
            for (int x = position.getX() - vision; x <= position.getX() + vision; ++x) {
                for (Unit unit : units) {
                    if (this != unit && unit.position.getX() == x && unit.position.getY() == y) {
                        isTarget = true;
                        break Loop;
                    }
                }
            }
        }
        return isTarget;
    }

    protected ArrayList<Unit> getTargetsInVisible(ArrayList<Unit> units) {
        ArrayList<Unit> targets = new ArrayList<>();

        for (int y = position.getY() - vision; y <= position.getY() + vision; ++y) {
            for (int x = position.getX() - vision; x <= position.getX() + vision; ++x) {
                for (Unit unit : units) {
                    if (this != unit && unit.position.getX() == x && unit.position.getY() == y) {
                        targets.add(unit);
                    }
                }
            }
        }
        return targets;
    }

    protected ArrayList<Unit> findNearestUnits(ArrayList<Unit> units) {
        int distance = Integer.MAX_VALUE;
        int x = position.getX();
        int y = position.getY();
        for (Unit unit : units) {
            int comp = Math.abs(x - unit.getPosition().getX()) + Math.abs(y - unit.getPosition().getY());
            if (this != unit && comp < distance) {
                distance = comp;
            }
        }

        ArrayList<Unit> nearestUnits = new ArrayList<>();
        for (Unit unit : units) {
            int comp = Math.abs(x - unit.getPosition().getX()) + Math.abs(y - unit.getPosition().getY());
            if (this != unit && comp == distance) {
                nearestUnits.add(unit);
            }
        }
        return nearestUnits;
    }

    protected ArrayList<Unit> findWeakestUnits(ArrayList<Unit> units) {
        int weakestHp = Integer.MAX_VALUE;
        for (Unit unit : units) {
            if (unit.hp < weakestHp) {
                weakestHp = unit.hp;
            }
        }

        ArrayList<Unit> weakestUnits = new ArrayList<>();
        for (Unit unit : units) {
            if (unit.hp == weakestHp) {
                weakestUnits.add(unit);
            }
        }
        return weakestUnits;
    }

    protected IntVector2D findVectorToMove(ArrayList<Unit> units) {
        assert (units.size() != 0);
        int distance = Math.abs(units.get(0).position.getX() - this.position.getX()) + Math.abs(units.get(0).position.getY() - this.position.getY());

        ArrayList<IntVector2D> vectors = new ArrayList<>();

        for (int i = 0; i <= distance; i++) {
            int j = distance - i;
            int x = position.getX() + i;
            int y = position.getY() - j;
            if (x >= 0 && x < 16 && y >= 0 && y < 16) {
                vectors.add(new IntVector2D(x, y));
            }
        }

        for (int i = 1; i <= distance; i++) {
            int j = distance - i;
            int x = position.getX() + j;
            int y = position.getY() + i;
            if (x >= 0 && x < 16 && y >= 0 && y < 16) {
                vectors.add(new IntVector2D(x, y));
            }
        }

        for (int i = 1; i <= distance; i++) {
            int j = distance - i;
            int x = position.getX() - i;
            int y = position.getY() + j;
            if (x >= 0 && x < 16 && y >= 0 && y < 16) {
                vectors.add(new IntVector2D(x, y));
            }
        }

        for (int i = 1; i < distance; i++) {
            int j = distance - i;
            int x = position.getX() - j;
            int y = position.getY() - i;
            if (x >= 0 && x < 16 && y >= 0 && y < 16) {
                vectors.add(new IntVector2D(x, y));
            }
        }

        IntVector2D towardVector = null;
        LOOP:
        for (IntVector2D vector : vectors) {
            for (Unit unit : units) {
                if (vector.equals(unit.position)) {
                    towardVector = unit.position;
                    break LOOP;
                }
            }
        }

        assert towardVector != null;

        // x와 y 모두 같은 경우는 없지않나? 없지
        if (towardVector.getY() != this.position.getY()) {
            return this.position.getY() - towardVector.getY() > 0 ? new IntVector2D(this.position.getX(), this.position.getY() - 1) : new IntVector2D(this.position.getX(), this.position.getY() + 1);
        }
        return this.position.getX() - towardVector.getX() > 0 ? new IntVector2D(this.position.getX() - 1, this.position.getY()) : new IntVector2D(this.position.getX() + 1, this.position.getY());
    }

    protected ArrayList<Unit> findUnitsToAttack(ArrayList<Unit> units, ArrayList<IntVector2D> attackablePositions) {
        ArrayList<Unit> targets = new ArrayList<>();
        for (IntVector2D vector : attackablePositions) {
            for (Unit unit : units) {
                if (unit != this && unit.position.equals(vector)) {
                    targets.add(unit);
                }
            }
        }
        return targets;
    }
}