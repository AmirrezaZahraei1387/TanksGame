package com.gitub.AmirrezaZahraei1387.GameMap;

import com.gitub.AmirrezaZahraei1387.Camera.CameraHandler;
import com.gitub.AmirrezaZahraei1387.Camera.CameraHandlerState;

import javax.swing.JComponent;
import javax.swing.Timer;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;


public class TileManager extends JComponent{

    /*
    each entry consists of two layers.
     */
    public static class TileEntry{
        public TileGB[] stack;

        public TileEntry(TileGB groundLayer, TileGB trapLayer){

            stack = new TileGB[]{groundLayer, trapLayer};
        }
    }

    private final TileEntry[][] map;
    private final CameraHandler cManager;
    private CameraHandlerState prev_cState;
    private final int tileSize;
    private final Dimension worldSize;
    private Timer timer;

    public TileManager(TileEntry[][] map, CameraHandler cManager, int tileSize, Dimension worldSize){
        this.map = map;
        this.cManager = cManager;
        this.tileSize = tileSize;
        this.worldSize = worldSize;
        prev_cState = new CameraHandlerState();

        timer = new Timer(100, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                CameraHandlerState new_cState = new CameraHandlerState();

                if(!new_cState.equals(prev_cState)){
                    prev_cState = new_cState;
                    repaint(1);
                }
            }
        });
    }

    /*
    translates a point from graphics space to the tile space
     */
    public Position translatePos(Point p){
        Position x = new  Position(p.x / tileSize, p.y / tileSize);

        if(x.i < 0)
            x.i = 0;

        if(x.j < 0)
            x.j = 0;

        if(x.i >= worldSize.width)
            x.i = worldSize.width - 1;

        if(x.j >= worldSize.height)
            x.j = worldSize.height - 1;

        return x;
    }


    @Override
    public void paintComponent(Graphics _g2d){
        Graphics2D g2d = (Graphics2D) _g2d;

        // sets this graphics object to draw to view
        // of the current camera
        cManager.setGraphics(g2d);

        Rectangle bound = cManager.getBounds();

        Position start = translatePos(bound.getLocation());
        TileEntry[][] tiles = getInBoundTiles(bound);

        for(int i = 0; i < tiles.length; i++){
            for(int j = 0; j < tiles[i].length; j++){
                if(tiles[i][j] != null){
                    for(int k = 0; k < tiles[i][j].stack.length; k++){

                        TileGB tile = tiles[i][j].stack[k];

                        if(tile != null){
                            if(tile.getStatus() == TileGB.ACTIVE){
                                AffineTransform prev = g2d.getTransform();
                                g2d.translate((start.i + i) * tileSize, (start.j + j) * tileSize);
                                tile.render(g2d);
                                g2d.setTransform(prev);
                            }else if(tile.getStatus() == TileGB.DESTROYED){
                                tiles[i][j].stack[k] = null;
                            }
                        }

                    }
                }
            }
        }
    }

    /*
    returns the tiles that are in the given bound.
    Note that bound is in terms of pixels and need translation.
    */
    private TileEntry[][] getInBoundTiles(Rectangle bound){

        Position s = translatePos(bound.getLocation());
        int w = (bound.width / tileSize) + 2;
        int h = (bound.height / tileSize) + 2;

        w = Math.min(w + s.i, worldSize.width);
        h = Math.min(h + s.j, worldSize.height);

        TileEntry[][] list = new TileEntry[w - s.i][h - s.j];

        for(int i = s.i; i < w; i++){
            for(int j = s.j; j < h; j++){
                list[i - s.i][j - s.j] = map[i][j];
            }
        }

        return list;
    }
}
