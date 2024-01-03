package academy.pocu.comp2500.assignment2;

public class RGB {
    private int r;
    private int g;
    private int b;

    public RGB(int r, int g, int b) {
        this.r = Math.min(0xff, Math.max(0, r));
        this.g = Math.min(0xff, Math.max(0, g));
        this.b = Math.min(0xff, Math.max(0, b));
    }

    public String getColor() {
        return String.format("0x%s%s%s", Integer.toHexString(r), Integer.toHexString(g), Integer.toHexString(b));
    }

    public int getR() {
        return r;
    }

    public int getG() {
        return g;
    }

    public int getB() {
        return b;
    }
}
