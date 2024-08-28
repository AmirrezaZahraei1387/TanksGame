package com.gitub.AmirrezaZahraei1387.common;

import java.awt.Dimension;

/*
represents a specific bound within a
2d matrix.
 */
public class MatrixBound {
    public Position pos;
    public Dimension dim;

    public MatrixBound(Position pos, Dimension dim){
        this.pos = pos;
        this.dim = dim;
    }

    public MatrixBound(){
        this.pos = new Position();
        this.dim = new Dimension();
    }

    public MatrixBound(int i, int j, int w, int h){
        this.dim = new Dimension(w, h);
        this.pos = new Position(i, j);
    }

    public MatrixBound(Position pos){
        this.pos = pos;
        this.dim = new Dimension(1, 1);
    }

    public MatrixBound(MatrixBound other){
        this.pos = new Position(other.pos);
        this.dim = new Dimension(other.dim);
    }

    @Override
    public String toString(){
        return "MatrixBound(i=%d, j=%d, w=%d, h=%d)".formatted(pos.i, pos.j, dim.width, dim.height);
    }

    @Override
    public boolean equals(Object other){
        if(other instanceof MatrixBound o)
            return o.pos.equals(pos) && o.dim.equals(dim);
        return false;
    }
}
