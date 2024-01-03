package academy.pocu.comp2500.assignment4;

public final class IncreasePixelCommand extends BaseCommand {
    private final int x;
    private final int y;

    private char charBeforeCommand;
    private char charAfterCommand;

    public IncreasePixelCommand(final int x, final int y) {
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

        // 왜 increase & decrease 는 구체 커맨드에서 유효성 검증을 안할까?
        // incre&&decre는 canvas 함수의 반환형(boolean)으로 검증까지 해주기 때문에 좀 더 간결하게 작성가능.
        // 그러나 canvas.ToUpper ToLower Horizontal Vertical 은 반환형이 void 이기 때문에 바깥에서 유효성 검증을 해야함
        if (canvas.increasePixel(x, y) == false) {
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

        this.canvasOrNull.decreasePixel(x, y);
        this.commandStatus = CommandStatus.REDO_ENABLED;

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

        this.canvasOrNull.increasePixel(x, y);
        this.commandStatus = CommandStatus.UNDO_ENABLED;

        return true;
    }
}
