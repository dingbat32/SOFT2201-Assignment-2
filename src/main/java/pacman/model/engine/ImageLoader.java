package pacman.model.engine;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ImageLoader {
    private String path;
    private static ImageLoader uniqueInstance;
    private ImageLoader(String path) {
        this.path = path;
    }

    public static ImageLoader getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new ImageLoader("src/main/resources/maze/");
        }
        return uniqueInstance;
    }

    public Image loadImage(String imageName) {
        Image image = null;
        try {
            image = new Image(new FileInputStream(path + imageName));
        } catch (FileNotFoundException e) {
            System.out.println(path + imageName + " File not found");
            System.exit(0);
        }
        return image;
    }
}
