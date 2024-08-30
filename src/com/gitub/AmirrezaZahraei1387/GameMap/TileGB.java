package com.gitub.AmirrezaZahraei1387.GameMap;

import java.awt.image.BufferedImage;


/*
one tile is represented through a reference to
a BufferedImage
 */
public class TileGB {

    private byte id;
    private int index;

    public TileGB(byte id, int index){
        this.id = id;
        this.index = index;
    }

    public byte getId() {
        return id;
    }

    public int getIndex() {
        return index;
    }

    public void setId(byte id, int index) {
        this.id = id;
        this.index = index;
    }

    public void makeReference(){
        this.index = -this.index;
    }

    public void clear(){
        this.index = 0;
    }

    public boolean isReference(){
        return this.index < 0;
    }

    public boolean isCleared(){
        return this.index == 0;
    }

    public boolean isOk(){
        return this.index > 0;
    }
}
