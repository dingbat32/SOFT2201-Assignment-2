package pacman.view.info;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import pacman.model.ScoreKeeper;
import pacman.model.entity.Renderable;
import pacman.model.entity.dynamic.physics.BoundingBox;
import pacman.model.entity.dynamic.physics.BoundingBoxImpl;
import pacman.model.entity.dynamic.physics.Vector2D;
import pacman.model.entity.staticentity.StaticEntityImpl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class ScoreView implements Observer, Display {
    private final ScoreKeeper scoreKeeper;
    private final Text scoreText;

    public ScoreView() {
        this.scoreKeeper = ScoreKeeper.getInstance();
        scoreText = new Text("0");
        Font font;
        try {
            font = Font.loadFont(new FileInputStream("src/main/resources/maze/PressStart2P-Regular.ttf"), 25);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Unable to load font", e);
        }
        scoreText.setFont(font);
        scoreText.setWrappingWidth(448);
        scoreText.setFill(Color.WHITE);
        scoreText.setX(20);
        scoreText.setY(40);
        scoreText.toFront();
        scoreText.setVisible(true);
    }

    @Override
    public void update() {
        int score = scoreKeeper.getScore();
        scoreText.setText(String.valueOf(score));
    }

    public Text textDisplay() {
        Vector2D position = new Vector2D(0,0);
        double width = 448;
        double height = scoreText.getLayoutBounds().getHeight();
        BoundingBox boundingBox = new BoundingBoxImpl(position, height, width);
        //Renderable textRenderable = new StaticEntityImpl(boundingBox, Renderable.Layer.FOREGROUND, L);
        return null;
    }

    @Override
    public List<Node> getNodes() {
        return List.of(scoreText);
    }
}
