package pacman.model.engine;

import javafx.scene.image.Image;

public class ImageLoader {
    private String path;
    private static ImageLoader uniqueInstance;
    private ImageLoader(String path) {
        this.path = path;
    }

    public static ImageLoader getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new ImageLoader("src/main/resources/");
        }
        return uniqueInstance;
    }

    public Image loadImage(String imageName) {
        return new Image(path + imageName);
    }
}
