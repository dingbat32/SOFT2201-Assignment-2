package pacman.model.command;

import pacman.model.entity.dynamic.physics.Direction;
import pacman.model.entity.dynamic.player.Controllable;

public class UpCommand implements Command {
    Controllable controllable;
    public UpCommand(Controllable givenControllable) {
        controllable = givenControllable;
    }

    @Override
    public void execute() {
        controllable.up();
    }

    @Override
    public boolean isDoable() {
        return controllable.canGo(Direction.UP);
    }
}
