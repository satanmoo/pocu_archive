package academy.pocu.comp2500.lab9;

import java.util.ArrayList;
import java.util.UUID;

public final class Cart {
    private ArrayList<Book> books = new ArrayList<>();

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
        this.books.addAll(books);
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

    public int getTotalPrice(IPricingModel pricingModel) {
        return pricingModel.getTotalPrice(this.books);
    }
}