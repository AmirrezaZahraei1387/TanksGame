package com.gitub.AmirrezaZahraei1387.GameMap;


import com.gitub.AmirrezaZahraei1387.common.MatrixBound;
import com.gitub.AmirrezaZahraei1387.common.Position;
import java.awt.Dimension;
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
            if(stack.stack.length > layer)
                return true;
        return false;
    }


    public TileGB get(int i, int j){
        if(have(i, j)){
            TileStack stack = tileMan.map[bound.pos.i + i][bound.pos.j + j];
            return stack.stack[layer];
        }
        return null;
    }


    public void set(int i, int j, BufferedImage img){
        if(have(i, j)){
            TileStack stack = tileMan.map[bound.pos.i + i][bound.pos.j + j];
            stack.stack[layer] = new TileGB(img);
        }
    }

    public Dimension getDim(){
        return bound.dim;
    }

    public void repaint(Position p){
        p.i += bound.pos.i;
        p.j += bound.pos.j;
        tileMan.againPaint(new MatrixBound(p));
    }

    public void repaint(MatrixBound p){
        p.pos.i += bound.pos.i;
        p.pos.j += bound.pos.j;
        tileMan.againPaint(p);
    }

    public void repaint(int i, int j, int w, int h){
        i += bound.pos.i;
        j += bound.pos.j;
        tileMan.againPaint(new MatrixBound(i, j, w, h));
    }
}
