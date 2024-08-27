package com.gitub.AmirrezaZahraei1387.Camera;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;

public class CameraHandler {
    private Camera currCamera;

    private Dimension viewSize;
    private final Dimension worldSize;

    public CameraHandler(Dimension worldSize){
        this.worldSize = worldSize;

        this.currCamera = null;
        this.viewSize = null;

    }

    public void setCurrCamera(Camera currCamera) {
        this.currCamera = currCamera;
    }

    public Dimension getViewSize() {
        return viewSize;
    }

    public void setViewSize(Dimension viewSize) {
        this.viewSize = viewSize;
    }

    public Dimension getWorldSize() {
        return worldSize;
    }

    public CameraHandlerState getState(){
        if(currCamera == null)
            return new CameraHandlerState(null, null);
        return new CameraHandlerState(currCamera.getWorldPos(), currCamera.getTransform());
    }

    public CameraHandlerState setGraphics(Graphics2D g2d) {
        if(currCamera == null)
            return new CameraHandlerState(null, null);

        Point p = currCamera.getWorldPos();
        g2d.translate(-p.x, -p.y);

        AffineTransform t = currCamera.getTransform();

        if(t != null)
            g2d.setTransform(t);

        return new CameraHandlerState(p, t);
    }

}
