package com.github.AmirrezaZahraei1387.TanksGame.Mafg;

import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;

/*
The points of a rectangle and the rectangle itself are
packed together. The points do not necessarily match with
the stored rectangle since the rectangle might be transformed
geometrically and the points merely store the result.
 */
public class RectRound {

    private int which; // indicates the index of this rect in the original array
    private Polygon trect;
    public final Rectangle rect;


    public RectRound(Rectangle rect, int which){
        this.rect = new Rectangle(rect);

        trect = new Polygon();

        trect.addPoint(rect.x, rect.y + rect.height);
        trect.addPoint(rect.x, rect.y);
        trect.addPoint(rect.x + rect.width, rect.y);
        trect.addPoint(rect.x + rect.width, rect.y + rect.height);

        this.which = which;
    }


    public Point get(int i){
        return new Point(trect.xpoints[i], trect.ypoints[i]);
    }

    public int getWhich(){
        return which;
    }

    public boolean intersects(RectRound other){
        return trect.contains(other.get(0)) ||
               trect.contains(other.get(1)) ||
               trect.contains(other.get(2)) ||
               trect.contains(other.get(3));
    }

    public void update(AffineTransform t){
        trect = new Polygon();

        Point2D p0 = new Point2D.Double();
        t.transform(new Point2D.Double(rect.x, rect.y + rect.height), p0);
        Point2D p1 = new Point2D.Double();
        t.transform(new Point2D.Double(rect.x, rect.y), p1);
        Point2D p2 = new Point2D.Double();
        t.transform(new Point2D.Double(rect.x + rect.width, rect.y), p2);
        Point2D p3 = new Point2D.Double();
        t.transform(new Point2D.Double(rect.x + rect.width, rect.y + rect.height), p3);

        trect.addPoint((int) p0.getX(), (int) p0.getY());
        trect.addPoint((int) p1.getX(), (int) p1.getY());
        trect.addPoint((int) p2.getX(), (int) p2.getY());
        trect.addPoint((int) p3.getX(), (int) p3.getY());
    }
}
