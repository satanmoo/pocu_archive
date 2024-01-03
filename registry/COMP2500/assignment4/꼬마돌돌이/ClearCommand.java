package academy.pocu.comp2500.assignment4;


import java.util.ArrayList;

public final class ClearCommand extends BaseCommand {

    private int hashcodeBeforeCommand;
    private int hashcodeAfterCommand;
    private ArrayList<ArrayList<Character>> canvasCopy = new ArrayList<>();

    @Override
    public boolean execute(Canvas canvas) {
        assert canvas != null;

        if (super.commandStatus != CommandStatus.EXECUTE_ENABLED) {
            return false;
        }

        this.hashcodeBeforeCommand = canvas.hashCode();
        for (int i = 0; i < canvas.getWidth(); i++) {
            ArrayList<Character> row = new ArrayList<>();
            for (int j = 0; j < canvas.getHeight(); j++) {
                row.add(canvas.getPixel(i, j));
            }
            this.canvasCopy.add(row);
        }

        canvas.clear();
        this.hashcodeAfterCommand = canvas.hashCode();
        this.canvasOrNull = canvas;
        this.commandStatus = CommandStatus.UNDO_ENABLED;

        return true;
    }

    @Override
    public boolean undo() {
        if (this.commandStatus != CommandStatus.UNDO_ENABLED) {
            return false;
        }

        if (this.hashcodeAfterCommand != canvasOrNull.hashCode()) {
            return false;
        }

        for (int i = 0; i < canvasOrNull.getWidth(); i++) {
            for (int j = 0; j < canvasOrNull.getHeight(); j++) {
                canvasOrNull.drawPixel(i, j, canvasCopy.get(i).get(j));
            }
        }

        this.commandStatus = CommandStatus.REDO_ENABLED;
        return true;
    }

    @Override
    public boolean redo() {
        if (this.commandStatus != CommandStatus.REDO_ENABLED) {
            return false;
        }

        if (this.hashcodeBeforeCommand != canvasOrNull.hashCode()) {
            return false;
        }

        this.canvasOrNull.clear();
        this.commandStatus = CommandStatus.UNDO_ENABLED;
        return true;
    }
}
