package com.gitub.AmirrezaZahraei1387.common;

import java.awt.Dimension;
import java.awt.Point;

public interface Alignment {

    /*
    provides the alignment of an object for the caller.
    if the object is not visible for the implementor its size
    is passed as a parameter, otherwise a null will be provided.
     */
    Point getAlignment(int w, int h);
}
