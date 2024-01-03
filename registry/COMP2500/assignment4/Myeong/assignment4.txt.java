//----------------------Canvas.java----------------------

package academy.pocu.comp2500.assignment4;

public class Canvas {
    private final int width;
    private final int height;
    private char[][] pixel;     // [row][col] = [height][width] = [y][x]

    public Canvas(final int width, final int height) {
        assert (width >= 0 && height >= 0) : "Impossiable Condition";

        this.width = width;
        this.height = height;

        this.pixel = new char[this.height][this.width];
        for (int y = 0; y < this.height; ++y) {
            for (int x = 0; x < this.width; ++x) {
                this.pixel[y][x] = ' ';
            }
        }
    }
    // !!!혹시라도 x, y 값이 각각 width, height를 넘을 경우도 고려해야할 경우, 추가적인 작업 필요!!!

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public void drawPixel(final int x, final int y, final char pixel) {
        assert (x >= 0 && y >= 0 && x < this.width && y < this.height) : "Overflow situation";

        if (pixel < 32 && pixel > 126) {
            return;
        }

        this.pixel[y][x] = pixel;
    }

    public char getPixel(final int x, final int y) {
        assert (x >= 0 && y >= 0 && x < this.width && y < this.height) : "Overflow situation";

        return this.pixel[y][x];
    }

    public boolean increasePixel(final int x, final int y) {
        assert (x >= 0 && y >= 0 && x < this.width && y < this.height) : "Overflow situation";

        char pixel = (char) (getPixel(x, y) + 1);

        if (pixel < 32 || pixel > 126) {
            return false;
        }

        drawPixel(x, y, pixel);
        return true;
    }

    public boolean decreasePixel(final int x, final int y) {
        assert (x >= 0 && y >= 0 && x < this.width && y < this.height) : "Overflow situation";

        char pixel = (char) (getPixel(x, y) - 1);

        if (pixel < 32 || pixel > 126) {
            return false;
        }

        drawPixel(x, y, pixel);
        return true;
    }

    public void toUpper(final int x, final int y) {
        assert (x >= 0 && y >= 0 && x < this.width && y < this.height) : "Overflow situation";

        char pixel = getPixel(x, y);

        if (pixel > 96 && pixel < 123) {
            drawPixel(x, y, (char) (pixel - 32));
        } else {
            drawPixel(x, y, pixel);
        }
    }

    public void toLower(final int x, final int y) {
        assert (x >= 0 && y >= 0 && x < this.width && y < this.height) : "Overflow situation";

        char pixel = getPixel(x, y);

        if (pixel > 64 && pixel < 91) {
            drawPixel(x, y, (char) (pixel + 32));
        } else {
            drawPixel(x, y, pixel);
        }
    }

    public void fillHorizontalLine(final int y, final char pixel) {
        assert (y >= 0 && y < this.height) : "Overflow situation";

        if (pixel < 32 && pixel > 126) {
            return;
        }

        for (int x = 0; x < this.width; ++x) {
            drawPixel(x, y, pixel);
        }
    }

    public void fillVerticalLine(final int x, final char pixel) {
        assert (x >= 0 && x < this.width) : "Overflow situation";

        if (pixel < 32 && pixel > 126) {
            return;
        }

        for (int y = 0; y < this.height; ++y) {
            drawPixel(x, y, pixel);
        }
    }

    public void clear() {
        // 캔버스의 모든 픽셀을 지웁니다. => (내 해석) : ' ' 초기화하는 것이라 생각함 [[ 아니라면 변경 필요 ]]
        for (int y = 0; y < this.height; ++y) {
            for (int x = 0; x < this.width; ++x) {
                drawPixel(x, y, ' ');
            }
        }
    }

    public String getDrawing() {
        StringBuilder drawingCanvas = new StringBuilder();

        drawingCanvas.append(borderLine());
        for (int y = 0; y < this.height; ++y) {
            drawingCanvas.append('|');
            for (int x = 0; x < this.width; ++x) {
                drawingCanvas.append(getPixel(x, y));
            }
            drawingCanvas.append('|')
                    .append(System.lineSeparator());
        }
        drawingCanvas.append(borderLine());
        return drawingCanvas.toString();
    }

    private String borderLine() {
        StringBuilder borderLine = new StringBuilder();
        borderLine.append('+');
        for (int x = 0; x < this.width; ++x) {
            borderLine.append('-');
        }
        borderLine.append('+')
                .append(System.lineSeparator());
        return borderLine.toString();
    }
}


//----------------------ClearCommand.java----------------------

package academy.pocu.comp2500.assignment4;

public class ClearCommand implements ICommand {
    private final static char SPACE_CHARACTER = ' ';
    private Canvas newCanvas;
    private Canvas oldCanvas;
    private boolean twiceCheck = false;

    public ClearCommand() {
    }

    @Override
    public boolean execute(final Canvas canvas) {
        if (this.twiceCheck) {
            return false;
        }

        this.newCanvas = canvas;
        this.oldCanvas = new Canvas(this.newCanvas.getWidth(), this.newCanvas.getHeight());

        for (int y = 0; y < this.newCanvas.getHeight(); ++y) {
            for (int x = 0; x < this.newCanvas.getWidth(); ++x) {
                this.oldCanvas.drawPixel(x, y, this.newCanvas.getPixel(x, y));
            }
        }

        this.newCanvas.clear();
        this.twiceCheck = true;

        return true;
    }

    @Override
    public boolean undo() {
        if (this.newCanvas == null || this.oldCanvas == null) {
            return false;
        }

        for (int y = 0; y < this.oldCanvas.getHeight(); ++y) {
            for (int x = 0; x < this.oldCanvas.getWidth(); ++x) {
                if (this.newCanvas.getPixel(x, y) != SPACE_CHARACTER) {
                    return false;
                }
            }
        }

        for (int y = 0; y < this.oldCanvas.getHeight(); ++y) {
            for (int x = 0; x < this.oldCanvas.getWidth(); ++x) {
                this.newCanvas.drawPixel(x, y, this.oldCanvas.getPixel(x, y));
            }
        }

        return true;
    }

    @Override
    public boolean redo() {
        if (this.newCanvas == null || this.oldCanvas == null) {
            return false;
        }

        for (int y = 0; y < this.newCanvas.getHeight(); ++y) {
            for (int x = 0; x < this.newCanvas.getWidth(); ++x) {
                if (this.newCanvas.getPixel(x, y) != this.oldCanvas.getPixel(x, y)) {
                    return false;
                }
            }
        }

        this.newCanvas.clear();
        return true;
    }
}


//----------------------CommandHistoryManager.java----------------------

package academy.pocu.comp2500.assignment4;

import java.util.ArrayList;

public final class CommandHistoryManager {
    private Canvas canvas;
    private ArrayList<ICommand> commands = new ArrayList<>();
    private int pointer = 0;

    public CommandHistoryManager(final Canvas canvas) {
        assert (canvas != null) : "Fail to call constructor";

        this.canvas = canvas;
    }

    public boolean execute(final ICommand command) {
        if (command.execute(this.canvas)) {
            // 이전 undo 되었던 command 들을 모두 제거 (포토샵 생각해보기)
            while (this.pointer != this.commands.size()) {
                int removeIndex = this.commands.size() - 1;
                this.commands.remove(removeIndex);
            }

            this.commands.add(command);
            ++this.pointer;
            return true;
        }
        return false;
    }

    public boolean canUndo() {
        return (this.commands.isEmpty() || this.pointer < 1 ? false : true);
    }

    public boolean canRedo() {
        return (this.commands.isEmpty() || this.pointer >= this.commands.size() ? false : true);
    }

    public boolean undo() {
        if (canUndo() == false) {
            return false;
        }

        if (this.commands.get(this.pointer - 1).undo()) {
            --this.pointer;
            return true;
        }
        return false;
    }

    public boolean redo() {
        if (canRedo() == false) {
            return false;
        }

        if (this.commands.get(this.pointer).redo()) {
            ++this.pointer;
            return true;
        }
        return false;
    }
}


//----------------------DecreasePixelCommand.java----------------------

package academy.pocu.comp2500.assignment4;

public final class DecreasePixelCommand implements ICommand {
    private final int x;
    private final int y;
    private char newCharacter;
    private char oldCharacter;
    private Canvas canvas;
    private boolean twiceCheck = false;

    public DecreasePixelCommand(final int x, final int y) {
        assert (x >= 0 && y >= 0) : "Impossiable Condition";

        this.x = x;
        this.y = y;
    }

    @Override
    public boolean execute(final Canvas canvas) {
        if (this.x < 0 || this.y < 0
                || this.x >= canvas.getWidth()
                || this.y >= canvas.getHeight()
                || this.twiceCheck == true) {
            return false;
        }

        this.oldCharacter = canvas.getPixel(this.x, this.y);

        if (canvas.decreasePixel(this.x, this.y)) {
            this.canvas = canvas;
            this.newCharacter = canvas.getPixel(this.x, this.y);
            this.twiceCheck = true;
            return true;
        }

        this.oldCharacter = 0;
        return false;
    }

    @Override
    public boolean undo() {
        if (this.canvas == null
                || this.newCharacter != canvas.getPixel(this.x, this.y)) {
            return false;
        }

        this.canvas.drawPixel(this.x, this.y, this.oldCharacter);
        return true;
    }

    @Override
    public boolean redo() {
        if (this.canvas == null
                || this.oldCharacter != canvas.getPixel(this.x, this.y)) {
            return false;
        }

        this.canvas.drawPixel(this.x, this.y, this.newCharacter);
        return true;
    }
}


//----------------------DrawPixelCommand.java----------------------

package academy.pocu.comp2500.assignment4;

public final class DrawPixelCommand implements ICommand {
    private final int x;
    private final int y;
    private final char newCharacter;
    private char oldCharacter;
    private Canvas canvas;
    private boolean twiceCheck = false;
    private int doubleUndoCheck = -1;
    private int doubleRedoCheck = -1;

    public DrawPixelCommand(final int x, final int y, final char character) {
        assert (x >= 0 && y >= 0) : "Impossiable Condition";
        assert (character > 32 && character < 126) : "Over range character";

        this.x = x;
        this.y = y;
        this.newCharacter = character;
    }

    @Override
    public boolean execute(final Canvas canvas) {
        if (this.x < 0 || this.y < 0
                || this.x >= canvas.getWidth()
                || this.y >= canvas.getHeight()
                || this.twiceCheck == true) {
            return false;
        }

        this.canvas = canvas;
        this.oldCharacter = canvas.getPixel(this.x, this.y);

        canvas.drawPixel(this.x, this.y, this.newCharacter);
        this.twiceCheck = true;

        if (canvas.getPixel(this.x, this.y) == this.oldCharacter) {
            this.doubleUndoCheck = 0;
            this.doubleRedoCheck = 0;
        }
        return true;
    }

    @Override
    public boolean undo() {
        if (this.canvas == null
                || this.newCharacter != canvas.getPixel(this.x, this.y)
                || (this.doubleUndoCheck != -1 && this.doubleUndoCheck == 1)) {
            return false;
        }

        this.doubleUndoCheck = 1;
        this.doubleRedoCheck = 0;
        this.canvas.drawPixel(this.x, this.y, this.oldCharacter);
        return true;
    }

    @Override
    public boolean redo() {
        if (this.canvas == null
                || this.oldCharacter != canvas.getPixel(this.x, this.y)
                || (this.doubleRedoCheck != -1 && this.doubleRedoCheck == 1)) {
            return false;
        }

        this.doubleUndoCheck = 0;
        this.doubleRedoCheck = 1;
        this.canvas.drawPixel(this.x, this.y, this.newCharacter);
        return true;
    }
}


//----------------------FillHorizontalLineCommand.java----------------------

package academy.pocu.comp2500.assignment4;

import java.util.ArrayList;

public class FillHorizontalLineCommand implements ICommand {
    private final int y;
    private char newCharacter;
    private ArrayList<Character> oldCharacters = new ArrayList<>();
    private Canvas canvas;
    private boolean twiceCheck = false;

    public FillHorizontalLineCommand(final int y, final char character) {
        assert (y >= 0) : "Impossiable Condition";
        assert (character > 32 && character < 126) : "Over range character";

        this.y = y;
        this.newCharacter = character;
    }

    @Override
    public boolean execute(final Canvas canvas) {
        if (this.y < 0
                || this.y >= canvas.getHeight()
                || this.twiceCheck == true) {
            return false;
        }

        this.canvas = canvas;

        for (int x = 0; x < this.canvas.getWidth(); ++x) {
            this.oldCharacters.add(canvas.getPixel(x, this.y));
        }

        canvas.fillHorizontalLine(this.y, this.newCharacter);
        this.twiceCheck = true;

        return true;
    }

    @Override
    public boolean undo() {
        if (this.canvas == null) {
            return false;
        }

        for (int x = 0; x < this.canvas.getWidth(); ++x) {
            if (this.canvas.getPixel(x, this.y) != this.newCharacter) {
                return false;
            }
        }

        for (int x = 0; x < this.canvas.getWidth(); ++x) {
            this.canvas.drawPixel(x, this.y, this.oldCharacters.get(x));
        }

        return true;
    }

    @Override
    public boolean redo() {
        if (this.canvas == null) {
            return false;
        }

        for (int x = 0; x < this.canvas.getWidth(); ++x) {
            if (this.canvas.getPixel(x, this.y) != this.oldCharacters.get(x)) {
                return false;
            }
        }

        this.canvas.fillHorizontalLine(this.y, this.newCharacter);

        return true;
    }
}


//----------------------FillVerticalLineCommand.java----------------------

package academy.pocu.comp2500.assignment4;

import java.util.ArrayList;

public final class FillVerticalLineCommand implements ICommand {
    private final int x;
    private final char newCharacter;
    private ArrayList<Character> oldCharacters = new ArrayList<>();
    private Canvas canvas;
    private boolean twiceCheck = false;

    public FillVerticalLineCommand(final int x, final char character) {
        assert (x >= 0) : "Impossiable Condition";
        assert (character > 32 && character < 126) : "Over range character";

        this.x = x;
        this.newCharacter = character;
    }

    @Override
    public boolean execute(final Canvas canvas) {
        if (this.x < 0
                || this.x >= canvas.getWidth()
                || this.twiceCheck == true) {
            return false;
        }

        this.canvas = canvas;

        for (int y = 0; y < this.canvas.getHeight(); ++y) {
            this.oldCharacters.add(canvas.getPixel(this.x, y));
        }

        canvas.fillVerticalLine(this.x, this.newCharacter);
        this.twiceCheck = true;

        return true;
    }

    @Override
    public boolean undo() {
        if (this.canvas == null) {
            return false;
        }

        for (int y = 0; y < this.canvas.getHeight(); ++y) {
            if (this.canvas.getPixel(this.x, y) != this.newCharacter) {
                return false;
            }
        }

        for (int y = 0; y < this.canvas.getHeight(); ++y) {
            this.canvas.drawPixel(this.x, y, this.oldCharacters.get(y));
        }

        return true;
    }

    @Override
    public boolean redo() {
        if (this.canvas == null) {
            return false;
        }

        for (int y = 0; y < this.canvas.getHeight(); ++y) {
            if (this.canvas.getPixel(this.x, y) != this.oldCharacters.get(y)) {
                return false;
            }
        }

        this.canvas.fillVerticalLine(this.x, this.newCharacter);

        return true;
    }
}


//----------------------ICommand.java----------------------

package academy.pocu.comp2500.assignment4;

public interface ICommand {
    boolean execute(final Canvas canvas);

    boolean undo();

    boolean redo();
}


//----------------------IncreasePixelCommand.java----------------------

package academy.pocu.comp2500.assignment4;

public final class IncreasePixelCommand implements ICommand {
    private final int x;
    private final int y;
    private char newCharacter;
    private char oldCharacter;
    private Canvas canvas;
    private boolean twiceCheck = false;

    public IncreasePixelCommand(final int x, final int y) {
        assert (x >= 0 && y >= 0) : "Impossiable Condition";

        this.x = x;
        this.y = y;
    }

    @Override
    public boolean execute(final Canvas canvas) {
        if (this.x < 0 || this.y < 0
                || this.x >= canvas.getWidth()
                || this.y >= canvas.getHeight()
                || this.twiceCheck == true) {
            return false;
        }

        this.oldCharacter = canvas.getPixel(this.x, this.y);

        if (canvas.increasePixel(this.x, this.y)) {
            this.canvas = canvas;
            this.newCharacter = canvas.getPixel(this.x, this.y);
            this.twiceCheck = true;
            return true;
        }

        this.oldCharacter = 0;
        return false;
    }

    @Override
    public boolean undo() {
        if (this.canvas == null
                || this.newCharacter != canvas.getPixel(this.x, this.y)) {
            return false;
        }

        this.canvas.drawPixel(this.x, this.y, this.oldCharacter);
        return true;
    }

    @Override
    public boolean redo() {
        if (this.canvas == null
                || this.oldCharacter != canvas.getPixel(this.x, this.y)) {
            return false;
        }

        this.canvas.drawPixel(this.x, this.y, this.newCharacter);
        return true;
    }
}


//----------------------OverdrawAnalyzer.java----------------------

package academy.pocu.comp2500.assignment4;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;

public class OverdrawAnalyzer extends Canvas {
    private HashMap<Vector, LinkedList<Character>> pixelsHistory = new HashMap<>();

    public OverdrawAnalyzer(int width, int height) {
        super(width, height);

        for (int y = 0; y < getHeight(); ++y) {
            for (int x = 0; x < getWidth(); ++x) {
                this.pixelsHistory.put(new Vector(x, y), new LinkedList<>());
            }
        }
    }

    private Vector findVectorOrNull(final int x, final int y) {
        for (Vector vector : this.pixelsHistory.keySet()) {
            if (vector.getX() == x && vector.getY() == y) {
                return vector;
            }
        }
        return null;
    }

    private void addPixelHistory(final int x, final int y, final char pixel) {
        Vector copyVector = findVectorOrNull(x, y);

        if (copyVector == null) {
            return;
        }

        for (Map.Entry<Vector, LinkedList<Character>> entry : this.pixelsHistory.entrySet()) {
            if (entry.getKey().equals(copyVector)) {
                entry.getValue().add(pixel);
                break;
            }
        }
    }

    @Override
    public void drawPixel(final int x, final int y, final char pixel) {
        if (super.getPixel(x, y) != pixel) {
            super.drawPixel(x, y, pixel);

            this.addPixelHistory(x, y, pixel);
        }
    }

    @Override
    public void toUpper(int x, int y) {
        char pixel = getPixel(x, y);

        if (pixel > 96 && pixel < 123) {
            this.drawPixel(x, y, (char) (pixel - 32));
        }
    }

    @Override
    public void toLower(int x, int y) {
        char pixel = getPixel(x, y);

        if (pixel > 64 && pixel < 91) {
            this.drawPixel(x, y, (char) (pixel + 32));
        }
    }

    @Override
    public void fillHorizontalLine(int y, char pixel) {
        assert (y >= 0 && y < getHeight()) : "Overflow situation";

        if (pixel < 32 && pixel > 126) {
            return;
        }

        for (int x = 0; x < getWidth(); ++x) {
            this.drawPixel(x, y, pixel);
        }
    }

    @Override
    public void fillVerticalLine(int x, char pixel) {
        assert (x >= 0 && x < getWidth()) : "Overflow situation";

        if (pixel < 32 && pixel > 126) {
            return;
        }

        for (int y = 0; y < getHeight(); ++y) {
            this.drawPixel(x, y, pixel);
        }
    }

    @Override
    public void clear() {
        boolean isCheck = false;
        int checkAllEmpty = 0;

        for (int y = 0; y < getHeight(); ++y) {
            for (int x = 0; x < getWidth(); ++x) {
                if (getPixel(x, y) == ' ') {
                    ++checkAllEmpty;
                } else {
                    isCheck = true;
                    break;
                }
            }
            if (isCheck) {
                break;
            }
        }

        if (checkAllEmpty == getHeight() * getWidth()) {
            return;
        }

        super.clear();
    }

    public LinkedList<Character> getPixelHistory(final int x, final int y) {
        Vector copyVector = findVectorOrNull(x, y);

        LinkedList<Character> copyPixelHistory = null;
        for (Map.Entry<Vector, LinkedList<Character>> entry : this.pixelsHistory.entrySet()) {
            if (entry.getKey().equals(copyVector)) {
                copyPixelHistory = entry.getValue();
            }
        }
        return copyPixelHistory;
    }

    public int getOverdrawCount(final int x, final int y) {
        LinkedList<Character> copyPixelHistory = getPixelHistory(x, y);

        return copyPixelHistory.size();
    }

    public int getOverdrawCount() {
        int overdrawCount = 0;
        LinkedList<Character> copyPixelHistory;
        for (Map.Entry<Vector, LinkedList<Character>> entry : this.pixelsHistory.entrySet()) {
            copyPixelHistory = entry.getValue();
            overdrawCount += copyPixelHistory.size();
        }

        return overdrawCount;
    }
}


//----------------------ToLowerPixelCommand.java----------------------

package academy.pocu.comp2500.assignment4;

public final class ToLowerPixelCommand implements ICommand {
    private final int x;
    private final int y;
    private char newCharacter;
    private char oldCharacter;
    private Canvas canvas;
    private boolean twiceCheck = false;

    public ToLowerPixelCommand(final int x, final int y) {
        assert (x >= 0 && y >= 0) : "Impossiable Condition";

        this.x = x;
        this.y = y;
    }

    @Override
    public boolean execute(final Canvas canvas) {
        if (this.x < 0 || this.y < 0
                || this.x >= canvas.getWidth()
                || this.y >= canvas.getHeight()
                || this.twiceCheck == true) {
            return false;
        }
        this.canvas = canvas;
        this.oldCharacter = canvas.getPixel(this.x, this.y);

        canvas.toLower(this.x, this.y);
        this.newCharacter = canvas.getPixel(this.x, this.y);
        this.twiceCheck = true;

        return true;
    }

    @Override
    public boolean undo() {
        if (this.canvas == null
                || this.newCharacter != canvas.getPixel(this.x, this.y)) {
            return false;
        }

        this.canvas.drawPixel(this.x, this.y, this.oldCharacter);
        return true;
    }

    @Override
    public boolean redo() {
        if (this.canvas == null
                || this.oldCharacter != canvas.getPixel(this.x, this.y)) {
            return false;
        }

        this.canvas.drawPixel(this.x, this.y, this.newCharacter);
        return true;
    }
}


//----------------------ToUpperPixelCommand.java----------------------

package academy.pocu.comp2500.assignment4;

public final class ToUpperPixelCommand implements ICommand {
    private final int x;
    private final int y;
    private char newCharacter;
    private char oldCharacter;
    private Canvas canvas;
    private boolean twiceCheck = false;

    public ToUpperPixelCommand(final int x, final int y) {
        assert (x >= 0 && y >= 0) : "Impossiable Condition";

        this.x = x;
        this.y = y;
    }

    @Override
    public boolean execute(final Canvas canvas) {
        if (this.x < 0 || this.y < 0
                || this.x >= canvas.getWidth()
                || this.y >= canvas.getHeight()
                || this.twiceCheck == true) {
            return false;
        }
        this.canvas = canvas;
        this.oldCharacter = canvas.getPixel(this.x, this.y);

        canvas.toUpper(this.x, this.y);
        this.newCharacter = canvas.getPixel(this.x, this.y);
        this.twiceCheck = true;

        return true;
    }

    @Override
    public boolean undo() {
        if (this.canvas == null
                || this.newCharacter != canvas.getPixel(this.x, this.y)) {
            return false;
        }

        this.canvas.drawPixel(this.x, this.y, this.oldCharacter);
        return true;
    }

    @Override
    public boolean redo() {
        if (this.canvas == null
                || this.oldCharacter != canvas.getPixel(this.x, this.y)) {
            return false;
        }

        this.canvas.drawPixel(this.x, this.y, this.newCharacter);
        return true;
    }
}


//----------------------Vector.java----------------------

package academy.pocu.comp2500.assignment4;

public final class Vector {
    private final int x;
    private final int y;

    public Vector(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }
}
