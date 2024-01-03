package academy.pocu.comp2500.assignment4;

public class DecreasePixelCommand implements ICommand {
    private final int x;
    private final int y;
    private Canvas canvas;
    private char cur;
    private boolean canUndo;
    private boolean canRedo;

    public DecreasePixelCommand(int x, int y) {
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

        boolean isExecuted = canvas.decreasePixel(x, y);
        if (!isExecuted) {
            return false;
        }
        cur = canvas.getPixel(x, y);
        this.canvas = canvas;
        return (canUndo = true);
    }

    @Override
    public boolean undo() { // execute -> 다른 개체 execute -> undo 할 수도 있음? ㅇㅇ.. 근데 이건 이 개체에서 처리하는 게 아닌 듯?
        if (!canUndo) {
            return false;
        }

        if (cur != canvas.getPixel(x, y)) {
            return false;
        }

        canvas.increasePixel(x, y);
        cur = canvas.getPixel(x, y);
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

        canvas.decreasePixel(x, y);
        cur = canvas.getPixel(x, y);
        canRedo = false;
        return (canUndo = true);
    }
}
