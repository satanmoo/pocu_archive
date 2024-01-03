package academy.pocu.comp2500.lab3;

import java.util.ArrayList;
import java.util.List;

public class ListItem {

    private String text;
    private List<ListItem> subListItems = new ArrayList<>();
    private char bulletStyle = '*';

    public ListItem(String text) {
        this.text = text;
    }

    public ListItem(String text, char bulletStyle) {
        this.text = text;
        this.bulletStyle = bulletStyle;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public char getBulletStyle() {
        return this.bulletStyle;
    }

    public void setBulletStyle(char bulletStyle) {
        this.bulletStyle = bulletStyle;
    }

    public void addSublistItem(ListItem listItem) {
        this.subListItems.add(listItem);
    }

    public ListItem getSublistItem(int index) {
        return this.subListItems.get(index);
    }

    public void removeSublistItem(int index) {
        this.subListItems.remove(index);
    }

    public String toString() {
        return this.recursiveToString(this, 0);
    }

    private String recursiveToString(ListItem listItem, int depth) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < depth; i++) {
            sb.append("    ");
        }

        sb.append(listItem.bulletStyle).append(' ').append(listItem.text).append(System.lineSeparator());

        for (var item : listItem.subListItems) {
            sb.append(this.recursiveToString(item, depth + 1));
        }

        return sb.toString();
    }
}