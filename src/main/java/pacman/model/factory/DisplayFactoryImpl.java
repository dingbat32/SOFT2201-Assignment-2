package pacman.model.factory;

import pacman.view.info.Display;
import pacman.view.info.LivesView;

import java.util.ArrayList;
import java.util.List;

public class DisplayFactoryImpl implements DisplayFactory {
    @Override
    public List<Display> createDisplays() {
        List<Display> displays = new ArrayList<Display>();
        displays.add(new LivesView());
        return displays;
    }
}
