//----------------------Book.java----------------------

package academy.pocu.comp2500.lab9;

import java.util.UUID;

public final class Book {
    private final UUID sku;
    private final String title;
    private final int price;
    private final int publishedYear;

    public Book(final UUID sku, final String title, final int price, final int publishedYear) {
        this.sku = sku;
        this.title = title;
        this.price = price;
        this.publishedYear = publishedYear;
    }

    public int getPrice() {
        return this.price;
    }

    public int getPublishedYear() {
        return this.publishedYear;
    }

    public String getTitle() {
        return this.title;
    }

    public UUID getSku() {
        return this.sku;
    }
}


//----------------------BuyOneGetOneFree.java----------------------

package academy.pocu.comp2500.lab9;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.UUID;

public class BuyOneGetOneFree implements ISaleable {
    private final HashSet<UUID> buyOneGetOneFreeBookSkus;

    public BuyOneGetOneFree(final HashSet<UUID> buyOneGetOneFreeBookSkus) {
        this.buyOneGetOneFreeBookSkus = buyOneGetOneFreeBookSkus;
    }

    @Override
    public int getTotalPrice(final Collection<Book> books) {
        int sum = 0;
        HashMap<UUID, Integer> duplicationBookCounts = new HashMap<>();

        for (Book book : books) {
            final UUID bookSku = book.getSku();
            if (this.buyOneGetOneFreeBookSkus.contains(bookSku)) {
                if (!duplicationBookCounts.containsKey(bookSku)) {
                    duplicationBookCounts.put(book.getSku(), 1);

                    continue;
                }
                int increaseDuplicationBookCount = duplicationBookCounts.get(bookSku);

                duplicationBookCounts.put(book.getSku(), ++increaseDuplicationBookCount);
            }
        }

        for (Book book : books) {
            UUID bookSku = book.getSku();

            if (duplicationBookCounts.containsKey(bookSku)) {
                int bookCount = duplicationBookCounts.get(bookSku);

                if (bookCount == 0) {
                    continue;
                }
                sum += book.getPrice() * Math.ceil(bookCount / 2.0);

                duplicationBookCounts.put(bookSku, 0);
            } else {
                sum += book.getPrice();
            }
        }

        return sum;
    }
}


//----------------------Cart.java----------------------

package academy.pocu.comp2500.lab9;

import java.util.ArrayList;

public final class Cart {
    private ArrayList<Book> books = new ArrayList<>();

    public Cart() {
    }

    public Book getBookOrNull(final int index) {
        if (this.books.size() <= index) {
            return null;
        }

        return this.books.get(index);
    }

    public int getBookCount() {
        return this.books.size();
    }

    public void addBooks(final ArrayList<Book> books) {
        for (Book book : books) {
            this.books.add(book);
        }
    }

    public void addBook(final Book book) {
        this.books.add(book);
    }

    public boolean remove(final int index) {
        if (this.books.size() <= index) {
            return false;
        }

        this.books.remove(index);

        return true;
    }

    public int getTotalPrice(final ISaleable saleable) {
        return saleable.getTotalPrice(this.books);
    }
}


//----------------------DecadeMadness.java----------------------

package academy.pocu.comp2500.lab9;

import java.util.Collection;
import java.util.HashMap;

public class DecadeMadness implements ISaleable {
    @Override
    public int getTotalPrice(final Collection<Book> books) {
        int sum = 0;
        HashMap<Integer, Integer> sameDecadeBookCounts = new HashMap<>();

        int sameDecadeYear;
        // XXX0~XXX9 = X : 동일 숫자, 마지막 숫자: 상관 X
        for (Book book : books) {
            sameDecadeYear = book.getPublishedYear() / 10;

            if (!sameDecadeBookCounts.containsKey(sameDecadeYear)) {
                sameDecadeBookCounts.put(sameDecadeYear, 1);
                continue;
            }
            int increaseSameDecadeBookCount = sameDecadeBookCounts.get(sameDecadeYear);
            sameDecadeBookCounts.put(sameDecadeYear, ++increaseSameDecadeBookCount);
        }

        // 동시대 년도 2개 이상 아닐 경우, 제거
        sameDecadeBookCounts.values().removeIf(value -> value < 2);

        HashMap<Integer, Integer> sameDecadeBookTotalPrices = new HashMap<>();

        for (Book book : books) {
            sameDecadeYear = book.getPublishedYear() / 10;

            if (sameDecadeBookCounts.containsKey(sameDecadeYear)) {
                if (!sameDecadeBookTotalPrices.containsKey(sameDecadeYear)) {
                    sameDecadeBookTotalPrices.put(sameDecadeYear, book.getPrice());
                } else {
                    int price = sameDecadeBookTotalPrices.get(sameDecadeYear);
                    sameDecadeBookTotalPrices.put(sameDecadeYear, price + book.getPrice());
                }
            } else {
                sum += book.getPrice();
            }
        }

        for (int key : sameDecadeBookTotalPrices.keySet()) {
            sum += (int) (sameDecadeBookTotalPrices.get(key) * 0.8);
        }

        return sum;
    }
}


//----------------------ISaleable.java----------------------

package academy.pocu.comp2500.lab9;


import java.util.Collection;

public interface ISaleable {
    int getTotalPrice(final Collection<Book> books);
}


//----------------------SimplePricing.java----------------------

package academy.pocu.comp2500.lab9;

import java.util.ArrayList;
import java.util.Collection;

public class SimplePricing implements ISaleable {

    public SimplePricing() {
    }

    @Override
    public int getTotalPrice(final Collection<Book> books) {
        int sum = 0;

        for (Book book : books) {
            sum += book.getPrice();
        }

        return sum;
    }
}


//----------------------SkyIsTheLimit.java----------------------

package academy.pocu.comp2500.lab9;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public final class SkyIsTheLimit implements ISaleable {
    private final int minDiscountTotalPrice;

    public SkyIsTheLimit(final int price) {
        this.minDiscountTotalPrice = price;
    }

    @Override
    public int getTotalPrice(final Collection<Book> books) {
        // Cart 의 기본 getTotalPrice를 호출
        int booksTotalPrice = 0;
        for (Book book : books) {
            booksTotalPrice += book.getPrice();
        }

        if (books.size() < 5 || booksTotalPrice < this.minDiscountTotalPrice) {
            return booksTotalPrice;
        }

        double sum = 0;
        ArrayList<Double> descendingOrderPrice = new ArrayList<>();

        for (Book book : books) {
            descendingOrderPrice.add((double) book.getPrice());
        }
        Collections.sort(descendingOrderPrice, Collections.reverseOrder());

        descendingOrderPrice.set(0, descendingOrderPrice.get(0) / 2.0);
        descendingOrderPrice.set(1, descendingOrderPrice.get(1) / 2.0);

        for (double price : descendingOrderPrice) {
            sum += price;
        }

        return (int) Math.floor(sum);
    }
}


