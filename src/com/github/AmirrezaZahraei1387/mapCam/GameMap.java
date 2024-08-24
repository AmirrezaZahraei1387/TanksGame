package com.github.AmirrezaZahraei1387.mapCam;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

public class GameMap {

    private CameraBase currCam = null; // current camera
    private final Dimension worldDim;
    private final Dimension windowDim;

    public GameMap(Dimension word, Dimension window){
        this.windowDim = window;
        this.worldDim = word;
    }

    public void setCam(CameraBase cam){
        currCam = cam;
    }

    public Dimension getWorldDim(){
        return worldDim;
    }

    public Dimension getWindowDim(){
        return windowDim;
    }

    /*
    set a Graphics object transform points into
    the camera view.
     */
    public void setGraphics(Graphics2D g2d){

        if(currCam == null)
            return;

        Point2D wi = currCam.getWindowAlign();
        Point2D wo = currCam.getWorldAlign();

        double Ox = wo.getX() - wi.getX();
        double Oy = wo.getY() - wi.getY();

        g2d.translate(Ox, Oy);
        AffineTransform t = currCam.getTransform();

        if(t != null)
            g2d.setTransform(t);
    }
}
