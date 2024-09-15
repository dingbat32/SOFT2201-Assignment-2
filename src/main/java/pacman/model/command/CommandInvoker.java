package pacman.model.command;

import java.util.ArrayList;
import java.util.List;

public class CommandInvoker {
    private Command currentCommand;
    private static CommandInvoker uniqueInstance;

    private CommandInvoker() {}

    public static CommandInvoker getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new CommandInvoker();
        }
        return uniqueInstance;
    }

    public void addCommand(Command command) {
        currentCommand = command;
    }

    public void update() {
        if (currentCommand != null && currentCommand.isDoable()) {
            currentCommand.execute();
        }
    }
}
