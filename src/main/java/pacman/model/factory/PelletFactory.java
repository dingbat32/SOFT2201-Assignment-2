package pacman.model.factory;

import javafx.scene.image.Image;
import pacman.model.engine.ImageLoader;
import pacman.model.entity.Renderable;
import pacman.model.entity.dynamic.physics.BoundingBox;
import pacman.model.entity.dynamic.physics.BoundingBoxImpl;
import pacman.model.entity.dynamic.physics.Vector2D;
import pacman.model.entity.staticentity.collectable.Pellet;

public class PelletFactory implements RenderableFactory {

    @Override
    public Renderable createRenderable(Vector2D position) {
        ImageLoader imageLoader = ImageLoader.getInstance();
        Image image = imageLoader.loadImage("pellet.png");
        BoundingBox boundingBox = new BoundingBoxImpl(position, image.getHeight(), image.getWidth());
        return new Pellet(boundingBox, Renderable.Layer.FOREGROUND, image, 100);
    }
}
