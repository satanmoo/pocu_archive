package academy.pocu.comp2500.assignment4;

public class ClearCommand implements ICommand {
    private Canvas canvas;
    private char[][] pre;
    private boolean canUndo;
    private boolean canRedo;

    public ClearCommand() {
    }

    @Override
    public boolean execute(Canvas canvas) {
        if (canUndo) {
            return false;
        }

        int height = canvas.getHeight();
        int width = canvas.getWidth();
        pre = new char[height][width];

        for (int y = 0; y < height; ++y) {
            for (int x = 0; x < width; ++x) {
                pre[y][x] = canvas.getPixel(x, y);
            }
        }

        canvas.clear();
        this.canvas = canvas;
        return (canUndo = true);
    }

    @Override
    public boolean undo() {
        if (!canUndo) {
            return false;
        }

        for (int y = 0; y < canvas.getHeight(); ++y) {
            for (int x = 0; x < canvas.getWidth(); ++x) {
                if (canvas.getPixel(x, y) != ' ') {
                    return false;
                }
            }
        }

        for (int y = 0; y < canvas.getHeight(); ++y) {
            for (int x = 0; x < canvas.getWidth(); ++x) {
                canvas.drawPixel(x, y, pre[y][x]);
            }
        }

        this.canUndo = false;
        return (canRedo = true);
    }

    @Override
    public boolean redo() {
        if (!canRedo) {
            return false;
        }

        for (int y = 0; y < canvas.getHeight(); ++y) {
            for (int x = 0; x < canvas.getWidth(); ++x) {
                if (pre[y][x] != canvas.getPixel(x, y)) {
                    return false;
                }
            }
        }


        canvas.clear();
        return (canUndo = true);
    }
}
