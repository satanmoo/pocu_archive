package academy.pocu.comp2500.assignment4;

import java.util.ArrayList;
import java.util.LinkedList;

public class OverdrawAnalyzer extends Canvas {
    private final ArrayList<ArrayList<LinkedList<Character>>> history;

    public OverdrawAnalyzer(int width, int height) {
        super(width, height);
        history = new ArrayList<>();
        for (int y = 0; y < height; ++y) {
            ArrayList<LinkedList<Character>> arr = new ArrayList<>();
            for (int x = 0; x < width; ++x) {
                arr.add(new LinkedList<>());
            }
            history.add(arr);
        }
    }

    @Override
    public void drawPixel(int x, int y, char c) {
        assert x >= 0 && x < getWidth();
        assert y >= 0 && y < getHeight();
        assert c >= 32 && c <= 126;
        if (getPixel(x, y) == c) {
            return;
        }
        super.drawPixel(x, y, c);
        history.get(y).get(x).add(c);
    }

    @Override
    public boolean increasePixel(int x, int y) {
        boolean isExecuted = super.increasePixel(x, y);
        if (isExecuted) {
            history.get(y).get(x).add(super.getPixel(x, y));
        }
        return isExecuted;
    }

    @Override
    public boolean decreasePixel(int x, int y) {
        boolean isExecuted = super.decreasePixel(x, y);
        if (isExecuted) {
            history.get(y).get(x).add(super.getPixel(x, y));
        }
        return isExecuted;
    }

    @Override
    public void toUpper(int x, int y) {
        char pre = getPixel(x, y);
        super.toUpper(x, y);
        if (pre != getPixel(x, y)) {
            history.get(y).get(x).add(getPixel(x, y));
        }
    }

    @Override
    public void toLower(int x, int y) {
        char pre = getPixel(x, y);
        super.toLower(x, y);
        if (pre != getPixel(x, y)) {
            history.get(y).get(x).add(getPixel(x, y));
        }
    }

    @Override
    public void fillHorizontalLine(int y, char c) {
        assert y >= 0 && y < getHeight();
        assert c >= 32 && c <= 126;
        for (int x = 0; x < getWidth(); ++x) {
            if (getPixel(x, y) != c) {
                history.get(y).get(x).add(c);
            }
        }
        super.fillHorizontalLine(y, c);
    }

    @Override
    public void fillVerticalLine(int x, char c) {
        assert x >= 0 && x < getWidth();
        assert c >= 32 && c <= 126;
        for (int y = 0; y < getHeight(); ++y) {
            if (getPixel(x, y) != c) {
                history.get(y).get(x).add(c);
            }
        }
        super.fillVerticalLine(x, c);
    }

    public LinkedList<Character> getPixelHistory(int x, int y) {
        return history.get(y).get(x);
    }

    public int getOverdrawCount(int x, int y) {
        return history.get(y).get(x).size();
    }

    public int getOverdrawCount() {
        int total = 0;
        for (int y = 0; y < getHeight(); ++y) {
            for (int x = 0; x < getWidth(); ++x) {
                total += getOverdrawCount(x, y);
            }
        }
        return total;
    }

    @Override
    public void clear() {
        for (int y = 0; y < getHeight(); ++y) {
            for (int x = 0; x < getWidth(); ++x) {
                if (getPixel(x, y) != ' ') {
                    this.drawPixel(x, y, ' ');
                }
            }
        }
    }
}
