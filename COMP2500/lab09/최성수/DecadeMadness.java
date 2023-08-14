package academy.pocu.comp2500.lab9;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class DecadeMadness implements IPricingModel {
    private static final double DISCOUNT_RATE = 0.2;

    public int getTotalPrice(Collection<Book> books) {
        HashMap<Integer, ArrayList<Book>> dupDecadeTable = new HashMap<>();
        books.forEach(book -> {
            int key = book.getPublishedYear() / 10;
            if (dupDecadeTable.containsKey(key)) {
                dupDecadeTable.get(key).add(book);
            } else {
                ArrayList<Book> value = new ArrayList<>();
                value.add(book);
                dupDecadeTable.put(key, value);
            }
        });


        double totalSum = 0.0;
        for (ArrayList<Book> decadeBooks : dupDecadeTable.values()) {
            int sum = decadeBooks.stream().mapToInt(Book::getPrice).sum();
            totalSum += decadeBooks.size() >= 2 ? sum * (1 - DISCOUNT_RATE) : sum;
        }

        return (int) totalSum;
    }
}
