package pacman.model.entity.staticentity.scoreboard;

import javafx.scene.image.Image;
import pacman.model.entity.dynamic.physics.BoundingBox;
import pacman.model.entity.staticentity.StaticEntityImpl;

public class Life extends StaticEntityImpl implements Scoreable {

    public Life(BoundingBox boundingBox, Layer layer, Image image) {
        super(boundingBox, layer, image);
    }

    @Override
    public void hide() {
        setLayer(Layer.INVISIBLE);
    }

    @Override
    public void show() {
        setLayer(Layer.FOREGROUND);
    }
}
