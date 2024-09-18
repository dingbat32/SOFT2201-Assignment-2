package pacman.view.info;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import pacman.model.ScoreKeeper;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.Instant;
import java.util.List;

public class StartView implements Observer, Display, Pauseable {
    private Text startingText;
    private Instant pauseEnd;
    public StartView() {
        startingText = new Text("READY!");
        Font font;
        try {
            font = Font.loadFont(new FileInputStream("src/main/resources/maze/PressStart2P-Regular.ttf"), 25);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Unable to load font", e);
        }
        startingText.setFont(font);
        startingText.setWrappingWidth(448);
        startingText.setFill(Color.WHITE);
        startingText.setX(200);
        startingText.setY(300);
        startingText.toFront();
        startingText.setVisible(true);
        pauseEnd = Instant.now().plusSeconds(5);
    }
    @Override
    public List<Node> getNodes() {
        return List.of(startingText);
    }

    @Override
    public void update() {

    }

    @Override
    public boolean isPaused() {
        return Instant.now().isBefore(pauseEnd);
    }
}
