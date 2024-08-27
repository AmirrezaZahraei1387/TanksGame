package com.gitub.AmirrezaZahraei1387.GameMap;

import com.gitub.AmirrezaZahraei1387.Camera.CameraHandler;
import com.gitub.AmirrezaZahraei1387.Camera.CameraHandlerState;

import javax.swing.JComponent;
import javax.swing.Timer;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class TileManager extends JComponent{

    /*
    each entry consists of two layers.
     */
    public static class TileEntry{
        public TileGB[] stack;

        public TileEntry(TileGB groundLayer, TileGB trapLayer){
            stack[0] = groundLayer;
            stack[1] = trapLayer;
        }
    }

    private final TileEntry[][] map;
    private final CameraHandler cManager;
    private CameraHandlerState prev_cState;
    private int tileSize;
    private Timer timer;

    public TileManager(TileEntry[][] map, CameraHandler cManager, int tileSize){
        this.map = map;
        this.cManager = cManager;
        this.tileSize = tileSize;
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

        if(x.i >= cManager.getWorldSize().width)
            x.i = cManager.getWorldSize().width - 1;

        if(x.j >= cManager.getWorldSize().height)
            x.j = cManager.getWorldSize().height - 1;

        return x;
    }


    @Override
    public void paintComponent(Graphics _g2d){
        Graphics2D g2d = (Graphics2D) _g2d;

        // sets this graphics object to draw to view
        // of the current camera
        cManager.setGraphics(g2d);

        Rectangle bound = cManager.getBounds();

        ArrayList<TileEntry> tiles = getInBoundTiles(bound);
        for(TileEntry tileStack : tiles){
            for(int k = 0; k < tileStack.stack.length; ++k){
                if(tileStack.stack[k] != null){

                    int status = tileStack.stack[k].getStatus();

                    if(status == TileGB.ACTIVE) {
                        tileStack.stack[k].render(g2d);
                    }else if(status == TileGB.DESTROYED){
                        tileStack.stack[k] = null;
                    }

                }
            }
        }
    }

    /*
    returns the tiles that are in the given bound.
    Note that bound is in terms of pixels and need translation.
    */
    private ArrayList<TileEntry> getInBoundTiles(Rectangle bound){

        Position s = translatePos(bound.getLocation());
        int w = (bound.width / tileSize) + 2;
        int h = (bound.height / tileSize) + 2;

        w = Math.min(w + s.i, cManager.getWorldSize().width);
        h = Math.min(h + s.j, cManager.getWorldSize().height);

        ArrayList<TileEntry> list = new ArrayList<>();

        for(int i = s.i; i < w; i++){
            for(int j = s.j; j < h; j++){
                list.add(map[i][j]);
            }
        }

        return list;
    }
}
