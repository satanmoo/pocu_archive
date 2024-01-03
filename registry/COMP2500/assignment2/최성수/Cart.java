package academy.pocu.comp2500.assignment2;

import java.util.ArrayList;

public class Cart {
    ArrayList<Product> products = new ArrayList<>();

    public void addShoppingCart(Product product) {
        products.add(product);
    }

    public void removeProduct(Product product) {
        for (Product p : products) {
            if (p.equals(product)) {
                products.remove(product);
                return;
            }
        }
    }

    public void changeShippingMethod(Product product, ShippingMethod shippingMethod) {
        for (Product p : products) {
            if (p.equals(product)) {
                product.setShippingMethod(shippingMethod);
                return;
            }
        }
    }

    public int getTotalPrice() {
        int price = 0;
        for (Product product : products) {
            price += product.getPrice();
        }
        return price;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }
}
