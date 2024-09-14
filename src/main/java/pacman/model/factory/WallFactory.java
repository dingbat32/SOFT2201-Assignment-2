package pacman.model.factory;

import javafx.scene.image.Image;
import pacman.model.engine.ImageLoader;
import pacman.model.entity.Renderable;
import pacman.model.entity.dynamic.physics.BoundingBox;
import pacman.model.entity.dynamic.physics.BoundingBoxImpl;
import pacman.model.entity.dynamic.physics.Vector2D;
import pacman.model.entity.staticentity.StaticEntityImpl;

public class WallFactory implements RenderableFactory {
    private int wallType;
    WallFactory(int type) {
        this.wallType = type;
    }
    @Override
    public Renderable createRenderable(int x, int y) {
        ImageLoader imageLoader = ImageLoader.getInstance();
        Image image;
        switch (wallType) {
            case 1:
                image = imageLoader.loadImage("walls/horizontal.png");
                break;
            case 2:
                image = imageLoader.loadImage("walls/vertical.png");
                break;
            case 3:
                image = imageLoader.loadImage("walls/upLeft.png");
                break;
            case 4:
                image = imageLoader.loadImage("walls/upRight.png");
                break;
            case 5:
                image = imageLoader.loadImage("walls/downLeft.png");
                break;
            case 6:
                image = imageLoader.loadImage("walls/downRight.png");
                break;
            default:
                throw new IllegalArgumentException("Invalid wall type");
        }
        BoundingBox boundingBox = new BoundingBoxImpl(new Vector2D(x*16,y*16), image.getHeight(), image.getWidth());
        return new StaticEntityImpl(boundingBox, Renderable.Layer.FOREGROUND, image);
    }
}
