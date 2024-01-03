package academy.pocu.comp2500.assignment4;

public class Canvas {
    private final int width;
    private final int height;
    private final char[][] canvas;

    public Canvas(int width, int height) {
        this.width = width;
        this.height = height;

        canvas = new char[height][width];
        for (int y = 0; y < height; ++y) {
            for (int x = 0; x < width; ++x) {
                canvas[y][x] = ' ';
            }
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void drawPixel(int x, int y, char c) {
        assert x >= 0 && x < width;
        assert y >= 0 && y < height;
        assert c >= 32 && c <= 126;
        canvas[y][x] = c;
    }

    public char getPixel(int x, int y) {
        assert x >= 0 && x < width;
        assert y >= 0 && y < height;
        return canvas[y][x];
    }

    public boolean increasePixel(int x, int y) {
        assert x >= 0 && x < width;
        assert y >= 0 && y < height;
        char c = canvas[y][x];
        if (c == 126) {
            return false;
        }
        ++canvas[y][x];
        return true;
    }

    public boolean decreasePixel(int x, int y) {
        assert x >= 0 && x < width;
        assert y >= 0 && y < height;
        char c = canvas[y][x];
        if (c == 32) {
            return false;
        }
        --canvas[y][x];
        return true;
    }

    public void toUpper(int x, int y) {
        assert x >= 0 && x < width;
        assert y >= 0 && y < height;
        canvas[y][x] = Character.toUpperCase(canvas[y][x]);
    }

    public void toLower(int x, int y) {
        assert x >= 0 && x < width;
        assert y >= 0 && y < height;
        canvas[y][x] = Character.toLowerCase(canvas[y][x]);
    }

    public void fillHorizontalLine(int y, char c) {
        assert y >= 0 && y < height;
        assert c >= 32 && c <= 126;
        for (int x = 0; x < width; ++x) {
            canvas[y][x] = c;
        }
    }

    public void fillVerticalLine(int x, char c) {
        assert x >= 0 && x < width;
        assert c >= 32 && c <= 126;
        for (int y = 0; y < height; ++y) {
            canvas[y][x] = c;
        }
    }

    public void clear() {
        for (int y = 0; y < height; ++y) {
            for (int x = 0; x < width; ++x) {
                canvas[y][x] = ' ';
            }
        }
    }

    public String getDrawing() {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("%c%s%c%s", '+', "-".repeat(Math.max(0, width)), '+', System.lineSeparator()));
        for (int y = 0; y < height; ++y) {
            sb.append('|');
            for (int x = 0; x < width; ++x) {
                sb.append(canvas[y][x]);
            }
            sb.append('|');
            sb.append(System.lineSeparator());

        }
        sb.append(String.format("%c%s%c%s", '+', "-".repeat(Math.max(0, width)), '+', System.lineSeparator()));

        return sb.toString();
    }

//    private boolean isCharInValidScope(char c) {
//        return c >= 32 && c <= 126;
//    }
//
//    private char getValidated(char c) {
//        // [32, 126]
//        return (char) ((char) (c - 32) % 95 + 32);
//    }
}
