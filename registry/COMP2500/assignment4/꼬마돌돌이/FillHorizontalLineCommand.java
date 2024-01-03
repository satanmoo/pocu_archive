package academy.pocu.comp2500.assignment4;


import java.util.ArrayList;

public final class FillHorizontalLineCommand extends BaseCommand {

    private final int y;
    private char charAfterCommand;
    private final ArrayList<Character> rowBeforeCommand = new ArrayList<>();

    public FillHorizontalLineCommand(final int y, final char c) {
        this.y = y;
        this.charAfterCommand = c;
    }

    @Override
    public boolean execute(Canvas canvas) {
        assert canvas != null;

        if (this.commandStatus != CommandStatus.EXECUTE_ENABLED) {
            return false;
        }

        if (super.isValidY(y) == false) {
            return false;
        }

        if (super.isPrintable(charAfterCommand) == false) {
            return false;
        }

        for (int i = 0; i < canvas.getWidth(); i++) {
            this.rowBeforeCommand.add(canvas.getPixel(i, y));
        }

        canvas.fillHorizontalLine(y, this.charAfterCommand);

        super.canvasOrNull = canvas;
        super.commandStatus = CommandStatus.UNDO_ENABLED;

        return true;
    }

    @Override
    public boolean undo() {
        if (super.commandStatus != CommandStatus.UNDO_ENABLED) {
            return false;
        }

        for (int i = 0; i < super.canvasOrNull.getWidth(); i++) {
            if (super.canvasOrNull.getPixel(i, y) != this.charAfterCommand) {
                return false;
            }
        }

        for (int i = 0; i < super.canvasOrNull.getWidth(); i++) {
            super.canvasOrNull.drawPixel(i, y, rowBeforeCommand.get(i));
        }

        super.commandStatus = CommandStatus.REDO_ENABLED;

        return true;
    }

    @Override
    public boolean redo() {
        if (super.commandStatus != CommandStatus.REDO_ENABLED) {
            return false;
        }

        for (int i = 0; i < super.canvasOrNull.getWidth(); i++) {
            if (super.canvasOrNull.getPixel(i, y) != this.rowBeforeCommand.get(i)) {
                return false;
            }
        }

        super.canvasOrNull.fillHorizontalLine(y, this.charAfterCommand);
        super.commandStatus = CommandStatus.UNDO_ENABLED;

        return true;
    }
}
