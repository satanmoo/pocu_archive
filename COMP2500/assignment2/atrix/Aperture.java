package academy.pocu.comp2500.assignment2;

public class Aperture {
    private int x;
    private int y;
    private Size size;

    protected Aperture(int x, int y, Size size) {
        this.x = x;
        this.y = y;
        this.size = size;
    }
    
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Size getSize() {
        return size;
    }
}
