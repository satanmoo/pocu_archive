package academy.pocu.comp2500.lab7;

import java.util.ArrayList;

public final class ReadingList {
    private final String name;
    private final ArrayList<Book> books;

    public ReadingList(final String name) {
        assert name != null;

        this.name = name;
        this.books = new ArrayList<>();
    }

    public void add(final Book book) {
        books.add(book);
    }

    public boolean remove(final Book book) {
        return books.remove(book);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < books.size(); i++) {
            sb.append(String.format("%d. %s%s", i + 1, books.get(i).toString(), System.lineSeparator()));
        }

        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (obj == null || !(obj instanceof ReadingList) || (this.hashCode() != obj.hashCode())) {
            return false;
        }

        final ReadingList readingList = (ReadingList) obj;

        // 1. name equal
        if (!this.name.equals(readingList.name)) {
            return false;
        }

        // 2. book equal + order equal
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).equals(readingList.books.get(i)) == false) {
                return false;
            }
        }

        return true;
    }

    @Override
    public int hashCode() {
        int hash = this.name.hashCode();
        hash = hash ^ (hash >> 16);
        // 32가 아니라 16인 이유?
        // int 이기 때문에. 32면 그냥 전부 0으로 채워진 비트가 나오기 때문

        for (int i = 0; i < books.size(); i++) {
            hash = hash + books.get(i).hashCode() + ((i + 1) * 17);
            hash = hash ^ (hash >> 16);
        }

        return hash;
    }
}
