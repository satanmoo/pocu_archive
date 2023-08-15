package academy.pocu.comp2500.assignment3;

public class IntVector2D {
    private int x;
    private int y;

    public IntVector2D(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public IntVector2D(final IntVector2D intVector2D) {
        this.x = intVector2D.getX();
        this.y = intVector2D.getY();
    }

    public int getX() {
        return this.x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return this.y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int distanceManhattan(IntVector2D to) {
        return Math.abs(this.x - to.getX()) + Math.abs(this.y - to.getY());
    }

    public int aoeDistance(IntVector2D to) {
        int absX = Math.abs(this.x - to.x);
        int absY = Math.abs(this.y - to.y);
        return Math.max(absX, absY);
    }

    public IntVector2D getNextIntVector2D(final IntVector2D enemy) {
        int nextX = this.x;
        int nextY = this.y;
        if (enemy.y > this.y) {
            nextY = Math.min(SimulationManager.MAX_Y_INDEX, this.y + 1);
        } else if (enemy.y < this.y) {
            nextY = Math.max(0, this.y - 1);
        } else {
            if (enemy.x > this.x) {
                nextX = Math.min(SimulationManager.MAX_X_INDEX, this.x + 1);
            } else if (enemy.x < this.x) {
                nextX = Math.max(0, this.x - 1);
            }
        }

        return new IntVector2D(nextX, nextY);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || (this.hashCode() != o.hashCode())) {
            return false;
        }
        IntVector2D that = (IntVector2D) o;
        return x == that.x && y == that.y;
    }

    @Override
    public int hashCode() {
        return x * 17 + ((y * 17) >> 16);
    }

    @Override
    public String toString() {
        return "IntVector2D{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
