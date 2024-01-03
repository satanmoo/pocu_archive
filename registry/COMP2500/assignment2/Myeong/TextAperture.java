package academy.pocu.comp2500.assignment2;

import java.util.UUID;

public class TextAperture extends Aperture {
    private String text;
    public TextAperture(UUID id, int x, int y, Size size, String text) {
        super(id, x, y, size);
        this.text = text;
    }

    public String getText() {
        return this.text;
    }
}
