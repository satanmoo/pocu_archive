package academy.pocu.comp2500.lab9;

import java.util.Collection;

public class SkyIsTheLimit implements IPricingModel {
    private static final double DISCOUNT_RATE = 0.5;
    private final int saleThreshold;

    public SkyIsTheLimit(int saleThreshold) {
        this.saleThreshold = saleThreshold;
    }

    public int getTotalPrice(Collection<Book> books) {
        double sum = books.stream().mapToInt(Book::getPrice).sum();

        if (books.size() < 5 || sum < this.saleThreshold) {
            return (int) sum;
        }

        int first = Integer.MIN_VALUE;
        int second = Integer.MIN_VALUE;
        for (Book book : books) {
            int comp = book.getPrice();

            if (comp >= first) {
                second = first;
                first = comp;

            } else if (comp > second) {
                second = comp;
            }
        }

        double total = sum - first * DISCOUNT_RATE - second * DISCOUNT_RATE;
        return (int) total;
    }
}
