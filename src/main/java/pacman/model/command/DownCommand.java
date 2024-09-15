package pacman.model.command;

import pacman.model.entity.dynamic.physics.Direction;
import pacman.model.entity.dynamic.player.Controllable;

public class DownCommand implements Command {
    Controllable controllable;
    public DownCommand(Controllable givenControllable) {
        controllable = givenControllable;
    }

    @Override
    public void execute() {
        controllable.down();
    }

    @Override
    public boolean isDoable() {
        return controllable.canGo(Direction.DOWN);
    }
}
