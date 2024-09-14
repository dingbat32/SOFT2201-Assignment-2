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
    public Renderable createRenderable(int x, int y) {
        ImageLoader imageLoader = ImageLoader.getInstance();
        Image image = imageLoader.loadImage("pellet.png");
        BoundingBox boundingBox = new BoundingBoxImpl(new Vector2D(x*16,y*16), image.getHeight(), image.getWidth());
        return new Pellet(boundingBox, Renderable.Layer.FOREGROUND, image, 100);
    }
}
