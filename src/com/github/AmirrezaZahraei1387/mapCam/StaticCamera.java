package com.github.AmirrezaZahraei1387.mapCam;

import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

/*
defines a camera which does not change in position.
 */
public class StaticCamera implements CameraBase{

    private final Point2D worldAlign;
    private final Point2D windowAlign;
    private final AffineTransform transform;

    public StaticCamera(Point2D worldAlign,
                        Point2D windowAlign,
                        AffineTransform transform){
        this.transform = transform;
        this.windowAlign = windowAlign;
        this.worldAlign = worldAlign;
    }

    @Override
    public Point2D getWorldAlign() {
        return worldAlign;
    }

    @Override
    public Point2D getWindowAlign() {
        return windowAlign;
    }

    @Override
    public AffineTransform getTransform() {
        return transform;
    }
}
