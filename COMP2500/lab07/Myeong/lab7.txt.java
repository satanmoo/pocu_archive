//----------------------Author.java----------------------

package academy.pocu.comp2500.lab7;

public class Author {
    private String firstName;
    private String lastName;

    public Author(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return String.format(this.firstName + " " + this.lastName);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || !(obj instanceof Author)) {
            return false;
        }

        Author author = (Author) obj;
        return this.firstName.equals(author.firstName) &&
                this.lastName.equals(author.lastName);
    }

    @Override
    public int hashCode() {
        return this.toString().hashCode();      // test
//        int hash = 7;
//        hash = 31 * hash + this.firstName.hashCode();
//        hash = 31 * hash + this.lastName.hashCode();
//        return hash;
    }
}


//----------------------Book.java----------------------

package academy.pocu.comp2500.lab7;

public class Book {
    private String title;
    private Author author;
    private int releaseYear;
    private Genre genre;

    public Book(String title, Author author, int releaseYear, Genre genre) {
        this.title = title;
        this.author = author;
        this.releaseYear = releaseYear;
        this.genre = genre;
    }

    @Override
    public String toString() {
        return String.format(this.title + " [" + this.author.toString() + "]");
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Book bookObj = (Book) obj;
        if (this.title.equals(bookObj.title) &&
                this.author.equals(bookObj.author) &&
                this.releaseYear == bookObj.releaseYear &&
                this.genre.equals(bookObj.genre)) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return (this.title.hashCode() + this.author.hashCode() + this.releaseYear + this.genre.hashCode());
    }
}


//----------------------Bookshelf.java----------------------

package academy.pocu.comp2500.lab7;

import java.util.ArrayList;
public class Bookshelf {
    private int maxBookNumber;
    private ArrayList<Book> books = new ArrayList<>();

    public Bookshelf(int maxBookNumber) {
        this.maxBookNumber = maxBookNumber;
    }

    public boolean add(Book book) {
        if (this.maxBookNumber <= this.books.size()) {
            return false;
        }
        return this.books.add(book);
    }

    public boolean remove(Book book) {
        for (Book copyBook : this.books) {
            if (copyBook.equals(book)) {
                return this.books.remove(copyBook);
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        return (this == obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}


//----------------------Bundle.java----------------------

package academy.pocu.comp2500.lab7;

import java.util.HashSet;
public class Bundle {
    private String name;        // unique value X (이름 중복 허용)
    private HashSet<Book> books = new HashSet<>();

    public Bundle(String name) {
        this.name = name;
    }

    public boolean add(Book book) {
        if (this.books.size() == 4) {
            return false;
        }
        return this.books.add(book);
    }

    public boolean remove(Book book) {
        for (Book copyBook : this.books) {
            if (copyBook.equals(book)) {
                return this.books.remove(copyBook);
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Bundle bundleObj = (Bundle) obj;
        return (this.name.equals(bundleObj.name) &&
                this.books.containsAll(bundleObj.books));
    }

    @Override
    public int hashCode() {
        int hash = this.name.hashCode();

        hash += this.books.hashCode();
        for (Book book : this.books) {
            hash += book.hashCode();
        }

        return hash;
    }
}


//----------------------Genre.java----------------------

package academy.pocu.comp2500.lab7;

public enum Genre {
    BIOGRAPHY,
    ROMANCE,
    MYSTERY,
    SCIENCE_FICTION,
    FANTASY,
    SUSPENSE
}


//----------------------ReadingList.java----------------------

package academy.pocu.comp2500.lab7;

import java.util.ArrayList;

public class ReadingList {
    private String name;
    private ArrayList<Book> books = new ArrayList<>();

    public ReadingList(String name) {
        this.name = name;
    }

    public void add(Book book) {
        this.books.add(book);
    }

    public boolean remove(Book book) {
        for (Book copyBook : this.books) {
            if (copyBook.equals(book)) {
                return this.books.remove(copyBook);
            }
        }
        return false;
    }

    @Override
    public String toString() {
        String strBooks = "";
        int count = 1;
        for (Book book : this.books) {
            strBooks += String.format(count++ + ". " + book.toString() + System.lineSeparator());
        }
        return strBooks;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ReadingList readingListObj = (ReadingList) obj;

        if (this.name.equals(readingListObj.name) &&
                this.books.size() == readingListObj.books.size()) {
            for (int idx = 0; this.books.size() > idx; ++idx) {
                if (!(this.books.get(idx).equals(readingListObj.books.get(idx)))) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return (this.name.hashCode() + this.toString().hashCode());
    }
}
