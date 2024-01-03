package academy.pocu.comp2500.lab7;

import java.util.HashSet;
import java.util.Set;

public class Bundle {
    private final int MAX_BOOK_COUNT = 4;
    private final String name;
    private final Set<Book> books = new HashSet<>();

    public Bundle(String name) {
        this.name = name;
    }

    public boolean add(Book book) {
        if (MAX_BOOK_COUNT == books.size()) {
            return false;
        }
        return books.add(book);
    }

    public boolean remove(Book bookToRemove) {
        return books.remove(bookToRemove);
    }

    public int hashCode() {
        int hash = 17;
        hash = hash * 31 + name.hashCode();
        hash = hash * 31 + books.hashCode();
        return hash;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof Bundle) || hashCode() != obj.hashCode()) {
            return false;
        }
        Bundle other = (Bundle) obj;
        return name.equals(other.name) && books.equals(other.books);
    }
}
