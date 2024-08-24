package com.github.AmirrezaZahraei1387.mapCam;

import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

public interface CameraBase {

    /*
    returns the concentrate point of
    the camera
     */
    Point2D getWorldAlign();
    /*
    returns the point in window that the
    concentrate point must alignTo.
     */
    Point2D getWindowAlign();
    /*
    an optional transformation applied to
    customize the camera's view.
    returns null if there is no needed transformation.
     */
    AffineTransform getTransform();
}
