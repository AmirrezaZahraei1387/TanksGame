package com.github.AmirrezaZahraei1387.comers;

import java.awt.geom.AffineTransform;
import java.awt.geom.Dimension2D;
import java.awt.geom.Point2D;

/*
defines the alignment of an
object when is being aligned with another object
in some context.
 */
public interface Alignment {

    /*
    returns a point for the alignment of the caller
    object. dim is an optional parameter that represent
    the dimension of the object.
    In case it is not available for the caller it is null.
     */
    Point2D getAlignPoint(Dimension2D dim);
}
