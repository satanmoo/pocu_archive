package academy.pocu.comp2500.assignment3;

public class MoveIntent {
    private final Unit mover;
    private final IntVector2D location;

    MoveIntent(Unit mover, IntVector2D location) {
        this.mover = mover;
        this.location = location;
    }

    public void move() {
        mover.position.setX(location.getX());
        mover.position.setY(location.getY());
    }
}
