package academy.pocu.comp2500.assignment2;

public class BusinessCard extends ApertureProduct {
    private final BusinessCardType businessCardType;
    private final Orientation businessCardOrientation;
    private final Sides businessCardSides;

    public BusinessCard(BusinessCardType businessCardType, BusinessCardColor businessCardColor, Orientation businessCardOrientation, Sides businessCardSides) {
        super(getPrice(businessCardType, businessCardSides), getDisplayName(businessCardType), new Size(90, 50), getColor(businessCardColor));
        this.businessCardType = businessCardType;
        this.businessCardOrientation = businessCardOrientation;
        this.businessCardSides = businessCardSides;
    }

    private static String getDisplayName(BusinessCardType businessCardType) {
        return String.format("%s Business Card", businessCardType);
    }

    private static int getPrice(BusinessCardType type, Sides sides) {
        int price;
        if (type == BusinessCardType.LINEN) {
            price = 110;
        } else if (type == BusinessCardType.LAID) {
            price = 120;
        } else {
            price = 100;
        }
        if (sides == Sides.DOUBLE) {
            price += 30;
        }
        return price;
    }

    private static RGB getColor(BusinessCardColor color) {
        if (color == BusinessCardColor.GREY) {
            return new RGB(0xE6, 0xE6, 0xE6);
        } else if (color == BusinessCardColor.IVORY) {
            return new RGB(0xFF, 0xFF, 0xF0);
        } else {
            return new RGB(0xFF, 0xFF, 0xFF);
        }
    }

    public Orientation getBusinessCardOrientation() {
        return businessCardOrientation;
    }

    public Sides getBusinessCardSides() {
        return businessCardSides;
    }

    public BusinessCardType getBusinessCardMaterial() {
        return businessCardType;
    }
}
