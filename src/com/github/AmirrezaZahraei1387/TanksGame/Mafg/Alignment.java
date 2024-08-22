package com.github.AmirrezaZahraei1387.TanksGame.Mafg;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.geom.AffineTransform;

public interface Alignment {

    /*
    Returns the alignment point.
    Note: the point will be within the object space.
    If the dimension of the object is held by the
    caller, then it will be passed to the size parameter.
    Otherwise, a null would be provided.
     */
    Point getAlignPoint(Dimension size);

    /*
    returns the affine transform for the intended object.
     */
    default AffineTransform getTransform() {
        return null;
    }

    static final Alignment CENTRALIZED = new Alignment() {
        @Override
        public Point getAlignPoint(Dimension size) {
            return new Point(size.width / 2, size.height / 2);
        }
    };

}
