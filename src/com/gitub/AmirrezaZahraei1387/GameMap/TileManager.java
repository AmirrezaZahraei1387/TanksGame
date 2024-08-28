package com.gitub.AmirrezaZahraei1387.GameMap;

import com.gitub.AmirrezaZahraei1387.Camera.CameraHandler;
import com.gitub.AmirrezaZahraei1387.Camera.CameraHandlerState;
import com.gitub.AmirrezaZahraei1387.GameMap.Objects.ObjectIds;

import javax.swing.JComponent;
import javax.swing.Timer;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;


public class TileManager extends JComponent{

    /*
    each entry consists of two layers.
     */
    public static class TileEntry{
        public TileGB[] stack;

        public TileEntry(TileGB[] stack){
            if(stack == null)
                throw new NullPointerException("the Tile Stack can not be null.");

            this.stack = stack;
        }
    }

    private final TileEntry[][] map;
    private final CameraHandler cManager;
    private CameraHandlerState prev_cState;
    private final int tileSize;
    private final Dimension worldSize;
    private final Timer timer;

    public TileManager(TileEntry[][] map, CameraHandler cManager, int tileSize, Dimension worldSize){
        this.map = map;
        this.cManager = cManager;
        this.tileSize = tileSize;
        this.worldSize = worldSize;
        prev_cState = new CameraHandlerState();

        timer = new Timer(100, e -> {
            CameraHandlerState new_cState = cManager.getState();

            if(!new_cState.equals(prev_cState)){
                prev_cState = new_cState;
                repaint(1);
            }
        });
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
            for(int j = 0; j < tiles.length; j++){
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
                                removeTile(i, j, k);
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

        int w = (bound.width / tileSize) + 1;
        int h = (bound.height / tileSize) + 1;

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

    /*
    translates a point from graphics space to the tile space
    */
    private Position translatePos(Point p){
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

    /*
    removes a tile in the specified position and layer.
     */
    private void removeTile(int _i, int _j, int layer){

        int i = _i;
        int j = _j + 1;

        while(true){
            for(;j < map[i].length; ++j){
                if(map[i][j] != null) {
                    TileGB t = map[i][j].stack[layer];
                    if (t != null) {
                        if (t.getId() == ObjectIds.REFERENCE) {
                            Position p = Position.openPos(t.getReference(), worldSize);
                            int k = t.getLayer();

                            if (p.i != i || p.j != j || k != layer)
                                break;
                            else
                                map[p.i][p.j].stack[k] = null;
                        }else
                            break;
                    }else
                        break;
                }else
                    break;
            }

            j = _j;
            ++i;

            if(i >= map.length)
                break;
            else{
                if(map[i][j] != null) {
                    TileGB t = map[i][j].stack[layer];
                    if (t != null) {
                        if (t.getId() == ObjectIds.REFERENCE) {
                            Position p = Position.openPos(t.getReference(), worldSize);
                            int k = t.getLayer();

                            if (p.i != i || p.j != j || k != layer)
                                break;
                            else
                                map[p.i][p.j].stack[k] = null;
                        }else
                            break;
                    }else
                        break;
                }else
                    break;
            }

            j += 1;
        }

        map[_i][_j].stack[layer] = null;

    }
}
