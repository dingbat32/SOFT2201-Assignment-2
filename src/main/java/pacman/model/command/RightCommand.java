package pacman.model.command;

import pacman.model.entity.dynamic.physics.Direction;
import pacman.model.entity.dynamic.player.Controllable;

public class RightCommand implements Command {
    Controllable controllable;
    public RightCommand(Controllable givenControllable) {
        controllable = givenControllable;
    }

    @Override
    public void execute() {
        controllable.right();
    }

    @Override
    public boolean isDoable() {
        return controllable.canGo(Direction.RIGHT);
    }
}
