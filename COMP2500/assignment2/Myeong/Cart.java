package academy.pocu.comp2500.assignment2;

import java.util.ArrayList;
import java.util.UUID;

public class Cart {
    private UUID id;
    private ArrayList<Product> products = new ArrayList<>();
    private int totalPrice;

    public Cart(UUID id) {
        this.id = id;
    }

    public ArrayList<Product> getProducts() {
        return this.products;
    }

    public Product getProductOrNull(UUID productId) {
        for (Product product : this.products) {
            if (product.getId().equals(productId)) {
                return product;
            }
        }
        return null;
    }

    public void removeProduct(Product product) {
        this.products.remove(product);
        updateTotalPrice();
    }

    public void addProduct(Product product) {
        this.products.add(product);
        updateTotalPrice();
    }

    public int getTotalPrice() {
        updateTotalPrice();
        return this.totalPrice;
    }

    private void updateTotalPrice() {
        this.totalPrice = 0;
        for (Product product : this.products) {
            this.totalPrice += product.getPrice();
        }
    }
}
