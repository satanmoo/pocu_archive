package academy.pocu.comp2500.assignment2;

public class ImageAperture extends Aperture {
    public String imagePath; // or null?

    public ImageAperture(String imagePath, int x, int y) {
        super(x, y);
        this.imagePath = imagePath;
    }

    public String getImagePathOrNull() {
        return imagePath;
    }
}
