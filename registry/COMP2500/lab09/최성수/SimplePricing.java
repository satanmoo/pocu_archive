package academy.pocu.comp2500.lab9;

import java.util.Collection;

public class SimplePricing implements IPricingModel {
    public int getTotalPrice(Collection<Book> books) {
        return books.stream().mapToInt(Book::getPrice).sum();
    }
}
