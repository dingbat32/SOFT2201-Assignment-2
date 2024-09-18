package pacman.model.factory;

import pacman.model.entity.Renderable;

public interface RenderableFactoryRegistry {
    Renderable createRenderable(char tileType, int x, int y);
}
