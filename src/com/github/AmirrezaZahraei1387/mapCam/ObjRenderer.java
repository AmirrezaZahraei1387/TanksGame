package com.github.AmirrezaZahraei1387.mapCam;

import javax.swing.JComponent;
import java.awt.Graphics;
import java.awt.Graphics2D;

/*
the base class for any object that
renders graphics.
make sure to override paintObj to handle rendering.
 */
public abstract class ObjRenderer extends JComponent {

    protected static CameraCord gameMap;

    public static void setGameMap(CameraCord map){
        gameMap = map;
    }

    @Override
    public void paintComponent(Graphics _g2d){
        super.paintComponent(_g2d);
        Graphics2D g2d = (Graphics2D) (_g2d);

        gameMap.setGraphics(g2d);
        paintObj(g2d);
    }

    protected abstract void paintObj(Graphics2D g2d);
}
