package academy.pocu.comp2500.lab7;

public final class Author {
    private final String firstName;
    private final String lastName;

    public Author(final String firstName, final String lastName) {
        assert firstName != null;
        assert lastName != null;

        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (obj == null || !(obj instanceof Author) || (this.hashCode() != obj.hashCode())) {
            return false;
        }

        final Author author = (Author) obj;

        return this.firstName.equals(author.firstName) && this.lastName.equals(author.lastName);
    }

    @Override
    public int hashCode() {
        return firstName.hashCode() * 17 + (lastName.hashCode() * 17 >> 16);
    }

    @Override
    public String toString() {
        return String.format("%s %s", this.firstName, this.lastName);
    }

}
