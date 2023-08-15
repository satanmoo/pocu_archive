package academy.pocu.comp2500.assignment2;

import java.util.UUID;

public class Stamp extends Product {
    private String text;
    private StampColor stampColor;
    private StampSize stampSize;

    public Stamp(UUID id,
                 ShippingOption shippingMethod,
                 String text,
                 StampColor stampColor,
                 StampSize stampSize) {
        super(id, shippingMethod);
        super.name = "Stamp";

        this.stampColor = stampColor;
        this.stampSize = stampSize;

        super.color = convertColorByStampColor();
        super.size = convertSizeByStampSize();

        this.text = text;

        setStampDisplayName();
        updateStampPrice();
    }

    public String getText() {
        return this.text;
    }

    private Color convertColorByStampColor() {
        Color copyColor = new Color(0xFF, 0xFF, 0xFF);
        switch (this.stampColor) {
            case RED:
                copyColor.setColor(0xFF, 0x00, 0x00);
                break;

            case GREEN:
                copyColor.setColor(0x00, 0x80, 0x00);
                break;

            case BLUE:
                copyColor.setColor(0x00, 0x00, 0xFF);
                break;

            default:
                assert (false) : "Unrecognized stamp color" + this.stampColor;
                break;
        }
        return copyColor;
    }

    private Size convertSizeByStampSize() {
        Size copySize = new Size(1, 1);
        switch (this.stampSize) {
            case SHORT:
                copySize.setSize(40, 30);
                break;

            case MIDDLE:
                copySize.setSize(50, 20);
                break;

            case LONG:
                copySize.setSize(70, 40);
                break;

            default:
                assert (false) : "Unrecognized stamp size" + this.stampSize;
                break;
        }
        return copySize;
    }

    private void updateStampPrice() {
        int price = 2300;
        if (this.stampSize.equals(StampSize.LONG)) {
            price += 300;
        }
        super.price = price;
    }

    private void setStampDisplayName() {
        String copyDisplayName = super.name;
        switch (this.stampSize) {
            case SHORT:
                copyDisplayName = copyDisplayName.concat(" (40 mm x 30 mm)");
                break;

            case MIDDLE:
                copyDisplayName = copyDisplayName.concat(" (50 mm x 20 mm)");
                break;

            case LONG:
                copyDisplayName = copyDisplayName.concat(" (70 mm x 40 mm)");
                break;

            default:
                assert (false) : "Unrecognized stamp size" + this.stampSize;
                break;
        }
        super.displayName = copyDisplayName;
    }
}
