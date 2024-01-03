package academy.pocu.comp2500.assignment4;

public class FillHorizontalLineCommand implements ICommand {
    private final int y;
    private final char c;
    private Canvas canvas;
    private char[] pre;
    private char[] cur;
    private boolean canUndo;
    private boolean canRedo;

    public FillHorizontalLineCommand(int y, char c) {
        this.y = y;
        this.c = c;
    }

    @Override
    public boolean execute(Canvas canvas) {
        if (canUndo) {
            return false;
        }

        if (y < 0 || y >= canvas.getHeight() || c < 32 || c > 126) {
            return false;
        }

        int width = canvas.getWidth();
        pre = new char[width];
        for (int x = 0; x < width; ++x) {
            pre[x] = canvas.getPixel(x, y);
        }

        canvas.fillHorizontalLine(y, c);

        cur = new char[width];
        for (int x = 0; x < width; ++x) {
            cur[x] = canvas.getPixel(x, y);
        }
        this.canvas = canvas;
        return (canUndo = true);
    }

    @Override
    public boolean undo() {
        if (!canUndo) {
            return false;
        }

        for (int x = 0; x < cur.length; ++x) {
            if (canvas.getPixel(x, y) != cur[x]) {
                return false;
            }
        }

        for (int x = 0; x < pre.length; ++x) {
            char tmp = canvas.getPixel(x, y);
            canvas.drawPixel(x, y, pre[x]);
            pre[x] = tmp;
            cur[x] = canvas.getPixel(x, y);
        }

        this.canUndo = false;
        return (canRedo = true);
    }

    @Override
    public boolean redo() {
        if (!canRedo) {
            return false;
        }

        for (int x = 0; x < cur.length; ++x) {
            if (canvas.getPixel(x, y) != cur[x]) {
                return false;
            }
        }

        for (int x = 0; x < pre.length; ++x) {
            pre[x] = canvas.getPixel(x, y);
        }

        canvas.fillHorizontalLine(y, c);

        for (int x = 0; x < cur.length; ++x) {
            cur[x] = canvas.getPixel(x, y);
        }
        return (canUndo = true);
    }
}
