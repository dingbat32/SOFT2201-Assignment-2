package pacman.view.keyboard;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import pacman.model.command.*;
import pacman.model.entity.Renderable;
import pacman.model.entity.dynamic.player.Controllable;
import pacman.model.entity.dynamic.player.Pacman;
import pacman.model.maze.Maze;

/**
 * Responsible for handling keyboard input from player
 */
public class KeyboardInputHandler {
    Controllable controllable;
    CommandInvoker commandInvoker;
    public KeyboardInputHandler(CommandInvoker commandInvoker) {
        Renderable givenControllable = Maze.getInstance().getControllable();
        if (givenControllable instanceof Controllable) {
            controllable = (Controllable) givenControllable;
        } else {
            throw new RuntimeException("getControllable() did not get controllable");
        }
        this.commandInvoker = commandInvoker;
    }

    public void handlePressed(KeyEvent keyEvent) {
        KeyCode keyCode = keyEvent.getCode();
        switch (keyCode) {
            case LEFT:
                commandInvoker.addCommand(new LeftCommand(controllable));
                break;
            case RIGHT:
                commandInvoker.addCommand(new RightCommand(controllable));
                break;
            case DOWN:
                commandInvoker.addCommand(new DownCommand(controllable));
                break;
            case UP:
                commandInvoker.addCommand(new UpCommand(controllable));
                break;
        }
    }
}
