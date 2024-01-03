package academy.pocu.comp2500.lab7;

import java.util.ArrayList;

public class ReadingList {
    private final String name;
    private final ArrayList<Book> list = new ArrayList<>();

    public ReadingList(String name) {
        this.name = name;
    }

    public void add(Book book) {
        list.add(book);
    }

    public boolean remove(Book bookToRemove) {
        return list.remove(bookToRemove);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); ++i) {
            sb.append(String.format("%d. %s%s", i + 1, list.get(i).toString(), System.lineSeparator()));
        }
        return sb.toString();
    }

    public int hashCode() {
        int hash = 17;
        hash = hash * 31 + name.hashCode();
        hash = hash * 31 + list.hashCode();
        return hash;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ReadingList) || hashCode() != obj.hashCode()) {
            return false;
        }
        ReadingList other = (ReadingList) obj;
        return name.equals(other.name) && list.equals(other.list);
    }
}
