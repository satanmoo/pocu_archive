package academy.pocu.comp2500.assignment4;

public final class DrawPixelCommand extends BaseCommand {
    private final int x;
    private final int y;

    private char charBeforeCommand;
    private final char charAfterCommand;

    public DrawPixelCommand(final int x, final int y, final char c) {
        this.x = x;
        this.y = y;
        this.charAfterCommand = c;
    }

    @Override
    public boolean execute(Canvas canvas) {
        assert canvas != null;

        if (this.commandStatus != CommandStatus.EXECUTE_ENABLED) {
            return false;
        }

        if (super.isValidXY(x, y) == false) {
            return false;
        }

        if (super.isPrintable(this.charAfterCommand) == false) {
            return false;
        }

        this.charBeforeCommand = canvas.getPixel(x, y);
        canvas.drawPixel(x, y, charAfterCommand);

        super.canvasOrNull = canvas;
        super.commandStatus = CommandStatus.UNDO_ENABLED;

        return true;
    }

    @Override
    public boolean undo() {
        if (super.commandStatus != CommandStatus.UNDO_ENABLED) {
            return false;
        }

        if (super.canvasOrNull.getPixel(x, y) != this.charAfterCommand) {
            return false;
        }

        super.canvasOrNull.drawPixel(x, y, charBeforeCommand);
        super.commandStatus = CommandStatus.REDO_ENABLED;

        return true;
    }

    @Override
    public boolean redo() {
        if (super.commandStatus != CommandStatus.REDO_ENABLED) {
            return false;
        }

        if (super.canvasOrNull.getPixel(x, y) != this.charBeforeCommand) {
            return false;
        }

        super.canvasOrNull.drawPixel(x, y, charAfterCommand);
        super.commandStatus = CommandStatus.UNDO_ENABLED;

        return true;
    }
}
