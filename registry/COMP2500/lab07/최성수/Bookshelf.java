package academy.pocu.comp2500.lab7;

import java.util.ArrayList;

public class Bookshelf {
    private final int maxBookCount;
    private final ArrayList<Book> books = new ArrayList<>();

    public Bookshelf(int maxBookCount) {
        this.maxBookCount = maxBookCount;
    }

    public boolean add(Book book) {
        if (maxBookCount == books.size()) {
            return false;
        }
        books.add(book);
        return true;
    }

    public boolean remove(Book bookToRemove) {
        return books.remove(bookToRemove);
    }

    public int hashCode() {
        return super.hashCode();
    }

    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
