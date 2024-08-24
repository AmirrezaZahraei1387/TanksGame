package com.github.AmirrezaZahraei1387.comers;

import java.awt.geom.AffineTransform;

/*
defines the transformation of the caller
object based on the preferences of the implementor.
 */
public interface Transformation {
    AffineTransform getTransform();
}