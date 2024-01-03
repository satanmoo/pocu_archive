package academy.pocu.comp2500.assignment2;

import java.util.UUID;

public class Aperture {
    protected UUID id;
    protected int x;
    protected int y;
    protected Size size;

    protected Aperture(UUID id, int x, int y, Size size) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.size = size;
    }

    public UUID getId() {
        return this.id;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public Size getSize() {
        return this.size;
    }
}
