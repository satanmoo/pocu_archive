package academy.pocu.comp2500.assignment4;

import java.util.Stack;

public final class CommandHistoryManager {
    private final Canvas canvas;
    private final Stack<ICommand> allCommands = new Stack<>();
    private final Stack<ICommand> undoCommands = new Stack<>();

    public CommandHistoryManager(final Canvas canvas) {
        assert canvas != null;
        this.canvas = canvas;
    }

    public boolean execute(final ICommand iCommand) {
        assert iCommand != null;

        if (iCommand.execute(canvas) == false) {
            return false;
        }

        undoCommands.clear();
        allCommands.push(iCommand);
        return true;
    }

    public boolean canUndo() {
        return allCommands.size() > 0;
    }

    public boolean canRedo() {
        return undoCommands.size() > 0;
    }

    public boolean undo() {
        if (canUndo() == false) {
            return false;
        }

        // 명령어가 매니저에 execute 된 이상 여기부터는 무조건 undo, redo에 대한 로직을 실행해야함.
        // 실패한 undo, redo 또한 히스토리에 취급되어야하기 때문에
        // Canvas에 명령어가 적용되었건, 안되었건 그것을 HistoryManager가 할 일이 아니다.
        // 매니저는 히스토리를 취급하는 것이 목적이기 때문이다.
        final ICommand command = allCommands.pop();
        undoCommands.push(command);

        return command.undo();
    }


    public boolean redo() {
        if (canRedo() == false) {
            return false;
        }

        final ICommand command = undoCommands.pop();
        allCommands.push(command);

        return command.redo();
    }
}
