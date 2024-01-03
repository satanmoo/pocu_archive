// Pet.java
package academy.pocu.comp2500.lab5;

public class Pet {
    private String name;
    private int attack;

    public Pet(String name, int attack) {
        this.name = name;
        this.attack = attack;
    }

    public String getName() {
        return name;
    }

    public int getAttack() {
        return attack;
    }
}

// Move.java
package academy.pocu.comp2500.lab5;

public class Move {
    private String name;
    private int power;
    private int maxPowerPoints;
    private int powerPoints;

    public Move(String name, int power, int maxPowerPoints) {
        this.name = name;
        this.power = power;
        this.maxPowerPoints = maxPowerPoints;
        this.powerPoints = maxPowerPoints;
    }

    public String getName() {
        return name;
    }

    public int getPower() {
        return power;
    }

    public int getPowerPoints() {
        return powerPoints;
    }

    public void usePowerPoint() {
        if (powerPoints > 0) {
            --powerPoints;
        }
    }

    public void addPowerPoint() {
        powerPoints = Math.min(maxPowerPoints, powerPoints + 1);
    }
}

// Barbarian.java
package academy.pocu.comp2500.lab5;

public class Barbarian {
    private String name;
    protected int hp;
    protected int maxHp;
    protected int attack;
    protected int defense;

    public Barbarian(String name, int maxHp, int attack, int defense) {
        this.name = name;
        this.maxHp = maxHp;
        this.hp = maxHp;
        this.attack = attack;
        this.defense = defense;
    }

    public int getHp() {
        return hp;
    }

    public void attack(Barbarian enemy) {
        int damage = (this.attack - enemy.defense) / 2;
        attack(enemy, Math.max(1, damage));
    }

    protected void attack(Barbarian enemy, int damage) {
        if (!isAlive() || !enemy.isAlive() || this.equals(enemy)) {
            return;
        }
        enemy.hp = Math.max(0, enemy.hp - damage);
    }

    public boolean isAlive() {
        return hp > 0;
    }

}

// Gladiator.java
package academy.pocu.comp2500.lab5;

import java.util.ArrayList;

public class Gladiator extends Barbarian {
    private ArrayList<Move> movies = new ArrayList<>();

    public Gladiator(String name, int maxHp, int attack, int defense) {
        super(name, maxHp, attack, defense);
    }

    public boolean addMove(Move move) {
        if (movies.size() == 4) {
            return false;
        }
        for (Move movie : movies) {
            if (movie.getName().equals(move.getName())) {
                return false;
            }
        }
        movies.add(move);
        return true;
    }

    public boolean removeMove(String moveName) {
        for (Move movie : movies) {
            if (movie.getName().equals(moveName)) {
                movies.remove(movie);
                return true;
            }
        }
        return false;
    }

    private Move getMoveOrNull(String moveName) {
        for (Move movie : movies) {
            if (movie.getName().equals(moveName)) {
                return movie;
            }
        }
        return null;
    }

    public void attack(String moveName, Barbarian enemy) {
        if (this.equals(enemy)) {
            return;
        }
        Move move = getMoveOrNull(moveName);
        if (move == null || move.getPowerPoints() == 0) {
            return;
        }
        int damage = (int) ((super.attack / (double) enemy.defense * move.getPower()) / 2);
        attack(enemy, Math.max(1, damage));
        move.usePowerPoint();
    }

    public void rest() {
        hp = Math.min(maxHp, hp + 10);
        for (Move movie : movies) {
            movie.addPowerPoint();
        }
    }
}

// Knight.java
package academy.pocu.comp2500.lab5;

public class Knight extends Gladiator {
    private Pet petOrNull;

    public Knight(String name, int maxHp, int attack, int defense) {
        super(name, maxHp, attack, defense);
    }

    public void setPet(Pet petOrNull) {
        this.petOrNull = petOrNull;
    }

    public void attackTogether(Barbarian enemy) {
        if (petOrNull == null) {
            return;
        }
        int damage = (super.attack + petOrNull.getAttack() - enemy.defense) / 2;
        attack(enemy, Math.max(1, damage));
    }
}
