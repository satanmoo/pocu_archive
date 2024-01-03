package academy.pocu.comp2500.assignment2;

import java.util.UUID;

public class Product {
    protected UUID id;
    protected ShippingOption shippingMethod;
    protected String name;
    protected String displayName;
    protected Color color = new Color(0xFF, 0xFF, 0xFF);
    protected Size size = new Size(1, 1);
    protected int price;

    protected Product(UUID id, ShippingOption shippingMethod) {
        this.id = id;
        this.shippingMethod = shippingMethod;
    }

    public UUID getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public Color getColor() {
        return this.color;
    }

    public Size getSize() {
        return this.size;
    }

    public int getPrice() {
        return this.price;
    }

    public ShippingOption getShippingMethod() {
        return this.shippingMethod;
    }

    public void setShippingMethod(ShippingOption shippingMethod) {
        this.shippingMethod = shippingMethod;
    }
}
