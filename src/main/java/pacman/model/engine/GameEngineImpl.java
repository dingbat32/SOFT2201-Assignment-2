package pacman.model.engine;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import pacman.model.ScoreKeeper;
import pacman.model.entity.Renderable;
import pacman.model.factory.DisplayFactory;
import pacman.model.factory.DisplayFactoryImpl;
import pacman.model.level.Level;
import pacman.model.level.LevelImpl;
import pacman.model.maze.Maze;
import pacman.model.maze.MazeCreator;
import pacman.view.info.Display;
import pacman.view.info.LivesView;
import pacman.view.info.Observer;
import pacman.view.info.ScoreView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Implementation of GameEngine - responsible for coordinating the Pac-Man model
 */
public class GameEngineImpl implements GameEngine {

    private Level currentLevel;
    private ScoreKeeper scoreKeeper;
    private int numLevels;
    private final int currentLevelNo;
    private Maze maze;
    private JSONArray levelConfigs;
    private List<Display> displays;
    public GameEngineImpl(String configPath) {
        this.currentLevelNo = 0;

        init(new GameConfigurationReader(configPath));
    }

    private void init(GameConfigurationReader gameConfigurationReader) {
        // Set up map
        String mapFile = gameConfigurationReader.getMapFile();
        MazeCreator mazeCreator = new MazeCreator(mapFile);
        this.maze = mazeCreator.createMaze();
        this.maze.setNumLives(gameConfigurationReader.getNumLives());

        // Get level configurations
        this.levelConfigs = gameConfigurationReader.getLevelConfigs();
        this.numLevels = levelConfigs.size();
        if (levelConfigs.isEmpty()) {
            System.exit(0);
        }
        this.scoreKeeper = ScoreKeeper.getInstance();
        scoreKeeper.init(gameConfigurationReader.getNumLives());
        DisplayFactory factory = new DisplayFactoryImpl();
    }

    @Override
    public List<Renderable> getRenderables() {
        List<Renderable> renderables = new ArrayList<>(this.currentLevel.getRenderables());
        return renderables;
    }

    @Override
    public void moveUp() {
        currentLevel.moveUp();
    }

    @Override
    public void moveDown() {
        currentLevel.moveDown();
    }

    @Override
    public void moveLeft() {
        currentLevel.moveLeft();
    }

    @Override
    public void moveRight() {
        currentLevel.moveRight();
    }

    @Override
    public void startGame() {
        startLevel();
    }

    private void startLevel() {
        JSONObject levelConfig = (JSONObject) levelConfigs.get(currentLevelNo);
        // reset renderables to starting state
        maze.reset();
        this.currentLevel = new LevelImpl(levelConfig, maze);
    }

    @Override
    public void tick() {
        currentLevel.tick();
    }

}

