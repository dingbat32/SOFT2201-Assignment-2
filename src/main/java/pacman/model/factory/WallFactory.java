package pacman.model.factory;

import pacman.model.entity.Renderable;
import pacman.model.entity.dynamic.physics.Vector2D;

public class WallFactory implements RenderableFactory {
    private int wallType;
    WallFactory(int type) {
        this.wallType = type;
    }
    @Override
    public Renderable createRenderable(Vector2D position) {
        return null;
    }
}
