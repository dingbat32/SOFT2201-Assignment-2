package pacman.model.level;

import pacman.model.entity.Renderable;
import pacman.view.info.Observer;

import java.util.List;

public interface Subject {
    void registerObserver(Observer o);
    void removeObserver(Observer o);
    void notifyObservers();
}
