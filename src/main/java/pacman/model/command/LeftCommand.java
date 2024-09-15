package pacman.model.command;

import pacman.model.entity.dynamic.physics.Direction;
import pacman.model.entity.dynamic.player.Controllable;

public class LeftCommand implements Command {
    Controllable controllable;
    public LeftCommand(Controllable givenControllable) {
        controllable = givenControllable;
    }

    @Override
    public void execute() {
        controllable.left();
    }

    @Override
    public boolean isDoable() {
        return controllable.canGo(Direction.LEFT);
    }
}
