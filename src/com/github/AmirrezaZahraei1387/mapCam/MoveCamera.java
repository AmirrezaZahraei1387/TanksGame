package com.github.AmirrezaZahraei1387.mapCam;

import com.github.AmirrezaZahraei1387.comers.Alignment;
import com.github.AmirrezaZahraei1387.comers.Transformation;

import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

/*
defines a camera which its alignments and
transformation can be dynamically changed.
 */
public class MoveCamera implements CameraBase{

    private final Alignment world;
    private final Alignment window;
    private final Transformation t;

    public MoveCamera(Alignment world, Alignment window, Transformation t){
        this.window = window;
        this.world = world;
        this.t = t;
    }

    @Override
    public Point2D getWorldAlign() {
        return world.getAlignPoint(null);
    }

    @Override
    public Point2D getWindowAlign() {
        return window.getAlignPoint(null);
    }

    @Override
    public AffineTransform getTransform() {
        return t.getTransform();
    }
}
