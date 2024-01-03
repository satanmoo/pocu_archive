package academy.pocu.comp2500.assignment2;

import java.awt.print.Paper;
import java.util.UUID;

public class BusinessCard extends AdvancedProduct {
    protected BusinessCardSides businessCardSides;
    protected PaperMaterial paperMaterial;
    protected BusinessCardColor businessCardColor;

    public BusinessCard(UUID id,
                        ShippingOption shippingMethod,
                        Orientation orientation,
                        BusinessCardSides businessCardSides,
                        PaperMaterial paperMaterial,
                        BusinessCardColor businessCardColor) {
        super(id, shippingMethod);
        super.name = "Business Card";
        super.orientation = orientation;
        super.size = new Size(90, 50);

        this.businessCardSides = businessCardSides;
        this.businessCardColor = businessCardColor;
        this.paperMaterial = paperMaterial;

        super.color = convertColorByBusinessCardColor();

        setBusinessCardDisplayName();
        updateBusinessCardPrice();
    }

    public BusinessCardSides getBusinessCardSides() {
        return this.businessCardSides;
    }

    public PaperMaterial getPaperMaterial() {
        return this.paperMaterial;
    }

    private Color convertColorByBusinessCardColor() {
        Color copyColor = new Color(0xFF, 0xFF, 0xFF);
        switch (this.businessCardColor) {
            case IVORY:
                copyColor.setColor(0xE6, 0xE6, 0xE6);
                break;
            case GRAY:
                copyColor.setColor(0xFF, 0xFF, 0xF0);
                break;
            case WHITE:
                copyColor.setColor(0xFF, 0xFF, 0xFF);
                break;
            default:
                assert (false) : "Uncongnized Business Card Color type: " + this.businessCardColor;
                break;
        }
        return copyColor;
    }

    private void updateBusinessCardPrice() {
        int price = 100;

        if (this.businessCardSides == BusinessCardSides.DOUBLE) {
            price += 30;
        }
        switch (this.paperMaterial) {
            case LINEN:
                price += 10;
                break;
            case LAID:
                price += 20;
                break;
            case SMOOTH:
                break;
            default:
                assert (false) : "Uncongnized Paper Material: " + this.paperMaterial;
                break;
        }
        super.price = price;
    }

    private void setBusinessCardDisplayName() {
        String copyDisplayName = super.name;
        switch (this.paperMaterial) {
            case LINEN:
                copyDisplayName = "Linen ".concat(copyDisplayName);
                break;
            case LAID:
                copyDisplayName = "Laid ".concat(copyDisplayName);
                break;
            case SMOOTH:
                copyDisplayName = "Smooth ".concat(copyDisplayName);
                break;
            default:
                assert (false) : "Uncongnized Paper Material: " + this.paperMaterial;
                break;
        }
        super.displayName = copyDisplayName;
    }
}
