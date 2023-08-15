package academy.pocu.comp2500.assignment2;

public class Size {
    private int width;
    private int height;

    public Size(int width, int height) {
        this.width = Math.max(0, width);
        this.height = Math.max(0, height);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
