package academy.pocu.comp2500.lab9;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.UUID;

public class BuyOneGetOneFree implements IPricingModel {
    private final HashSet<UUID> eventBookSkus;

    public BuyOneGetOneFree(HashSet<UUID> eventBookSkus) {
        this.eventBookSkus = eventBookSkus;
    }

    public int getTotalPrice(Collection<Book> books) {
        HashMap<UUID, Integer> dupBookCountTable = new HashMap<>();
        HashMap<UUID, Integer> dupBookPriceTable = new HashMap<>();


        books.forEach(book -> {
            if (dupBookCountTable.containsKey(book.getSku())) {
                dupBookCountTable.put(book.getSku(), dupBookCountTable.get(book.getSku()) + 1);
            } else {
                dupBookCountTable.put(book.getSku(), 1);
                dupBookPriceTable.put(book.getSku(), book.getPrice());
            }
        });

        assert dupBookCountTable.size() == dupBookPriceTable.size();

        int sum = 0;

        for (Map.Entry<UUID, Integer> entry : dupBookCountTable.entrySet()) {
            UUID key = entry.getKey();
            Integer count = entry.getValue();

            if (eventBookSkus.contains(key)) {
                sum += dupBookPriceTable.get(key) * (count / 2 + count % 2);
            } else {
                sum += dupBookPriceTable.get(key) * count;
            }
        }

        return sum;
    }
}
