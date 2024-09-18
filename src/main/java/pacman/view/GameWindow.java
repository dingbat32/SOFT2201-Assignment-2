package pacman.view;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;
import pacman.model.ScoreKeeper;
import pacman.model.command.CommandInvoker;
import pacman.model.engine.GameEngine;
import pacman.model.entity.Renderable;
import pacman.model.factory.DisplayFactory;
import pacman.model.factory.DisplayFactoryImpl;
import pacman.view.background.BackgroundDrawer;
import pacman.view.background.StandardBackgroundDrawer;
import pacman.view.entity.EntityView;
import pacman.view.entity.EntityViewImpl;
import pacman.view.info.Display;
import pacman.view.info.Observer;
import pacman.view.info.Pauseable;
import pacman.view.keyboard.KeyboardInputHandler;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Responsible for managing the Pac-Man Game View
 */
public class GameWindow {

    public static final File FONT_FILE = new File("src/main/resources/maze/PressStart2P-Regular.ttf");

    private final Scene scene;
    private final Pane pane;
    private final GameEngine model;
    private final List<EntityView> entityViews;
    private final List<Display> displays;
    public GameWindow(GameEngine model, int width, int height) {
        this.model = model;

        pane = new Pane();
        scene = new Scene(pane, width, height);
        entityViews = new ArrayList<>();
        KeyboardInputHandler keyboardInputHandler = new KeyboardInputHandler(CommandInvoker.getInstance());
        scene.setOnKeyPressed(keyboardInputHandler::handlePressed);

        BackgroundDrawer backgroundDrawer = new StandardBackgroundDrawer();
        backgroundDrawer.draw(model, pane);

        DisplayFactory displayFactory = new DisplayFactoryImpl();
        displays = displayFactory.createDisplays();
        ScoreKeeper scoreKeeper = ScoreKeeper.getInstance();
        for (Display display : displays) {
            if (display instanceof Observer) {
                scoreKeeper.registerObserver((Observer) display);
            }
        }
        for (Display display : displays) {
            pane.getChildren().addAll(display.getNodes());
        }
    }

    public Scene getScene() {
        return scene;
    }

    public void run() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(34),
                t -> this.draw()));

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        model.startGame();
    }

    private void draw() {
        boolean doLoop = true;
        //don't do anything while game is paused
        for (Display display : displays) {
            if (display instanceof Pauseable pauseable) {
                if (pauseable.isPaused()) {
                    doLoop = false;
                }
            }
        }
        if (doLoop) {
            model.tick();
        }


        List<Renderable> entities = model.getRenderables();

        for (EntityView entityView : entityViews) {
            entityView.markForDelete();
        }

        for (Renderable entity : entities) {
            boolean notFound = true;
            for (EntityView view : entityViews) {
                if (view.matchesEntity(entity)) {
                    notFound = false;
                    view.update();
                    break;
                }
            }
            if (notFound) {
                EntityView entityView = new EntityViewImpl(entity);
                entityViews.add(entityView);
                pane.getChildren().add(entityView.getNode());
            }
        }

        for (EntityView entityView : entityViews) {
            if (entityView.isMarkedForDelete()) {
                pane.getChildren().remove(entityView.getNode());
            }
        }
        entityViews.removeIf(EntityView::isMarkedForDelete);
    }
}
