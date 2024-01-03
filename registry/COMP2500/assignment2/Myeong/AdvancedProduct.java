package academy.pocu.comp2500.assignment2;

import java.util.ArrayList;
import java.util.UUID;

public class AdvancedProduct extends Product {
    protected Orientation orientation = Orientation.LANDSCAPE;
    protected ArrayList<Aperture> apertures = new ArrayList<>();
    protected int aperturesPrice = 0;

    protected AdvancedProduct(UUID id, ShippingOption shippingMethod) {
        super(id, shippingMethod);
    }

    public Orientation getOrientation() {
        return this.orientation;
    }

    public Aperture getApertureOrNull(UUID apertureId) {
        for (Aperture aperture : this.apertures) {
            if (aperture.getId().equals(apertureId)) {
                return aperture;
            }
        }
        return null;
    }

    public ArrayList<Aperture> getApertures() {
        return this.apertures;
    }

    protected boolean isValidAperture(Aperture aperture) {
        if ((aperture.getX() < 0 || aperture.getX() > super.size.getWidth())
                || (aperture.getY() < 0 || aperture.getY() > super.size.getHeight())) {
            return false;
        }

        if ((super.size.getHeight() <= aperture.getY() + aperture.getSize().getHeight())
                || (super.size.getWidth() <= aperture.getX() + aperture.getSize().getWidth())) {
            return false;
        }
        return true;
    }

    public void addAperture(Aperture aperture) {
        if (!isValidAperture(aperture)) {
            return;
        }
        this.apertures.add(aperture);
        updateApertruePrice();
    }

    public void removeAperture(Aperture aperture) {
        boolean isContain = this.apertures.remove(aperture);
        if (isContain) {
            updateApertruePrice();
        }
    }

    private void updateApertruePrice() {
        int price = 0;
        int count = this.apertures.size();
        if (count != 0) {
            price += count * 5;
        }
        int diffPrice = price - this.aperturesPrice;
        super.price += diffPrice;
        this.aperturesPrice = price;

    }
}
