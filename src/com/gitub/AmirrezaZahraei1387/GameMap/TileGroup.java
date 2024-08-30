package com.gitub.AmirrezaZahraei1387.GameMap;


import com.gitub.AmirrezaZahraei1387.common.MatrixBound;
import com.gitub.AmirrezaZahraei1387.common.Position;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.image.BufferedImage;


/*
wrap access of listeners to a group of tiles they own.
 */
public class TileGroup {
    private final MatrixBound bound;
    private static TileManager tileMan;

    private final byte layer;

    public TileGroup(MatrixBound bound, byte layer){
        this.bound = bound;
        this.layer = layer;
    }

    public static void setTileManager(TileManager m){
        tileMan = m;
    }

    public boolean have(int i, int j){
        TileStack stack = tileMan.map[bound.pos.i + i][bound.pos.j + j];

        if(stack != null)
            return stack.stack.length > layer;
        return false;
    }
    /*
    moves this tileGroup x tiles in the rows
    and y tiles in the columns.
    return true if it was successful, false otherwise.
     */
    public boolean advance(int x, int y){
        int i = bound.pos.i + x;
        int j = bound.pos.j + y;

        Dimension dim = tileMan.getMapDim();

        if(i >= 0 && i < dim.width && j >= 0 && j < dim.height){
            bound.pos.i = i;
            bound.pos.j = j;
            return true;
        }else
            return false;
    }

    public boolean resize(int w, int h){
        Dimension dim = tileMan.getMapDim();
        Position pos = bound.pos;

        if(pos.i + w >= 0 && pos.j + h>= 0)
            if(pos.i + w < dim.width && pos.j + h < dim.height){
                bound.dim.width = w;
                bound.dim.height = h;
            }

        return false;
    }

    public TileGB get(int i, int j){
        if(have(i, j)){
            TileStack stack = tileMan.map[bound.pos.i + i][bound.pos.j + j];
            return stack.stack[layer];
        }
        return null;
    }

    public void set(int i, int j, byte id, int index){
        TileStack stack = tileMan.map[bound.pos.i + i][bound.pos.j + j];

        if(stack != null) {
            TileGB tile = new TileGB(id, index);
            stack.set(tile, layer);
        }

    }

    public Dimension getDim(){
        return bound.dim;
    }

    public void againPaint(Position p){
        p.i += bound.pos.i;
        p.j += bound.pos.j;
        tileMan.againPaint(new MatrixBound(p));
    }

    public void againPaint(MatrixBound p){
        p.pos.i += bound.pos.i;
        p.pos.j += bound.pos.j;
        tileMan.againPaint(p);
    }

    public int getTileSize(){
        return tileMan.getTileSize();
    }

    public void againPaint(int i, int j, int w, int h){
        i += bound.pos.i;
        j += bound.pos.j;
        tileMan.againPaint(new MatrixBound(i, j, w, h));
    }
}
