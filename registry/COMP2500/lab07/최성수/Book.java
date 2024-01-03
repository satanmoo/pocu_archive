package academy.pocu.comp2500.lab7;

public class Book {
    private final String title;
    private final Author author;
    private final int publishedAt;
    private final Genre genre;

    public Book(String title, Author author, int publishedAt, Genre genre) {
        this.title = title;
        this.author = author;
        this.publishedAt = publishedAt;
        this.genre = genre;
    }

    public String toString() {
        return String.format("%s [%s]", title, author.toString());
    }

    public int hashCode() {
        int hash = 17;
        hash = hash * 31 + title.hashCode();
        hash = hash * 31 + author.hashCode();
        hash = hash * 31 + publishedAt;
        hash = hash * 31 + genre.hashCode();
        return hash;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof Book) || this.hashCode() != obj.hashCode()) {
            return false;
        }
        Book other = (Book) obj;
        return title.equals(other.title) && author.equals(other.author) && publishedAt == other.publishedAt && genre == other.genre;
    }
}
