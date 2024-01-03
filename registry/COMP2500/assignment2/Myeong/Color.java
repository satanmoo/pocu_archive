package academy.pocu.comp2500.assignment2;

public class Color {
    private int red;
    private int green;
    private int blue;

    public Color(int red, int green, int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public int getRed() {
        return this.red;
    }

    public int getGreen() {
        return this.green;
    }

    public int getBlue() {
        return this.blue;
    }

    public int getColorByIntegar() {
        int colorValue = (this.red & 0xFF) << 16;
        colorValue += (this.green & 0xFF) << 8;
        colorValue += (this.blue & 0xFF);
        return colorValue;
    }

    public void setColor(int r, int g, int b) {
        this.red = r;
        this.green = g;
        this.blue = b;
    }

    public void setColorByInteger(int color) {
        this.red = ((color >> 16) & 0xFF);
        this.green = ((color >> 8) & 0xFF);
        this.blue = (color & 0xFF);
    }
}
