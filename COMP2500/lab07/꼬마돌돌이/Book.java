package academy.pocu.comp2500.lab7;

public final class Book {
    private final String title;
    private final Author author;
    private final int publishedDate;
    private final Genre genre;

    public Book(final String title, final Author author, final int publishedDate, final Genre genre) {
        assert title != null;
        assert author != null;
        assert publishedDate > 0;
        assert genre != null;

        this.title = title;
        this.author = author;
        this.publishedDate = publishedDate;
        this.genre = genre;
    }

    @Override
    public String toString() {
        return String.format("%s [%s]", this.title, this.author.toString());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (obj == null || !(obj instanceof Book) || (this.hashCode() != obj.hashCode())) {
            return false;
        }

        Book book = (Book) obj;

        return this.title.equals(book.title) && this.author.equals(book.author) && (this.publishedDate == book.publishedDate) && this
                .genre.equals(book.genre);
    }

    @Override
    public int hashCode() {
        // 따로 추가적인 연산을 지정안한 이유?
        // 전부 다 다른 자료형이기 때문에!!
        // Author의 경우 String 자료형으로 firstName, lastName은 같은 해시가 나올 가능성이 높으나 Book은 아니다.
        return this.title.hashCode() + this.author.hashCode() + this.publishedDate + this.genre.hashCode();
    }
}
