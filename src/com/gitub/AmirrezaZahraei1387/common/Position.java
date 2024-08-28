package com.gitub.AmirrezaZahraei1387.common;

import java.awt.Dimension;

/*
represents a position in the
tile map.
 */
public class Position {

    public int i;
    public int j;

    public Position(int i, int j) {
        this.i = i;
        this.j = j;
    }

    public Position(Position other){
        this.i = other.i;
        this.j = other.j;
    }

    public Position() {
        this.i = 0;
        this.j = 0;
    }

    public int flatten(Dimension dim){
        return this.i * dim.width + this.j;
    }

    public static Position openPos(int k, Dimension dim){
        return new Position(k / dim.width, k % dim.width);
    }

    @Override
    public String toString() {
        return "Position(%d, %d)".formatted(i, j);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Position p)
            return p.i == i && p.j == j;
        return false;
    }
}
