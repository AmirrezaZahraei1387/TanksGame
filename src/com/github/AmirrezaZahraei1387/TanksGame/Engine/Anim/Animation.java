package com.github.AmirrezaZahraei1387.TanksGame.Engine.Anim;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Animation {
    private final BufferedImage[] images;
    private final Alignment alignment;
    private final long timePerFrame;

    public Animation(BufferedImage[] images, Alignment alignment, long timePerFrame){
        this.images = images;
        this.timePerFrame = timePerFrame;
        this.alignment = alignment;
    }

    public BufferedImage get(int i){
        return images[i];
    }

    public int size(){
        return images.length;
    }

    public Point getAlignment(int i){
        return alignment.getPlace(new Dimension(images[i].getWidth(), images[i].getHeight()));
    }

    public long getTimePerFrame(){
        return timePerFrame;
    }
}
