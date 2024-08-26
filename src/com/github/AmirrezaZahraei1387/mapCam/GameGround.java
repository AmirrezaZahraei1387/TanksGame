package com.github.AmirrezaZahraei1387.mapCam;

import javax.swing.Timer;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/*
displays the game's ground as well as provide
a way to find objects that currently within the camera for a better
performance in drawing.

The map is divided into MxN squares of fixed size.
each can have form of ground being displayed.
 */
public class GameGround extends ObjRenderer{

    private final Dimension tileSize; // the size of each square/tile.
    private final Dimension mapSize; // the total size of the map.

    private final BufferedImage[] groundImages; // texture of the ground.
    // assumed to have the tileSize.

    private final BufferedImage background;

    private final int[][] map; // the map have the dimention mapSize and each cell
    // either reference an image index in groundImages or is -1 meaning empty.

    private Timer timer;

    public GameGround(Dimension tileSize, Dimension mapSize,
                      BufferedImage[] groundImages, BufferedImage backGround, int[][] map) {
        this.tileSize = tileSize;
        this.mapSize = mapSize;
        this.groundImages = groundImages;
        this.map = map;

        timer = new Timer(4, e -> {
            if(cameraCord.HasChanged()){
                repaint(1);
            }
        });

        this.background = backGround;
    }


    @Override
    protected void paintObj(Graphics2D g2d, AffineTransform prev) {
        cameraCord.setCurrGrounds(getInViewGrounds());

        AffineTransform curr = g2d.getTransform();

        g2d.setTransform(prev);
        g2d.drawImage(background, 0, 0,
                cameraCord.getWindowDim().width,
                cameraCord.getWindowDim().height,
                null);

        g2d.setTransform(curr);
        for(Integer x: cameraCord.getCurrGrounds()){
            Point r = translate(x);
            g2d.drawImage(groundImages[map[r.x][r.y]],
                    r.x * tileSize.width,
                    r.y * tileSize.height,
                    tileSize.width,
                    tileSize.height, null);
        }

    }

    public Point translate(int n) {
        return new Point(n / mapSize.width, n % mapSize.width);
    }

    public int translate(Point p){
        return p.x * mapSize.width + p.y;
    }

    public int translate(int i, int j){
        return i * mapSize.width + j;
    }

    public Point getPos(Point2D p){
        return new Point((int) (p.getX() / tileSize.width), (int) (p.getY() / tileSize.getWidth()));
    }

    public Point getPos(double x, double y){
        return new Point((int) (x / tileSize.width), (int) (y / tileSize.getWidth()));
    }

    public ArrayList<Integer> getInViewGrounds() {

        Rectangle2D bounds = cameraCord.getBounds();

        Point beginPoint = getPos(bounds.getX(), bounds.getY());

        if (beginPoint.x < 0) {
            beginPoint.x = 0;
        }

        if (beginPoint.y <= 0) {
            beginPoint.y = 0;
        }

            ArrayList<Integer> list = new ArrayList<>();

            int i_b = bounds.getWidth() / tileSize.width + 2 >= mapSize.width ?
                    mapSize.width :
                    (int) (bounds.getWidth() / tileSize.width + 2);

            int j_b = bounds.getHeight() / tileSize.height + 2 >= mapSize.height ?
                    mapSize.height :
                    (int) (bounds.getHeight() / tileSize.height + 2);

            for (int i = beginPoint.x; i < i_b; ++i) {
                for (int j = beginPoint.y; j < j_b; ++j) {
                    if (map[i][j] != -1)
                        list.add(translate(i, j));
                }
            }

        return list;
    }

    public void start(){
        timer.start();
    }

    public void stop(){
        timer.stop();
    }
}