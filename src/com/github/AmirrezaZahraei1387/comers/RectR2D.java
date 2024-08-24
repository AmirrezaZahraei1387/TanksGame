package com.github.AmirrezaZahraei1387.comers;

import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;


/*
representing a 2D Rectangle with its four points.
The rectangle is not meant to be parallel with x and y-axis
and can be rotated.
 */
public class RectR2D {

    private final double[] xpoints;
    private final double[] ypoints;

    {
        xpoints = new double[4];
        ypoints = new double[4];
    }

    public RectR2D(Rectangle2D rect) {
        xpoints[0] = rect.getX();
        ypoints[0] = rect.getY();

        xpoints[1] = rect.getX() + rect.getWidth();
        ypoints[1] = rect.getY();

        xpoints[2] = rect.getX() + rect.getWidth();
        ypoints[2] = rect.getY() + rect.getHeight();

        xpoints[3] = rect.getX();
        ypoints[3] = rect.getY() + rect.getHeight();
    }

    public RectR2D(double[] _xpoints, double[] _ypoints) {

        if(_xpoints.length != 4 || _ypoints.length != 4)
            throw new IndexOutOfBoundsException("the RectR2D requires exactly 4 points");

        System.arraycopy(_xpoints, 0, xpoints, 0, 4);
        System.arraycopy(_ypoints, 0, ypoints, 0, 4);

        double dist1 = parDist(xpoints[0], ypoints[0], xpoints[1], ypoints[1]);
        double dist2 = parDist(xpoints[0], ypoints[0], xpoints[2], ypoints[2]);

        if(dist1 > dist2){
            swap(xpoints, 1, 2);
            swap(ypoints, 1, 2);

            dist1 = parDist(xpoints[1], ypoints[1], xpoints[2], ypoints[2]);
            dist2 = parDist(xpoints[1], ypoints[1], xpoints[3], ypoints[3]);

            if(dist1 > dist2){
                swap(xpoints, 2, 3);
                swap(ypoints, 2, 3);

            }
            //else
            // they are in correct order

        }else{ // the points 0 and 1 are in correct order
            dist1 = parDist(xpoints[1], ypoints[1], xpoints[2], ypoints[2]);
            dist2 = parDist(xpoints[1], ypoints[1], xpoints[3], ypoints[3]);

            if(dist1 > dist2){
                swap(xpoints, 2, 3);
                swap(ypoints, 2, 3);

            }
            //else
            // they are in correct order
        }
    }

    /*
    the intersects method check if at least
    one of the points of the first rect is contained with the other.
    this method will not always work (ex.if the rectangles create and X shape.)
     */
    public boolean intersects(RectR2D other){
        for(int i = 0; i < 4; i++)
            if(contains(other.xpoints[i], other.ypoints[i]))
                return true;
        return false;
    }

    public boolean contains(double x, double y){

        // going through corresponding segments
        boolean orit01 = getPointOrient(x, y, xpoints[0], ypoints[0], xpoints[1], ypoints[1]);
        boolean orit02 = getPointOrient(x, y, xpoints[2], ypoints[2], xpoints[3], ypoints[3]);

        boolean orit11 = getPointOrient(x, y, xpoints[0], ypoints[0], xpoints[3], ypoints[3]);
        boolean orit12 = getPointOrient(x, y, xpoints[1], ypoints[1], xpoints[2], ypoints[2]);

        return orit12 != orit11 && orit02 != orit01;
    }

    // transforms all the points from the base rectangle rect
    // with an affine transform and set the coordinates of the current
    // rect to it.
    public void updatePoints(AffineTransform t, Rectangle2D rect){
        Point2D p0 = new Point2D.Double(rect.getX(),
                rect.getY());
        t.transform(p0, p0);
        Point2D p1 = new Point2D.Double(rect.getX() + rect.getWidth(),
                rect.getY());
        t.transform(p1, p1);
        Point2D p2 = new Point2D.Double(rect.getX() + rect.getWidth(),
                rect.getY() + rect.getHeight());
        t.transform(p2, p2);
        Point2D p3 = new Point2D.Double(rect.getX(),
                rect.getY() + rect.getHeight());
        t.transform(p3, p3);

        xpoints[0] = (int) p0.getX();
        xpoints[1] = (int) p1.getX();
        xpoints[2] = (int) p2.getX();
        xpoints[3] = (int) p3.getX();

        ypoints[0] = (int) p0.getY();
        ypoints[1] = (int) p1.getY();
        ypoints[2] = (int) p2.getY();
        ypoints[3] = (int) p3.getY();
    }

    @Override
    public String toString(){
        return "RectR2D((%d, %d), (%d, %d), (%d, %d), (%d, %d))".formatted(
                xpoints[0], ypoints[0],
                xpoints[1], ypoints[1],
                xpoints[2], ypoints[2],
                xpoints[3], ypoints[3]);
    }

    public Point2D get(int i){
        return new Point2D.Double(xpoints[i], ypoints[i]);
    }

    private static double parDist(double x1, double y1, double x2, double y2){
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }

    private static void swap(double[] arr, int i, int j){
        double temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /*
    returns the orientation of a point about a line.
     */
    private static boolean getPointOrient(double x, double y,
                                          double sx1, double sy1,
                                          double sx2, double sy2){
        x -= sx1;
        y -= sy1;
        sx2 -= sx1;
        sy2 -= sy1;

        sx1 = 0;
        sy1 = 0;

        double base = sx1 - sx2;

        if(base != 0) {
            double a = (sy1 - sy2) / (sx1 - sx2);
            double newy = a * x;

            return newy > y;
        }else{
            return sx1 > x;
        }
    }
}
