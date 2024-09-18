package pacman.model;

import pacman.model.level.Subject;
import pacman.view.info.Observer;

import java.util.ArrayList;
import java.util.List;

public class ScoreKeeper implements Subject {
    int startingLives;
    int numLives;
    int score;
    private static ScoreKeeper keeper;
    private final List<Observer> observers;

    private ScoreKeeper() {
        observers = new ArrayList<>();
        this.startingLives = 0;
        numLives = 0;
        score = 0;
    }

    public static ScoreKeeper getInstance() {
        if (keeper == null) {
            keeper = new ScoreKeeper();
        }
        return keeper;
    }

    public void init(int startingLives) {
        this.startingLives = startingLives;
        numLives = startingLives;
        score = 0;
    }

    public int getNumLives() {
        return numLives;
    }

    public int getStartingLives() {
        return startingLives;
    }

    public int getScore() {
        return score;
    }

    public void resetNumLives() {
        this.numLives = startingLives;
        notifyObservers();
    }

    public void decreaseNumLives() {
        numLives--;
        notifyObservers();
    }

    public void addScore(int score) {
        this.score += score;
        notifyObservers();
    }

    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer o : observers) {
            o.update();
        }
    }
}
