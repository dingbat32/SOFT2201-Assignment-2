package pacman.view.keyboard;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import pacman.model.entity.Renderable;
import pacman.model.entity.dynamic.player.Controllable;
import pacman.model.entity.dynamic.player.Pacman;
import pacman.model.maze.Maze;

/**
 * Responsible for handling keyboard input from player
 */
public class KeyboardInputHandler {
    Controllable controllable;

    public KeyboardInputHandler() {
        Renderable givenControllable = Maze.getInstance().getControllable();
        if (givenControllable instanceof Controllable) {
            controllable = (Controllable) givenControllable;
        } else {
            throw new RuntimeException("getControllable() did not get controllable");
        }
    }

    public void handlePressed(KeyEvent keyEvent) {
        KeyCode keyCode = keyEvent.getCode();
        switch (keyCode) {
            case LEFT:
                controllable.left();
                break;
            case RIGHT:
                controllable.right();
                break;
            case DOWN:
                controllable.down();
                break;
            case UP:
                controllable.up();
                break;
        }
    }
}
