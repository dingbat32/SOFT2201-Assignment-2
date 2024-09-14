package pacman.model.factory;

import pacman.model.entity.Renderable;
import pacman.model.entity.dynamic.physics.Vector2D;

import java.util.HashMap;
import java.util.Map;

public class RenderableFactoryRegistryImpl implements RenderableFactoryRegistry {
    private final Map<Character, RenderableFactory> factoryRegistry = new HashMap<>();
    public RenderableFactoryRegistryImpl() {
        for (int wallType = 1 ; wallType <= 6 ; wallType++) {
            registerFactory(Character.forDigit(wallType, 10), new WallFactory(wallType));
        }
        registerFactory('7', new PelletFactory());
        registerFactory('g', new GhostFactory());
        registerFactory('p', new PacManFactory());
    }

    private void registerFactory(char key, RenderableFactory factory) {

        if (factoryRegistry.containsKey(key)) {
            throw new IllegalStateException("Duplicate registration of factory for specification.");
        }

        factoryRegistry.put(key, factory);
    }

    @Override
    public Renderable createRenderable(char tileType, Vector2D position) {

        if (!factoryRegistry.containsKey(tileType)) {
            throw new IllegalStateException("No factory registered for specification.");
        }

        RenderableFactory factory = factoryRegistry.get(tileType);
        return factory.createRenderable(position);
    }
}
