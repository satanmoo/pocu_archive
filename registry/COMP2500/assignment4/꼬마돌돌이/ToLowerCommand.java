package academy.pocu.comp2500.assignment4;

public final class ToLowerCommand extends BaseCommand {
    private final int x;
    private final int y;

    private char charBeforeCommand;
    private char charAfterCommand;

    public ToLowerCommand(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean execute(Canvas canvas) {
        assert canvas != null;

        if (super.commandStatus != CommandStatus.EXECUTE_ENABLED) {
            return false;
        }

        if (super.isValidXY(x, y) == false) {
            return false;
        }

        this.charBeforeCommand = canvas.getPixel(x, y);
        canvas.toLower(x, y);
        this.charAfterCommand = canvas.getPixel(x, y);

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

        if (this.charBeforeCommand == this.charAfterCommand) {
            return false;
        }

        super.canvasOrNull.toUpper(x, y);
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

        if (this.charBeforeCommand == this.charAfterCommand) {
            return false;
        }

        super.canvasOrNull.toLower(x, y);
        super.commandStatus = CommandStatus.UNDO_ENABLED;

        return true;
    }
}
