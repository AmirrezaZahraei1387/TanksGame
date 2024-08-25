package com.github.AmirrezaZahraei1387.mapCam;


import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class CameraCord {

    private CameraBase camera;
    private Dimension windowDim;

    private final Rectangle2D bounds;

    public CameraCord(){
        bounds = new Rectangle2D.Double();
    }

    public void setCamera(CameraBase camera){
        this.camera = camera;
    }

    public void setWindowDim(Dimension size){
        this.windowDim = size;
    }

    public Dimension getWindowDim(){
        return windowDim;
    }

    public Rectangle2D getBounds(){
        return bounds;
    }

    void setGraphics(Graphics2D g2d){
        if(camera == null || windowDim == null)
            return;

        update_origin();

        g2d.translate(-bounds.getX(), -bounds.getY());

        AffineTransform t = camera.getTransform();
        if(t != null)
            g2d.setTransform(t);
    }

    /*
    checks if the given point in present in the camera
     */
    public boolean contains(double x, double y){
        update_origin();
        return bounds.contains(x, y);
    }

    /*
    updates the origin of the camera in contained in
    the world
     */
    private void update_origin(){
        Point2D wi_al = camera.getWindowAlign();
        Point2D wo_al = camera.getWorldAlign();

        // the start origin of the window in the world
        double o_x = wo_al.getX() - wi_al.getX();
        double o_y = wo_al.getY()  - wi_al.getY();

        bounds.setRect(o_x, o_y, windowDim.width, windowDim.height);
    }
}
