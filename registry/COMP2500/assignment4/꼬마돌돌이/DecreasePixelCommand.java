package academy.pocu.comp2500.assignment4;

public final class DecreasePixelCommand extends BaseCommand {
    private final int x;
    private final int y;

    private char charBeforeCommand;
    private char charAfterCommand;

    public DecreasePixelCommand(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean execute(Canvas canvas) {
        assert canvas != null;

        if (this.commandStatus != CommandStatus.EXECUTE_ENABLED) {
            return false;
        }

        this.charBeforeCommand = canvas.getPixel(x, y);

        if (canvas.decreasePixel(x, y) == false) {
            return false;
        }

        this.charAfterCommand = canvas.getPixel(x, y);
        super.canvasOrNull = canvas;
        super.commandStatus = CommandStatus.UNDO_ENABLED;

        return true;
    }

    @Override
    public boolean undo() {
        if (this.commandStatus != CommandStatus.UNDO_ENABLED) {
            return false;
        }

        if (canvasOrNull.getPixel(x, y) != this.charAfterCommand) {
            return false;
        }

        super.canvasOrNull.increasePixel(x, y);
        super.commandStatus = CommandStatus.REDO_ENABLED;

        return true;
    }

    @Override
    public boolean redo() {
        if (this.commandStatus != CommandStatus.REDO_ENABLED) {
            return false;
        }

        if (canvasOrNull.getPixel(x, y) != this.charBeforeCommand) {
            return false;
        }

        super.canvasOrNull.decreasePixel(x, y);
        super.commandStatus = CommandStatus.UNDO_ENABLED;

        return true;
    }
}
