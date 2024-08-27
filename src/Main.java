import com.gitub.AmirrezaZahraei1387.Camera.CameraHandler;
import com.gitub.AmirrezaZahraei1387.Camera.StaticCamera;
import com.gitub.AmirrezaZahraei1387.GameMap.TileGB;
import com.gitub.AmirrezaZahraei1387.GameMap.TileManager;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

class Grass implements TileGB{

    static private BufferedImage img;
    static private Dimension dim;

    public static void setImg(BufferedImage img, Dimension dim) {
        Grass.img = img;
        Grass.dim = dim;
    }

    @Override
    public void render(Graphics2D g2d) {
        g2d.drawImage(img,0,0, dim.width, dim.height,null);
    }

    @Override
    public int getId() {
        return 0;
    }

    @Override
    public boolean isCollisionObj() {
        return false;
    }

    @Override
    public byte getStatus() {
        return ACTIVE;
    }
}

class Water implements TileGB{

    static private BufferedImage img;
    static private Dimension dim;

    public static void setImg(BufferedImage img, Dimension dim) {
        Water.img = img;
        Water.dim = dim;
    }

    @Override
    public void render(Graphics2D g2d) {
        g2d.drawImage(img,0,0, dim.width, dim.height,null);
    }

    @Override
    public int getId() {
        return 0;
    }

    @Override
    public boolean isCollisionObj() {
        return false;
    }

    @Override
    public byte getStatus() {
        return ACTIVE;
    }
}

public class Main {

    public static void main(String[] args) throws IOException {

        Dimension screenSize = new Dimension(600, 600);
        int tileSize = 60;
        Dimension worldSize = new Dimension(10, 10);


        Grass.setImg(ImageIO.read(new File("data/grass.jpg")), new Dimension(tileSize, tileSize));
        TileManager.TileEntry eg = new TileManager.TileEntry(new Grass(), null);

        Water.setImg(ImageIO.read(new File("data/water.png")), new Dimension(tileSize, tileSize));
        TileManager.TileEntry ew = new TileManager.TileEntry(new Water(), null);

        TileManager.TileEntry[][] entries = new TileManager.TileEntry[][]{
                {ew, ew, ew, ew, ew, ew, ew, ew, ew, ew},
                {ew, ew, ew, ew, ew, ew, ew, ew, ew, ew},
                {ew, ew, eg, eg, eg, eg, eg, eg, ew, ew},
                {ew, ew, eg, eg, eg, eg, eg, eg, ew, ew},
                {ew, ew, eg, eg, eg, eg, eg, eg, ew, ew},
                {ew, ew, eg, eg, eg, eg, eg, eg, ew, ew},
                {ew, ew, eg, eg, eg, eg, eg, eg, ew, ew},
                {ew, ew, eg, eg, eg, eg, eg, eg, ew, ew},
                {ew, ew, ew, ew, ew, ew, ew, ew, ew, ew},
                {ew, ew, ew, ew, ew, ew, ew, ew, ew, ew},

        };

        CameraHandler cameraHandler = new CameraHandler();
        cameraHandler.setViewSize(screenSize);
        cameraHandler.setCurrCamera(new StaticCamera(null, new Point(100, 100)));

        TileManager tileManager = new TileManager(entries, cameraHandler, tileSize, worldSize);

        JFrame frame = new JFrame();
        frame.setPreferredSize(screenSize);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        frame.add(tileManager);
        frame.pack();
        frame.setVisible(true);
    }

}
