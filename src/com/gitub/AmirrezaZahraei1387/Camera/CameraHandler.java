package com.gitub.AmirrezaZahraei1387.Camera;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;

public class CameraHandler {
    private Camera currCamera;

    private Dimension viewSize;

    public CameraHandler(){

        this.currCamera = new StaticCamera(null, new Point(0, 0));
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

    public CameraHandlerState getState(){
        return new CameraHandlerState(currCamera.getWorldPos(), currCamera.getTransform());
    }

    public Rectangle getBounds(){
        Point p = currCamera.getWorldPos();
        return new Rectangle(p.x, p.y, viewSize.width, viewSize.height);
    }

    public CameraHandlerState setGraphics(Graphics2D g2d) {
        Point p = currCamera.getWorldPos();
        g2d.translate(-p.x, -p.y);

        AffineTransform t = currCamera.getTransform();

        if(t != null)
            g2d.setTransform(t);

        return new CameraHandlerState(p, t);
    }

}
