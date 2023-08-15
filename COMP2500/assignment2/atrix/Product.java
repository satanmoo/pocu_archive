package academy.pocu.comp2500.assignment2;

public class Product {
    protected Size size;
    protected int price;
    protected Color color;
    protected ShippingType shippingMethod;
    protected String displayName;

    protected Product(ShippingType shippingMethod) {
        this.shippingMethod = shippingMethod;
    }

    public Size getSize() {
        return size;
    }

    public int getPrice() {
        return price;
    }

    public Color getColor() {
        return color;
    }

    public ShippingType getShippingMethod() {
        return shippingMethod;
    }

    public void setShippingMethod(ShippingType shippingMethod) {
        this.shippingMethod = shippingMethod;
    }

    public String getDisplayName() {
        return displayName;
    }
}
