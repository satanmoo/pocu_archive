package academy.pocu.comp2500.lab3;

import java.util.ArrayList;

public class ListItem {
    private String text;
    private final ArrayList<ListItem> sublistItems = new ArrayList<>();
    private char bulletStyle = '*';

    public ListItem(String text) {
        this.text = text;
    }

    public ListItem(String text, char bulletStyle) {
        this.text = text;
        this.bulletStyle = bulletStyle;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public char getBulletStyle() {
        return bulletStyle;
    }

    public void setBulletStyle(char bulletStyle) {
        this.bulletStyle = bulletStyle;
    }

    public ListItem getSublistItem(int index) {
        return sublistItems.get(index);
    }

    public void addSublistItem(ListItem item) {
        sublistItems.add(item);
    }

    public void removeSublistItem(int index) {
        sublistItems.remove(index);
    }

    private void appendIndentedSublistItemsRecursive(int indentLevel, StringBuilder sb, ListItem listItem) {

        sb.append(listItem.bulletStyle);
        sb.append(' ');
        sb.append(listItem.text);
        sb.append(System.lineSeparator());

        for (ListItem item : listItem.sublistItems) {
            sb.append(String.valueOf(' ').repeat(indentLevel * 4));
            appendIndentedSublistItemsRecursive(indentLevel + 1, sb, item);
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        appendIndentedSublistItemsRecursive(1, sb, this);
        return sb.toString();
    }
}
