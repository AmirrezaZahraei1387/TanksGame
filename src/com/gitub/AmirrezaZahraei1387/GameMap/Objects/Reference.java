package com.gitub.AmirrezaZahraei1387.GameMap.Objects;

import com.gitub.AmirrezaZahraei1387.GameMap.TileGB;

import java.awt.Graphics2D;
import java.awt.Point;

public class Reference implements TileGB {

    private int pos;
    private byte layer;

    public Reference(int pos, byte layer){
        this.pos = pos;
        this.layer = layer;
    }

    @Override
    public int getId() {
        return ObjectIds.REFERENCE;
    }

    @Override
    public boolean isCollisionObj() {
        return false;
    }

    @Override
    public byte getStatus() {
        return TileGB.REFERENCE;
    }

    @Override
    public int getReference() {
        return pos;
    }

    @Override
    public int getLayer() {
        return layer;
    }
}
