package pacman.model.level;

import pacman.model.entity.Renderable;
import pacman.view.info.Observer;

import java.util.ArrayList;
import java.util.List;

public class LevelSubject implements Subject {
    private final List<Observer> observers;
    private int numLives;
    private int score;
    public LevelSubject() {
        observers = new ArrayList<>();
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
