package com.gitub.AmirrezaZahraei1387.GameMap;

import java.awt.image.BufferedImage;


/*
one tile is represented through a reference to
a BufferedImage
 */
public class TileGB {
    public BufferedImage img;
    public TileListener listener;

    public TileGB(BufferedImage img, TileListener listener){
        this.img = img;
        this.listener = listener;
    }

    public TileGB(BufferedImage img){
        this(img, null);
    }
}
