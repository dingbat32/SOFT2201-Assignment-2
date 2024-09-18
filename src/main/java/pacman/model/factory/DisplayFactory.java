package pacman.model.factory;

import pacman.view.info.Display;

import java.util.List;

public interface DisplayFactory {
    List<Display> createDisplays();
}
