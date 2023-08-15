package academy.pocu.comp2500.assignment4;

public class DrawPixelCommand implements ICommand {
    private final int x;
    private final int y;
    private final char c;
    private Canvas canvas;
    private char pre;
    private char cur;
    private boolean canUndo;
    private boolean canRedo;

    public DrawPixelCommand(int x, int y, char c) {
        this.x = x;
        this.y = y;
        this.c = c;
    }

    @Override
    public boolean execute(Canvas canvas) {
        if (canUndo) {
            return false;
        }

        if (x < 0 || x >= canvas.getWidth() || y < 0 || y >= canvas.getHeight()) {
            return false;
        }

        pre = canvas.getPixel(x, y);
        canvas.drawPixel(x, y, c);
        cur = c;
        this.canvas = canvas;
        return (canUndo = true);
    }

    @Override
    public boolean undo() {
        if (!canUndo) {
            return false;
        }

        if (cur != canvas.getPixel(x, y)) {
            return false;
        }

        canvas.drawPixel(x, y, pre);
        cur = pre;
        this.canUndo = false;
        return (canRedo = true);
    }

    @Override
    public boolean redo() {
        if (!canRedo) {
            return false;
        }

        if (cur != canvas.getPixel(x, y)) {
            return false;
        }

        canvas.drawPixel(x, y, c);
        cur = c;
        return (canUndo = true);
    }
}
