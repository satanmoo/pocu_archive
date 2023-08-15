package academy.pocu.comp2500.assignment2;

import java.util.ArrayList;

public class ApertureProduct extends Product {
    private ArrayList<Aperture> apertures = new ArrayList<>();

    protected ApertureProduct(int price, String displayName, Size size, RGB color) {
        super(price, displayName, size, color);
    }

    public ArrayList<Aperture> getApertures() {
        return apertures;
    }

    public void addAperture(Aperture aperture) {
        if (aperture.getX() >= size.getHeight() || aperture.getX() < 0 || aperture.getY() >= size.getWidth() || aperture.getY() < 0) {
            return;
        }

        apertures.add(aperture);
        price += 5;
    }
}
