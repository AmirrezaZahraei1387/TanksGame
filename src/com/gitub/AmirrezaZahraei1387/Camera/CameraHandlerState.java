package com.gitub.AmirrezaZahraei1387.Camera;

import java.awt.Point;
import java.awt.geom.AffineTransform;

public class CameraHandlerState {
    public final Point currP;
    public final AffineTransform transform;

    public CameraHandlerState(Point currP, AffineTransform transform) {
        this.currP = currP;
        this.transform = transform;
    }

    public CameraHandlerState() {
        this.currP = null;
        this.transform = null;
    }


    @Override
    public boolean equals(Object other) {
        if(other instanceof CameraHandlerState o_s) {
            boolean t1;

            if(currP != null)
                t1 = currP.equals(o_s.currP);
            else
                t1 = (o_s.currP == null);

            boolean t2;

            if(transform != null)
                t2 = transform.equals(o_s.transform);
            else
                t2 = (o_s.transform == null);

            return t1 && t2;
        }

        return false;
    }
}
