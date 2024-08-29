package com.gitub.AmirrezaZahraei1387.common;

import java.awt.geom.AffineTransform;

public interface Transformation {

    /*
    returns a transformation needed by the caller at one
    point. if no affine transformation is needed at that moment
    return null.
     */
    default AffineTransform getTransform(){return null;}

}
