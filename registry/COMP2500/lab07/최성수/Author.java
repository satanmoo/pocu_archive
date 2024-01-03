package academy.pocu.comp2500.lab7;

public class Author {
    private final String firstName;
    private final String lastName;

    public Author(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String toString() {
        return String.format("%s %s", firstName, lastName);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (obj == null || !(obj instanceof Author) || hashCode() != obj.hashCode()) {
            return false;
        }

        Author other = (Author) obj;
        return firstName.equals(other.firstName) && lastName.equals(other.lastName);
    }

    public int hashCode() {
        int hash = 17;
        hash = hash * 31 + firstName.hashCode();
        hash = hash * 31 + lastName.hashCode();
        return hash;
    }
}
