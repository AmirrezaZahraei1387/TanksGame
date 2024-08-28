package com.gitub.AmirrezaZahraei1387.GameMap;


import com.gitub.AmirrezaZahraei1387.common.MatrixBound;
import com.gitub.AmirrezaZahraei1387.common.Position;
import java.awt.Dimension;


/*
wrap access of listeners to a group of tiles they own.
 */
public class TileGroup {
    private MatrixBound bound;
    private static TileManager tileMan;

    private byte layer;

    TileGroup(MatrixBound bound, byte layer){
        this.bound = bound;
        this.layer = layer;
    }

    public void setTileManager(TileManager m){
        tileMan = m;
    }

    public boolean have(Position p){
        TileStack stack = tileMan.map[bound.pos.i + p.i][bound.pos.j + p.j];

        if(stack != null)
            if(stack.stack.length > layer)
                return true;
        return false;
    }

    public TileGB get(Position p){
        if(have(p)){
            TileStack stack = tileMan.map[bound.pos.i + p.i][bound.pos.j + p.j];
            return stack.stack[layer];
        }
        return null;
    }

    public Dimension getDim(){
        return bound.dim;
    }

    public void repaint(Position p){
        tileMan.againPaint(new MatrixBound(p));
    }

    public void repaint(MatrixBound bound){
        tileMan.againPaint(bound);
    }

    public void repaint(int i, int j, int w, int h){
        tileMan.againPaint(new MatrixBound(i, j, w, h));
    }
}
