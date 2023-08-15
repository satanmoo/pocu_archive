package academy.pocu.comp2500.assignment2;

import java.util.ArrayList;

public class ApertureAdder extends Product {
    private ArrayList<Aperture> apertures = new ArrayList<>();

    protected ApertureAdder(ShippingType shippingMethod) {
        super(shippingMethod);
    }

    public void addAperture(Aperture aperture) {
        if (!this.isValidAperture(aperture)) {
            return;
        }
        this.apertures.add(aperture);
        this.price += 5;
    }

    public ArrayList<Aperture> getApertures() {
        return apertures;
    }

    private boolean isValidAperture(Aperture aperture) {
        if (aperture.getX() < 0 || aperture.getY() < 0 || aperture.getX() > aperture.getSize().getWidth() || aperture.getY() > aperture.getSize().getHeight()) {
            return false;
        }
        if (aperture.getX() + aperture.getSize().getWidth() > this.getSize().getWidth() || aperture.getY() + aperture.getSize().getHeight() > this.getSize().getHeight()) {
            return false;
        }
        return true;
    }
}
