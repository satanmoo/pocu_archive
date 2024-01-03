package academy.pocu.comp2500.lab5;

public class Pet {
    private final String name;
    private final int attack;

    public Pet(final String name, final int attack) {
        assert name != null;
        assert attack > 0;

        this.name = name;
        this.attack = attack;
    }

    public int getAttack() {
        return this.attack;
    }

}
