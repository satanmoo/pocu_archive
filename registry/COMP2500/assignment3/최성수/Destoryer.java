package academy.pocu.comp2500.assignment3;

import java.util.ArrayList;

public class Destoryer extends Unit implements IThinkable {

    public Destoryer(IntVector2D position) {
        super(position);
        super.symbol = 'D';
        super.hp = Integer.MAX_VALUE;
    }

    public void onAttacked(int damage) {
        hp = Math.max(0, hp - 1);
    }

    @Override
    public void onSpawn() {
        SimulationManager.getInstance().registerThinkable(this);
        SimulationManager.getInstance().setDestoryer(this);
    }

    @Override
    public void determineAction() {
        targets = SimulationManager.getInstance().getUnits();
    }

    @Override
    public AttackIntent attack() {
        if (super.targets == null) {
            return null;
        }

        ArrayList<Unit> units = super.targets;

        ArrayList<IntVector2D> allPositions = new ArrayList<>();
        for (int y = 0; y < 8; ++y) {
            for (int x = 0; x < 16; ++x) {
                allPositions.add(new IntVector2D(x, y));
            }
        }

        return new AttackIntent(this, allPositions, 200, 0, units);
    }
}
