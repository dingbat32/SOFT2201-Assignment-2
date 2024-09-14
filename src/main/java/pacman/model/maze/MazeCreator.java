package pacman.model.maze;

import pacman.model.entity.Renderable;
import pacman.model.entity.dynamic.physics.Vector2D;
import pacman.model.factory.RenderableFactoryRegistry;
import pacman.model.factory.RenderableFactoryRegistryImpl;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static java.lang.System.exit;

/**
 * Responsible for creating renderables and storing it in the Maze
 */
public class MazeCreator {

    private final String fileName;
    public static final int RESIZING_FACTOR = 16;
    RenderableFactoryRegistry factoryRegistry;
    public MazeCreator(String fileName){
        this.fileName = fileName;
        factoryRegistry = new RenderableFactoryRegistryImpl();
    }

    public Maze createMaze(){
        File f = new File(this.fileName);
        Maze maze =  Maze.getInstance();
        maze.reset();

        try {
            Scanner scanner = new Scanner(f);

            int y = 0;

            while (scanner.hasNextLine()){

                String line = scanner.nextLine();
                char[] row = line.toCharArray();

                for (int x = 0; x < row.length; x++){
                    if (row[x] != '0'){
                        Renderable renderable = factoryRegistry.createRenderable(row[x], x, y);
                        maze.addRenderable(renderable, row[x], x, y);
                    }
                }
                y += 1;
            }

            scanner.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("No maze file was found.");
            exit(0);
        } catch (Exception e){
            System.out.println("Error");
            exit(0);
        }

        return maze;
    }
}
