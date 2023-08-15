package academy.pocu.comp2500.assignment2;

import java.util.UUID;

public class ImageAperture extends Aperture {
    private String imagePath;
    public ImageAperture(UUID id, int x, int y, Size size, String imagePath) {
        super(id, x, y, size);
        this.imagePath = imagePath;
    }

    public String getImagePath() {
        return this.imagePath;
    }

}
