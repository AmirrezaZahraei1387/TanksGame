package com.gitub.AmirrezaZahraei1387.GameMap;

import com.gitub.AmirrezaZahraei1387.Camera.CameraHandler;
import com.gitub.AmirrezaZahraei1387.Camera.CameraHandlerState;
import com.gitub.AmirrezaZahraei1387.common.MatrixBound;
import com.gitub.AmirrezaZahraei1387.common.Position;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import javax.swing.JComponent;
import javax.swing.Timer;


public class TileManager extends JComponent {

    private final TileStack[][] map;
    private final Dimension mapDim; //the width and height of the map.
    private final int tileSize;
    private final CameraHandler cam;
    private CameraHandlerState prev_camState;

    private final Timer timer;

    private MatrixBound currB;

    public TileManager(TileStack[][] map, Dimension mapDim, int tileSize, CameraHandler cam) {
        this.map = map;
        this.mapDim = mapDim;
        this.tileSize = tileSize;
        this.cam = cam;

        prev_camState = new CameraHandlerState();

        currB = null;

        timer = new Timer(0, e -> {
            CameraHandlerState currState = cam.getState();

            if(!currState.equals(prev_camState)){
                prev_camState = currState;
                againRepaint();
            }
        });
    }

    public int getTileSize(){
        return tileSize;
    }

    public CameraHandler getCameraHandler(){
        return cam;
    }

    public Dimension getMapDim(){
        return mapDim;
    }

    @Override
    public void paintComponent(Graphics _g2d){
        Graphics2D g2d = (Graphics2D) _g2d;
        cam.setGraphics(g2d);

        if(currB == null)
            paintMap(g2d);
        else
            paintMap(g2d, currB);
    }

    public void againPaint(MatrixBound bound){
        Rectangle rect = translateBound(bound);
        currB = bound;
        repaint(1, rect.x, rect.y, rect.width, rect.height);
    }

    public void againRepaint(){
        currB = null;
        repaint(1);
    }

    public void start(){
        timer.start();
    }

    public void stop(){
        timer.stop();
    }

    /*
    returns the tile the specified point is located.
    regulates the position if it is out of bounds.
     */
    private Position translate(Point p){
        Position x = new  Position(p.x / tileSize, p.y / tileSize);

        if(x.i < 0)
            x.i = 0;

        if(x.j < 0)
            x.j = 0;

        if(x.i >= mapDim.width)
            x.i = mapDim.width - 1;

        if(x.j >= mapDim.height)
            x.j =  mapDim.height - 1;

        return x;
    }

    private Rectangle translateBound(MatrixBound bound){
        Rectangle rect = new Rectangle();

        rect.x = bound.pos.i * tileSize;
        rect.y = bound.pos.j * tileSize;
        rect.width = bound.dim.width * tileSize;
        rect.height = bound.dim.height * tileSize;

        return rect;
    }

    /*
    paints the specified region of the into the camera space.
    it only draws tiles that are currently contained within the
    bounding rectangle of camera.
     */
    private void paintMap(Graphics2D g2d, MatrixBound bound) {

        Rectangle camBound = cam.getBounds();

        TileStack[][] tiles = inViewTilesCheckAll(bound, camBound);

//        int w = tiles.length;
//        int h = 0;
//        if(tiles.length > 0)
//            h = tiles[0].length;
//
//        System.out.println(w + " " + h);

        for(int i = 0; i < tiles.length; i++)
            for(int j = 0; j < tiles[i].length; j++)
                if(tiles[i][j] != null)
                    for(int k = 0; k < tiles[i][j].stack.length; k++){

                        TileGB tile = tiles[i][j].stack[k];

                        if(tile != null)
                            g2d.drawImage(tile.img,
                                    (bound.pos.i + i) * tileSize,
                                    (bound.pos.j + j) * tileSize,
                                    null);
                    }
    }

    private void paintMap(Graphics2D g2d){

        Rectangle camBound = cam.getBounds();

        Position start = translate(camBound.getLocation());
        TileStack[][] tiles = inViewTilesCam(camBound);

//        int w = tiles.length;
//        int h = 0;
//        if(tiles.length > 0)
//            h = tiles[0].length;
//
//        System.out.println(w + " " + h);

        for(int i = 0; i < tiles.length; i++)
            for(int j = 0; j < tiles[i].length; j++)
                if(tiles[i][j] != null)
                    for(int k = 0; k < tiles[i][j].stack.length; k++){

                        TileGB tile = tiles[i][j].stack[k];

                        if(tile != null)
                            g2d.drawImage(tile.img,
                                    (start.i + i) * tileSize,
                                    (start.j + j) * tileSize,
                                    //tileSize,
                                    //tileSize,
                                    null);
                    }
    }

    private TileStack[][] inViewTilesCheckAll(MatrixBound visBound, Rectangle bound){
        Position s = translate(bound.getLocation());

        int w = (bound.width / tileSize) + 1;
        int h = (bound.height / tileSize) + 1;

        w = Math.min(w + s.i, mapDim.width);
        h = Math.min(h + s.j, mapDim.height);

        TileStack[][] list = new TileStack[visBound.dim.width][visBound.dim.height];

        for(int i = 0; i < visBound.dim.width; ++i){
            for(int j = 0; j < visBound.dim.height; ++j){
                int _i = i + visBound.pos.i;
                int _j = j + visBound.pos.j;

                list[i][j] = null;

                if(map[_i][_j] != null){
                    if(_i >= s.i && _i < w && _j >= s.j && _j < h)
                        list[i][j] = map[_i][_j];
                }

            }
        }

        return list;
    }

    public TileStack[][] inViewTilesCam(Rectangle bound){
        Position s = translate(bound.getLocation());

        int w = (bound.width / tileSize) + 1;
        int h = (bound.height / tileSize) + 1;

        w = Math.min(w + s.i, mapDim.width);
        h = Math.min(h + s.j, mapDim.height);

        TileStack[][] list = new TileStack[w - s.i][h - s.j];

        for(int i = s.i; i < w; i++){
            for(int j = s.j; j < h; j++){
                list[i - s.i][j - s.j] = map[i][j];
            }
        }

        return list;
    }
}
