package academy.pocu.comp2500.lab7;

import java.util.HashSet;

public final class Bundle {
    private static final int BUNDLE_SIZE = 4;
    private final String bundleName;
    private final HashSet<Book> books;

    public Bundle(final String bundleName) {
        assert bundleName != null;

        this.bundleName = bundleName;
        this.books = new HashSet<>();
    }

    public boolean add(final Book book) {
        if (books.size() == BUNDLE_SIZE) {
            return false;
        }
        return books.add(book);
    }

    public boolean remove(final Book book) {
        return books.remove(book);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (obj == null || !(obj instanceof Bundle) || (this.hashCode() != obj.hashCode())) {
            return false;
        }

        final Bundle bundle = (Bundle) obj;

        // equal condition
        // 1. bundleName must be same
        if (this.bundleName.equals(bundle.bundleName) == false) {
            return false;
        }

        // 2. each Book must be same
        for (Book book : this.books) {
            boolean isExist = false;
            for (Book otherBundleBook : bundle.books) {
                if (book.equals(otherBundleBook)) {
                    isExist = true;
                }
            }
            if (isExist == false) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = this.bundleName.hashCode();
        for (Book book : this.books) {
            hash += book.hashCode();
        }
        return hash;
    }
}
