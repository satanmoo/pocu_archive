package academy.pocu.comp2500.assignment2;

public class Stamp extends Product {
    private String text;

    public Stamp(StampSize stampSize, ShippingType shippingMethod, StampColor stampColor, String text) {
        super(shippingMethod);
        this.text = text;
        this.setSize(stampSize);
        this.setColor(stampColor);
        this.setPrice();
        this.setDisplayName();
    }

    public String getText() {
        return text;
    }

    private void setSize(StampSize stampSize) {
        if (stampSize == StampSize.STAMP_SIZE_40_30) {
            this.size = new Size(40, 30);
        } else if (stampSize == StampSize.STAMP_SIZE_50_20) {
            this.size = new Size(50, 20);
        } else if (stampSize == StampSize.STAMP_SIZE_70_40) {
            this.size = new Size(70, 40);
        }
    }

    private void setColor(StampColor stampColor) {
        if (stampColor == StampColor.RED) {
            this.color = new Color(255, 0, 0);
        } else if (stampColor == StampColor.GREEN) {
            this.color = new Color(0, 128, 0);
        } else if (stampColor == StampColor.BLUE) {
            this.color = new Color(0, 0, 255);
        }
    }

    private void setPrice() {
        if (size.getWidth() == 70) {
            this.price = 2600;
        } else {
            this.price = 2300;
        }
    }

    private void setDisplayName() {
        this.displayName = "Stamp " + "(" + size.getWidth() + " mm x " + size.getHeight() + " mm)";
    }
}
