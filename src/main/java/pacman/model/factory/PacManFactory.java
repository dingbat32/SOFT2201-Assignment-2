package pacman.model.factory;

import javafx.scene.image.Image;
import pacman.model.engine.ImageLoader;
import pacman.model.entity.Renderable;
import pacman.model.entity.dynamic.physics.*;
import pacman.model.entity.dynamic.player.Pacman;
import pacman.model.entity.dynamic.player.PacmanVisual;

import java.util.HashMap;
import java.util.Map;

public class PacManFactory implements RenderableFactory {

    @Override
    public Renderable createRenderable(Vector2D position) {
        ImageLoader imageLoader = ImageLoader.getInstance();
        Map<PacmanVisual, Image> pacmanVisualImages = new HashMap<>();
        pacmanVisualImages.put(PacmanVisual.CLOSED, imageLoader.loadImage("pacman/playerClosed.png"));
        pacmanVisualImages.put(PacmanVisual.DOWN, imageLoader.loadImage("pacman/playerDown.png"));
        pacmanVisualImages.put(PacmanVisual.LEFT, imageLoader.loadImage("pacman/playerLeft.png"));
        pacmanVisualImages.put(PacmanVisual.RIGHT, imageLoader.loadImage("pacman/playerRight.png"));
        pacmanVisualImages.put(PacmanVisual.UP, imageLoader.loadImage("pacman/playerUp.png"));
        Image closedImage = pacmanVisualImages.get(PacmanVisual.CLOSED);
        BoundingBox pacmanBox = new BoundingBoxImpl(position, closedImage.getHeight(), closedImage.getWidth());
        KinematicStateImpl state = new KinematicStateImpl.KinematicStateBuilder()
                .setPosition(position)
                .build();
        return new Pacman(closedImage, pacmanVisualImages, pacmanBox, state);
    }
}
