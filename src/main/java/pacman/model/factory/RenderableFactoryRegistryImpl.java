package pacman.model.factory;

import pacman.model.entity.Renderable;
import pacman.model.entity.dynamic.physics.Vector2D;
import pacman.model.maze.RenderableType;

import java.util.HashMap;
import java.util.Map;

public class RenderableFactoryRegistryImpl implements RenderableFactoryRegistry {
    private final Map<Character, RenderableFactory> factoryRegistry = new HashMap<>();
    public RenderableFactoryRegistryImpl() {
        for (int wallType = 1 ; wallType <= 6 ; wallType++) {
            registerFactory(Character.forDigit(wallType, 10), new WallFactory(wallType));
        }
        registerFactory(RenderableType.PELLET, new PelletFactory());
        registerFactory(RenderableType.GHOST, new GhostFactory());
        registerFactory(RenderableType.PACMAN, new PacManFactory());
    }

    private void registerFactory(char key, RenderableFactory factory) {

        if (factoryRegistry.containsKey(key)) {
            throw new IllegalStateException("Duplicate registration of factory for specification.");
        }

        factoryRegistry.put(key, factory);
    }

    @Override
    public Renderable createRenderable(char tileType, int x, int y) {
        Vector2D position = new Vector2D(x*16, y*16);
        if (!factoryRegistry.containsKey(tileType)) {
            throw new IllegalStateException("No factory registered for key " + tileType);
        }

        RenderableFactory factory = factoryRegistry.get(tileType);
        return factory.createRenderable(position);
    }
}
