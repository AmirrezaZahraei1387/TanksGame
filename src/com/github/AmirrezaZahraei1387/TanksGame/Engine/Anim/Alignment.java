package com.github.AmirrezaZahraei1387.TanksGame.Engine.Anim;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.geom.AffineTransform;

public abstract class Alignment {

    /*
    returns a position in the images that must be aligned by the
    position given by the sprite.
     */
    public abstract Point getPlace(Dimension imageSize);

    public AffineTransform getTransform(){
        return null;
    }

    public final static Alignment CENTRALIZED;
    public final static Alignment TOP_LEFT;
    public final static Alignment TOP_RIGHT;
    public final static Alignment BOTTOM_LEFT;
    public final static Alignment BOTTOM_RIGHT;

    static {
        CENTRALIZED = new Alignment() {
            @Override
            public Point getPlace(Dimension imageSize) {
                return new Point(imageSize.width / 2, imageSize.height / 2);
            }
        };

        TOP_LEFT = new Alignment() {
            @Override
            public Point getPlace(Dimension imageSize) {
                return new Point(0, 0);
            }
        };

        TOP_RIGHT = new Alignment() {
            @Override
            public Point getPlace(Dimension imageSize) {
                return new Point(imageSize.width, 0);
            }
        };

        BOTTOM_LEFT = new Alignment() {
            @Override
            public Point getPlace(Dimension imageSize) {
                return new Point(0, imageSize.height);
            }
        };

        BOTTOM_RIGHT = new Alignment() {
            @Override
            public Point getPlace(Dimension imageSize) {
                return new Point(imageSize.width, imageSize.height);
            }
        };
    }
}

