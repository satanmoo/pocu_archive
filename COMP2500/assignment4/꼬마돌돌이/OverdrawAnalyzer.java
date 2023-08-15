package academy.pocu.comp2500.assignment4;

import java.util.ArrayList;
import java.util.LinkedList;

public final class OverdrawAnalyzer extends Canvas {

    private final ArrayList<ArrayList<LinkedList<Character>>> overdrawAnalyzer;

    public OverdrawAnalyzer(final int width, final int height) {
        super(width, height);

        this.overdrawAnalyzer = new ArrayList<>();

        for (int i = 0; i < super.getHeight(); i++) {
            ArrayList<LinkedList<Character>> row = new ArrayList<>(super.getWidth());
            for (int j = 0; j < super.getWidth(); j++) {
                LinkedList<Character> linkedList = new LinkedList<>();
                row.add(linkedList);
            }

            this.overdrawAnalyzer.add(row);
        }
    }

    public LinkedList<Character> getPixelHistory(final int x, final int y) {
        return overdrawAnalyzer.get(y).get(x);
    }

    public int getOverdrawCount() {
        int count = 0;

        for (int i = 0; i < getHeight(); i++) {
            for (int j = 0; j < getWidth(); j++) {
                count += overdrawAnalyzer.get(i).get(j).size();
            }
        }

        return count;
    }

    public int getOverdrawCount(final int x, final int y) {
        return overdrawAnalyzer.get(y).get(x).size();
    }

    @Override
    public void clear() {
        for (int i = 0; i < super.getHeight(); i++) {
            for (int j = 0; j < this.getWidth(); j++) {
                if (super.getPixel(j, i) == CanvasInfo.INIT_CHARACTER.getCharacter()) {
                    continue;
                }

                overdrawAnalyzer.get(i).get(j).addLast(CanvasInfo.INIT_CHARACTER.getCharacter());
                super.drawPixel(j, i, CanvasInfo.INIT_CHARACTER.getCharacter());
            }
        }
    }

    @Override
    public void drawPixel(int x, int y, char character) {
        if (super.getPixel(x, y) == character) {
            return;
        }

        if (character < CanvasInfo.LOW_LIMIT_PRINTABLE_ASCII.getCharacter() || character > CanvasInfo.HIGH_LIMIT_PRINTABLE_ASCII.getCharacter()) {
            return;
        }

        super.drawPixel(x, y, character);
        overdrawAnalyzer.get(y).get(x).addLast(character);
    }

    @Override
    public boolean increasePixel(int x, int y) {
        if (super.getPixel(x, y) == CanvasInfo.HIGH_LIMIT_PRINTABLE_ASCII.getCharacter()) {
            return false;
        }

        super.increasePixel(x, y);
        overdrawAnalyzer.get(y).get(x).addLast(super.getPixel(x, y));
        return true;
    }

    @Override
    public boolean decreasePixel(int x, int y) {
        if (super.getPixel(x, y) == CanvasInfo.LOW_LIMIT_PRINTABLE_ASCII.getCharacter()) {
            return false;
        }

        super.decreasePixel(x, y);
        overdrawAnalyzer.get(y).get(x).addLast(super.getPixel(x, y));
        return true;
    }

    @Override
    public void toUpper(int x, int y) {

        char c = super.getPixel(x, y);
        if (c >= CanvasInfo.LOWER_A.getCharacter()  && c <= CanvasInfo.LOWER_Z.getCharacter() ) {
            super.toUpper(x, y);
            overdrawAnalyzer.get(y).get(x).addLast(super.getPixel(x, y));
        }

    }

    @Override
    public void toLower(int x, int y) {

        char c = super.getPixel(x, y);
        if (c >= CanvasInfo.UPPER_A.getCharacter()  && c <= CanvasInfo.UPPER_Z.getCharacter() ) {
            super.toLower(x, y);
            overdrawAnalyzer.get(y).get(x).addLast(super.getPixel(x, y));
        }

    }

    @Override
    public void fillHorizontalLine(int y, char character) {
        for (int i = 0; i < super.getWidth(); i++) {
            if (super.getPixel(i, y) == character) {
                continue;
            }

            super.drawPixel(i, y, character);
            overdrawAnalyzer.get(y).get(i).addLast(character);
        }
    }

    @Override
    public void fillVerticalLine(int x, char character) {
        for (int i = 0; i < super.getHeight(); i++) {
            if (super.getPixel(x, i) == character) {
                continue;
            }

            super.drawPixel(x, i, character);
            overdrawAnalyzer.get(i).get(x).addLast(character);
        }
    }

}
