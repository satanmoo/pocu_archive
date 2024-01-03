package academy.pocu.comp2500.assignment2;

public class TextAperture extends Aperture {
    private String text;

    public TextAperture(int x, int y, String text) {
        super(x, y);
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
