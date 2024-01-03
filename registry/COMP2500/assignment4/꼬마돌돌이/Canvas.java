package academy.pocu.comp2500.assignment4;

import java.util.ArrayList;

public class Canvas {
    private final int WIDTH;
    private final int HEIGHT;
    private final ArrayList<ArrayList<Character>> canvas;

    public Canvas(final int width, final int height) {
        assert width > 0;
        assert height > 0;

        this.WIDTH = width; // +x는 오른쪽
        this.HEIGHT = height; // +y는 아래쪽

        this.canvas = new ArrayList<>(HEIGHT);

        for (int i = 0; i < HEIGHT; i++) {
            ArrayList<Character> row = new ArrayList<>(WIDTH);

            for (int j = 0; j < WIDTH; j++) {
                row.add(CanvasInfo.INIT_CHARACTER.getCharacter());
            }

            canvas.add(row);
        }
    }

    public int getWidth() {
        return WIDTH;
    }

    public int getHeight() {
        return HEIGHT;
    }

    public void clear() {
        for (int i = 0; i < HEIGHT; i++) {
            ArrayList<Character> row = canvas.get(i);
            for (int j = 0; j < WIDTH; j++) {
                row.set(j, CanvasInfo.INIT_CHARACTER.getCharacter());
            }
        }
    }

    public void drawPixel(final int x, final int y, final char character) {
        if (isValidXY(x, y) == false || isPrintable(character) == false) {
            return;
        }
        canvas.get(y).set(x, character);
    }

    public char getPixel(final int x, final int y) {
        if (isValidXY(x, y) == false) {
            return CanvasInfo.NULL_CHARACTER.getCharacter();
        }

        return canvas.get(y).get(x);
    }

    public boolean increasePixel(final int x, final int y) {
        // increase & decrease 는 boolean 인 이유는 무엇일까 ??
        // --- 허용하지 않는 아스키에 대해서 명령어 실행완료로 판단하지 않겠다는 뜻임,
        // --- 그렇기 때문에 호출자에서 이 오류코드(boolean)을 보고 로직을 처리하라는 뜻이겠다.
        if (isValidXY(x, y) == false) {
            return false;
        }

        if (canvas.get(y).get(x) == CanvasInfo.HIGH_LIMIT_PRINTABLE_ASCII.getCharacter()) {
            return false;
        }

        canvas.get(y).set(x, (char) (canvas.get(y).get(x) + 1));
        return true;
    }

    public boolean decreasePixel(final int x, final int y) {
        if (isValidXY(x, y) == false) {
            return false;
        }

        if (canvas.get(y).get(x) == CanvasInfo.LOW_LIMIT_PRINTABLE_ASCII.getCharacter()) {
            return false;
        }

        canvas.get(y).set(x, (char) (canvas.get(y).get(x) - 1));
        return true;
    }

    public void toUpper(final int x, final int y) {
        if (isValidXY(x, y) == false) {
            return;
        }

        char currentPixel = canvas.get(y).get(x);
        if (this.isAlpha(currentPixel)) {
            canvas.get(y).set(x, (char) (currentPixel & ~0x20));
        }
    }

    public void toLower(final int x, final int y) {
        if (isValidXY(x, y) == false) {
            return;
        }

        char currentPixel = canvas.get(y).get(x);
        if (this.isAlpha(currentPixel)) {
            canvas.get(y).set(x, (char) (currentPixel | 0x20));
        }
    }

    public void fillHorizontalLine(final int y, final char character) {
        if (isValidY(y) == false || isPrintable(character) == false) {
            return;
        }

        ArrayList<Character> row = this.canvas.get(y);
        for (int i = 0; i < WIDTH; i++) {
            row.set(i, character);
        }
    }

    public void fillVerticalLine(final int x, final char character) {
        if (isValidX(x) == false || isPrintable(character) == false) {
            return;
        }

        for (int i = 0; i < HEIGHT; i++) {
            this.canvas.get(i).set(x, character);
        }
    }

    public String getDrawing() {
        StringBuilder sb = new StringBuilder();

        // 함수 안에서 한번에 보기 위해서 성능보단 가독성을 선택함.
        // --- 성능문제 발견되면 이제 바꿔줘야 겠지만
        sb.append("+");
        for (int i = 0; i < WIDTH; i++) {
            sb.append("-");
        }
        sb.append("+");

        sb.append(System.lineSeparator());

        for (int i = 0; i < HEIGHT; i++) {
            sb.append('|');
            for (int j = 0; j < WIDTH; j++) {
                sb.append(canvas.get(i).get(j));
            }
            sb.append('|');
            sb.append(System.lineSeparator());
        }

        sb.append("+");
        for (int i = 0; i < WIDTH; i++) {
            sb.append("-");
        }
        sb.append("+");

        sb.append(System.lineSeparator());

        return sb.toString();
    }


    private boolean isValidXY(final int x, final int y) {
        // helper function
        return isValidX(x) && isValidY(y);
    }

    private boolean isValidX(final int x) {
        return (x >= 0 && x < WIDTH);
    }

    private boolean isValidY(final int y) {
        return (y >= 0 || y < HEIGHT);
    }

    private boolean isAlpha(final char character) {
        return (character >= CanvasInfo.UPPER_A.getCharacter() && character <= CanvasInfo.UPPER_Z.getCharacter())
                || (character >= CanvasInfo.LOWER_A.getCharacter() && character <= CanvasInfo.LOWER_Z.getCharacter());
    }

    private boolean isPrintable(final char character) {
        return character >= CanvasInfo.LOW_LIMIT_PRINTABLE_ASCII.getCharacter() && character <= CanvasInfo.HIGH_LIMIT_PRINTABLE_ASCII.getCharacter();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || (obj instanceof Canvas) == false || (this.hashCode() != obj.hashCode())) {
            return false;
        }

        final Canvas otherCanvas = (Canvas) obj;

        if (this.getWidth() != otherCanvas.getWidth()) {
            return false;
        }

        if (this.getHeight() != otherCanvas.getHeight()) {
            return false;
        }

        for (int i = 0; i < getHeight(); i++) {
            for (int j = 0; j < getWidth(); j++) {
                if (otherCanvas.canvas.get(i).get(j).equals(this.canvas.get(i).get(j)) == false) {
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    public int hashCode() {
        int hashCode = 0;
        for (int i = 0; i < getHeight(); i++) {
            for (int j = 0; j < getWidth(); j++) {
                hashCode += (int) canvas.get(j).get(i);
                hashCode = hashCode << 16;
            }
        }
        return hashCode;
    }
}
