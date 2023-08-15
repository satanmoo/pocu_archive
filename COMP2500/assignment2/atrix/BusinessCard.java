package academy.pocu.comp2500.assignment2;

public class BusinessCard extends ApertureAdder {
    private BusinessCardType businessCardType;
    private BusinessCardSides businessCardSides;
    private OrientationType orientation;

    public BusinessCard(ShippingType shippingMethod, BusinessCardColor businessCardColor, BusinessCardType businessCardType, BusinessCardSides businessCardSides, OrientationType orientation) {
        super(shippingMethod);
        this.size = new Size(90, 50);
        this.businessCardType = businessCardType;
        this.businessCardSides = businessCardSides;
        this.orientation = orientation;
        this.setColor(businessCardColor);
        this.setPrice();
        this.setDisplayName();
    }

    public BusinessCardType getBusinessCardType() {
        return businessCardType;
    }

    public BusinessCardSides getBusinessCardSides() {
        return businessCardSides;
    }

    public OrientationType getOrientation() {
        return orientation;
    }
    
    private void setPrice() {
        if (businessCardType == BusinessCardType.LINEN) {
            if (businessCardSides == BusinessCardSides.SINGLE) {
                this.price = 110;
            } else {
                this.price = 140;
            }
        } else if (businessCardType == BusinessCardType.LAID) {
            if (businessCardSides == BusinessCardSides.SINGLE) {
                this.price = 120;
            } else {
                this.price = 150;
            }
        } else if (businessCardType == BusinessCardType.SMOOTH) {
            if (businessCardSides == BusinessCardSides.SINGLE) {
                this.price = 100;
            } else {
                this.price = 130;
            }
        }
    }

    private void setDisplayName() {
        if (businessCardType == BusinessCardType.LAID) {
            this.displayName = "Laid Business Card";
        } else if (businessCardType == BusinessCardType.SMOOTH) {
            this.displayName = "Smooth Business Card";
        } else if (businessCardType == BusinessCardType.LINEN) {
            this.displayName = "Linen Business Card";
        }
    }

    private void setColor(BusinessCardColor businessCardColor) {
        if (businessCardColor == BusinessCardColor.GRAY) {
            this.color = new Color(230, 230, 230);
        } else if (businessCardColor == BusinessCardColor.IVORY) {
            this.color = new Color(255, 255, 240);
        } else if (businessCardColor == BusinessCardColor.WHITE) {
            this.color = new Color(255, 255, 255);
        }
    }
}
