package academy.pocu.comp2500.assignment4;

public class ToLowerCommand implements ICommand {
    private final int x;
    private final int y;
    private Canvas canvas;
    private char pre;
    private char cur;
    private boolean canUndo;
    private boolean canRedo;

    public ToLowerCommand(int x, int y) {
        this.x = x;
        this.y = y;
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
        canvas.toLower(x, y);
        cur = canvas.getPixel(x, y);
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

        char tmp = canvas.getPixel(x, y);
        canvas.drawPixel(x, y, pre);
        cur = pre;
        pre = tmp;
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

        char tmp = canvas.getPixel(x, y);
        canvas.drawPixel(x, y, pre);
        cur = pre;
        pre = tmp;
        this.canRedo = false;
        return (canUndo = true);
    }
}
