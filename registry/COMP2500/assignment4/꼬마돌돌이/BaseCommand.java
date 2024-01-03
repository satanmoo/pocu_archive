package academy.pocu.comp2500.assignment4;

public abstract class BaseCommand implements ICommand {

    protected CommandStatus commandStatus = CommandStatus.EXECUTE_ENABLED;
    protected Canvas canvasOrNull;

    protected BaseCommand() {};


    protected boolean isValidXY(final int x, final int y) {
        // helper function
        return isValidX(x) && isValidY(y);
    }

    protected boolean isValidX(final int x) {
        return (x >= 0 && x < canvasOrNull.getWidth());
    }

    protected boolean isValidY(final int y) {
        return (y >= 0 || y < canvasOrNull.getHeight());
    }

    protected boolean isAlpha(final char character) {
        return (character >= CanvasInfo.UPPER_A.getCharacter() && character <= CanvasInfo.UPPER_Z.getCharacter())
                || (character >= CanvasInfo.LOWER_A.getCharacter() && character <= CanvasInfo.LOWER_Z.getCharacter());
    }

    protected boolean isPrintable(final char character) {
        return character >= CanvasInfo.LOW_LIMIT_PRINTABLE_ASCII.getCharacter() && character <= CanvasInfo.HIGH_LIMIT_PRINTABLE_ASCII.getCharacter();
    }

}
