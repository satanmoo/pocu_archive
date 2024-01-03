package academy.pocu.comp2500.assignment4;


import java.util.ArrayList;

public final class FillVerticalLineCommand extends BaseCommand {

    private final int x;
    private char charAfterCommand;
    private ArrayList<Character> columnBeforeCommand = new ArrayList<>();

    public FillVerticalLineCommand(final int x, final char c) {
        this.x = x;
        this.charAfterCommand = c;
    }

    @Override
    public boolean execute(Canvas canvas) {
        assert canvas != null;

        if (this.commandStatus != CommandStatus.EXECUTE_ENABLED) {
            return false;
        }


        if (super.isValidX(x) == false) {
            return false;
        }

        if (super.isPrintable(charAfterCommand) == false) {
            return false;
        }

        for (int i = 0; i < canvas.getHeight(); i++) {
            this.columnBeforeCommand.add(canvas.getPixel(x, i));
        }

        canvas.fillVerticalLine(x, this.charAfterCommand);

        super.canvasOrNull = canvas;
        super.commandStatus = CommandStatus.UNDO_ENABLED;

        return true;
    }

    @Override
    public boolean undo() {
        if (super.commandStatus != CommandStatus.UNDO_ENABLED) {
            return false;
        }

        for (int i = 0; i < super.canvasOrNull.getHeight(); i++) {
            if (super.canvasOrNull.getPixel(x, i) != this.charAfterCommand) {
                return false;
            }
        }

        for (int i = 0; i < super.canvasOrNull.getHeight(); i++) {
            super.canvasOrNull.drawPixel(x, i, columnBeforeCommand.get(i));
        }

        super.commandStatus = CommandStatus.REDO_ENABLED;

        return true;
    }

    @Override
    public boolean redo() {
        if (super.commandStatus != CommandStatus.REDO_ENABLED) {
            return false;
        }

        for (int i = 0; i < super.canvasOrNull.getHeight(); i++) {
            if (super.canvasOrNull.getPixel(x, i) != this.columnBeforeCommand.get(i)) {
                return false;
            }
        }

        super.canvasOrNull.fillVerticalLine(x, this.charAfterCommand);
        super.commandStatus = CommandStatus.UNDO_ENABLED;

        return true;
    }
}
