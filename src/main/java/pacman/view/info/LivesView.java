package pacman.view.info;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import pacman.model.ScoreKeeper;
import pacman.model.engine.ImageLoader;
import pacman.model.entity.Renderable;
import pacman.model.entity.dynamic.ghost.Ghost;
import pacman.model.entity.dynamic.physics.BoundingBox;
import pacman.model.entity.dynamic.physics.BoundingBoxImpl;
import pacman.model.entity.dynamic.physics.Vector2D;
import pacman.model.entity.staticentity.scoreboard.Life;
import pacman.model.maze.MazeCreator;
import pacman.view.entity.EntityView;
import pacman.view.entity.EntityViewImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LivesView implements Observer, Display {
    public final int LIVES_HEIGHT_OFFSET = 550;
    private List<Life> lives;
    private final ScoreKeeper scoreKeeper;
    private final List<EntityView> entityViews;
    public LivesView() {
        this.scoreKeeper = ScoreKeeper.getInstance();
        lives = new ArrayList<>();
        for (int i = 0; i < scoreKeeper.getStartingLives(); i++) {
            lives.add(createLife(i));
        }
        entityViews = new ArrayList<>();
        for (Life life : lives) {
            EntityView entityView = new EntityViewImpl(life);
            entityViews.add(entityView);
        }
    }
    @Override
    public void update() {
        int totalLives = scoreKeeper.getStartingLives();
        int livesRemaining = scoreKeeper.getNumLives();
        for (int i = 0; i < totalLives; i++) {
            if (i < livesRemaining) {
                lives.get(i).show();
            } else {
                lives.get(i).hide();
            }
        }
        entityViews.forEach(EntityView::update);
    }

    @Override
    public List<Node> getNodes() {
        List<Node> nodes = new ArrayList<>();
        for (EntityView entityView : entityViews) {
            nodes.add(entityView.getNode());
        }
        return nodes;
    }

    private Life createLife(int i) {
        ImageLoader imageLoader = ImageLoader.getInstance();
        Image image = imageLoader.loadImage("pacman/playerRight.png");
        Vector2D position = new Vector2D(
                i * MazeCreator.RESIZING_FACTOR * 2, 550);
        BoundingBox lifeBox = new BoundingBoxImpl(position, image.getHeight(), image.getWidth());
        return new Life(lifeBox, Renderable.Layer.FOREGROUND, image);
    }
}
