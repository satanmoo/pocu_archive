package academy.pocu.comp2500.lab7;

import java.util.ArrayList;

public final class Bookshelf {
    private final int maxSize;
    private final ArrayList<Book> books = new ArrayList<>();

    public Bookshelf(final int maxSize) {
        assert maxSize > 0;
        this.maxSize = maxSize;
    }

    public boolean add(final Book book) {
        if (books.size() == maxSize) {
            return false;
        }
        books.add(book);
        return true;
    }

    public boolean remove(final Book book) {
        // Removes the first occurrence of the specified element from this list
        return books.remove(book);
    }

    // 레퍼런스가 동일한 경우 같다면 기존 구현 그대로임.
    // 추가구현 불필요함.
}
