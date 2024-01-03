package academy.pocu.comp2500.assignment3;

public class IntVector2D {
    private int x;
    private int y;

    public IntVector2D(int x, int y) {
        this.x = x;
        this.y = y;
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

    public int hashCode() {
        int hash = 17;
        hash = hash * 31 + x;
        hash = hash * 31 + y;
        return hash;
    }

    public boolean equals(IntVector2D other) {
        if (this == other) {
            return true;
        }
        if (hashCode() != other.hashCode()) {
            return false;
        }
        return x == other.x && y == other.y;
    }

    public IntVector2D copy() {
        return new IntVector2D(this.x, this.y);
    }
}