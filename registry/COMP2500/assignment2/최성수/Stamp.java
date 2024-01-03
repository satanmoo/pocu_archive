package academy.pocu.comp2500.assignment2;

public class Stamp extends Product {
    private final String text;

    public Stamp(StampColor stampColor, StampSize stampSize, String text) {
        super(getStampPrice(stampSize), getDisplayName(getStampSize(stampSize)), getStampSize(stampSize), getStampColor(stampColor));
        this.text = text;
    }

    private static int getStampPrice(StampSize stampSize) {
        if (stampSize == StampSize.WIDTH_70_HEIGHT_40) {
            return 2600;
        } else {
            return 2300;
        }
    }

    private static RGB getStampColor(StampColor stampColor) {
        if (stampColor == StampColor.RED) {
            return new RGB(0xFF, 0, 0);
        } else if (stampColor == StampColor.BLUE) {
            return new RGB(0, 0, 0xFF);
        } else {
            return new RGB(0, 0x80, 0);
        }
    }

    private static Size getStampSize(StampSize stampSize) {
        if (stampSize == StampSize.WIDTH_40_HEIGHT_30) {
            return new Size(40, 30);
        } else if (stampSize == StampSize.WIDTH_50_HEIGHT_20) {
            return new Size(50, 20);
        } else {
            return new Size(70, 40);
        }
    }

    private static String getDisplayName(Size size) {
        return String.format("Stamp (%d mm x %d mm)", size.getWidth(), size.getHeight());
    }

    public String getText() {
        return text;
    }
}

