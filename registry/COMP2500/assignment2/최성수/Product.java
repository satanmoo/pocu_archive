package academy.pocu.comp2500.assignment2;


public class Product {
    protected int price;
    private ShippingMethod shippingMethod;
    private String displayName;
    protected Size size;
    protected RGB color;

    protected Product(int price, String displayName, Size size, RGB color) {
        this.price = Math.max(0, price);
        this.displayName = displayName;
        this.size = size;
        this.color = color;
    }

    public int getPrice() {
        return price;
    }

    public ShippingMethod getShippingMethodOrNull() {
        return shippingMethod;
    }

    public void setShippingMethod(ShippingMethod shippingMethod) {
        this.shippingMethod = shippingMethod;
    }

    public String getDisplayName() {
        return displayName;
    }

    public Size getSize() {
        return size;
    }

    public RGB getColor() {
        return color;
    }
}
