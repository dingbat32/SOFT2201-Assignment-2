package pacman.model.factory;

import javafx.scene.image.Image;
import pacman.model.engine.ImageLoader;
import pacman.model.entity.Renderable;
import pacman.model.entity.dynamic.physics.BoundingBox;
import pacman.model.entity.dynamic.physics.BoundingBoxImpl;
import pacman.model.entity.dynamic.physics.KinematicStateImpl;
import pacman.model.entity.dynamic.physics.Vector2D;
import pacman.model.entity.dynamic.player.Pacman;
import pacman.model.entity.dynamic.player.PacmanVisual;
import pacman.model.entity.staticentity.scoreboard.Life;
import pacman.model.maze.MazeCreator;

import java.util.HashMap;
import java.util.Map;

public class LifeFactory implements RenderableFactory {
    @Override
    public Renderable createRenderable(int x, int y) {
        ImageLoader imageLoader = ImageLoader.getInstance();
        Image image = imageLoader.loadImage("pacman/playerRight.png");
        Vector2D position = new Vector2D(
                x * MazeCreator.RESIZING_FACTOR,
                y * MazeCreator.RESIZING_FACTOR);
        BoundingBox lifeBox = new BoundingBoxImpl(position, image.getHeight(), image.getWidth());
        return new Life(lifeBox, Renderable.Layer.FOREGROUND, image);
    }
}
