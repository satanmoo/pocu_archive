package academy.pocu.comp2500.assignment4;

public class FillVerticalLineCommand implements ICommand {
    private final int x;
    private final char c;
    private Canvas canvas;
    private char[] pre;
    private char[] cur;
    private boolean canUndo;
    private boolean canRedo;

    public FillVerticalLineCommand(int x, char c) {
        this.x = x;
        this.c = c;
    }

    @Override
    public boolean execute(Canvas canvas) {
        if (canUndo) {
            return false;
        }

        if (x < 0 || x >= canvas.getWidth() || c < 32 || c > 126) {
            return false;
        }

        int height = canvas.getHeight();
        pre = new char[height];
        for (int y = 0; y < height; ++y) {
            pre[y] = canvas.getPixel(x, y);
        }

        canvas.fillVerticalLine(x, c);

        cur = new char[height];
        for (int y = 0; y < height; ++y) {
            cur[y] = canvas.getPixel(x, y);
        }
        this.canvas = canvas;
        return (canUndo = true);
    }

    @Override
    public boolean undo() {
        if (!canUndo) {
            return false;
        }

        for (int y = 0; y < cur.length; ++y) {
            if (canvas.getPixel(x, y) != cur[y]) {
                return false;
            }
        }

        for (int y = 0; y < pre.length; ++y) {
            char tmp = canvas.getPixel(x, y);
            canvas.drawPixel(x, y, pre[y]);
            pre[y] = tmp;
            cur[y] = canvas.getPixel(x, y);
        }

        this.canUndo = false;
        return (canRedo = true);
    }

    @Override
    public boolean redo() {
        if (!canRedo) {
            return false;
        }

        for (int y = 0; y < cur.length; ++y) {
            if (canvas.getPixel(x, y) != cur[y]) {
                return false;
            }
        }

        for (int y = 0; y < pre.length; ++y) {
            pre[y] = canvas.getPixel(x, y);
        }

        canvas.fillVerticalLine(x, c);

        for (int y = 0; y < cur.length; ++y) {
            cur[y] = canvas.getPixel(x, y);
        }
        return (canUndo = true);
    }
}
