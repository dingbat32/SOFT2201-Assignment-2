package pacman.model.factory;

import javafx.scene.image.Image;
import pacman.model.engine.ImageLoader;
import pacman.model.entity.Renderable;
import pacman.model.entity.dynamic.physics.BoundingBox;
import pacman.model.entity.dynamic.physics.BoundingBoxImpl;
import pacman.model.entity.dynamic.physics.Vector2D;
import pacman.model.entity.staticentity.StaticEntityImpl;
import pacman.model.maze.MazeCreator;

public class WallFactory implements RenderableFactory {
    private int wallType;
    WallFactory(int type) {
        this.wallType = type;
    }
    @Override
    public Renderable createRenderable(int x, int y) {
        ImageLoader imageLoader = ImageLoader.getInstance();
        Image image = switch (wallType) {
            case 1 -> imageLoader.loadImage("walls/horizontal.png");
            case 2 -> imageLoader.loadImage("walls/vertical.png");
            case 3 -> imageLoader.loadImage("walls/upLeft.png");
            case 4 -> imageLoader.loadImage("walls/upRight.png");
            case 5 -> imageLoader.loadImage("walls/downLeft.png");
            case 6 -> imageLoader.loadImage("walls/downRight.png");
            default -> throw new IllegalArgumentException("Invalid wall type");
        };
        BoundingBox boundingBox = new BoundingBoxImpl(new Vector2D(x*MazeCreator.RESIZING_FACTOR,y*MazeCreator.RESIZING_FACTOR), image.getHeight(), image.getWidth());
        return new StaticEntityImpl(boundingBox, Renderable.Layer.FOREGROUND, image);
    }
}
