package pacman.model.factory;

import javafx.scene.image.Image;
import pacman.model.engine.ImageLoader;
import pacman.model.entity.Renderable;
import pacman.model.entity.dynamic.ghost.GhostImpl;
import pacman.model.entity.dynamic.ghost.GhostMode;
import pacman.model.entity.dynamic.physics.*;

import java.util.List;
import java.util.Random;

public class GhostFactory  implements RenderableFactory {

    @Override
    public Renderable createRenderable(int x, int y) {
        ImageLoader imageLoader = ImageLoader.getInstance();
        Image ghostImage = imageLoader.loadImage("ghosts/ghost.png");
        BoundingBox ghostBox = new BoundingBoxImpl(new Vector2D(x*16+4,y*16-4), ghostImage.getHeight(), ghostImage.getWidth());
        KinematicStateImpl state = new KinematicStateImpl.KinematicStateBuilder()
                .setPosition(new Vector2D(x*16+4,y*16-4))
                .build();
        List<Vector2D> corners = List.of(
                new Vector2D(0, 0),
                new Vector2D(0, 576),
                new Vector2D(448, 0),
                new Vector2D(448, 576));
        Random random = new Random();
        Vector2D targetCorner = corners.get(random.nextInt(4));
        return new GhostImpl(ghostImage, ghostBox, state, GhostMode.SCATTER, targetCorner, Direction.UP);
    }
}
