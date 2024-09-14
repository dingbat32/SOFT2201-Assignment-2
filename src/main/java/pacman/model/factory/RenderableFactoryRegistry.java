package pacman.model.factory;

import pacman.model.entity.Renderable;
import pacman.model.entity.dynamic.physics.Vector2D;

public interface RenderableFactoryRegistry {
    Renderable createRenderable(char tileType, Vector2D position);
}
