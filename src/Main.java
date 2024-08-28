
import com.gitub.AmirrezaZahraei1387.Camera.CameraHandler;
import com.gitub.AmirrezaZahraei1387.Camera.StaticCamera;
import com.gitub.AmirrezaZahraei1387.GameMap.TileGB;
import com.gitub.AmirrezaZahraei1387.GameMap.TileGroup;
import com.gitub.AmirrezaZahraei1387.GameMap.TileManager;
import com.gitub.AmirrezaZahraei1387.GameMap.TileStack;
import com.gitub.AmirrezaZahraei1387.TestG;
import com.gitub.AmirrezaZahraei1387.common.MatrixBound;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.Timer;
import javax.swing.WindowConstants;


public class Main {
    public static void main(String[] args) throws IOException {

        // size and dimension information
        Dimension worldDim = new Dimension(10, 10);
        int tileSize = 100;
        Dimension screenSize = new Dimension(500, 500);
        Dimension worldSize = new Dimension(worldDim.width * tileSize, worldDim.height * tileSize);

        // camera establishment
        CameraHandler cam = new CameraHandler(worldSize);
        cam.setViewSize(screenSize);
        cam.setCurrCamera(new StaticCamera(null, new Point(0, 0)));

        // making the tiles
        BufferedImage _grass = ImageIO.read(new File("data/grass.jpg"));
        BufferedImage _fire = ImageIO.read(new File("data/Explosion_D.png"));

        TileStack[][] map = new TileStack[worldDim.width][worldDim.height];

        for(int i = 0; i < map.length; ++i)
            for(int j = 0; j < map[i].length; ++j)
                map[i][j] = new TileStack(new TileGB[]{null, null});

        TileManager tileManager = new TileManager(map, worldDim, tileSize, cam);

        JFrame frame = new JFrame();
        frame.setPreferredSize(screenSize);
        frame.add(tileManager);
        frame.pack();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);

        tileManager.start();

        new Timer(5, e -> cam.setViewSize(frame.getSize())).start();

        TileGroup.setTileManager(tileManager);

        TestG testG = new TestG();
        testG.group = new TileGroup(new MatrixBound(0, 0, 10, 10), (byte) 0);
        TestG.setImg(_grass);

        testG.draw();
    }
}
