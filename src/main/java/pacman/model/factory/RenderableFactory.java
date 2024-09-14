package pacman.model.factory;

import pacman.model.entity.Renderable;
import pacman.model.entity.dynamic.physics.Vector2D;

public interface RenderableFactory {
    Renderable createRenderable(int x, int y);
}
