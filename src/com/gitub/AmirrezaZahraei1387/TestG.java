package com.gitub.AmirrezaZahraei1387;

import com.gitub.AmirrezaZahraei1387.GameMap.TileGroup;
import java.awt.Dimension;
import java.awt.image.BufferedImage;

public class TestG {
    public TileGroup group;
    public static BufferedImage img;

    public static void setImg(BufferedImage _img){
        img = _img;
    }

    public void draw(){

        Dimension d = group.getDim();

        for(int i = 0; i < d.width; ++i)
            for(int j = 0; j < d.height; ++j)
                group.set(i, j, img);

        group.repaint(0, 0, d.width, d.height);
    }

}
