package com.github.AmirrezaZahraei1387.mapCam;


import java.awt.Dimension;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.HashSet;

/*
calculates the objects contained within the
camera space with binary searching.
 */
public class WhichRender {

    public static class Pair implements Comparable<Integer>{

        public final double num;
        public final int id;

        public Pair(double num, int id){
            this.num = num;
            this.id = id;
        }

        @Override
        public String toString(){
            return "Pair [num=" + num + ", id=" + id + "]";
        }

        @Override
        public int compareTo(Integer o) {
            return Double.compare(num, o);
        }

    }

    /*
    100000002
    000000000
    000000000
    300000004

    p1 -> y-value of 1 and 2 -> y-value of 3 and 4
    p2 -> x-value of 3 and 1 -> x-value of 4 and 2

    these arrays are sorted and are assumed to never change
     */
    private final Pair[] p1;
    private final Pair[] p2;

    private final CameraCord cameraHandler;

    /*
    p1 are the x coordinates and
    p2 are the y coordinates
     */
    public WhichRender(Pair[] p1, Pair[] p2, CameraCord cameraHandler){
        this.p1 = p2;
        this.p2 = p1;
        this.cameraHandler = cameraHandler;
    }

    /*
    returns an integer HashSet of id's of objects
    contained inside the camera.
     */
    public HashSet calcRenderReq(){

        Rectangle2D bounds = cameraHandler.getBounds();

        double x1 = bounds.getX();
        double x2 = bounds.getX() + bounds.getWidth();

        double y1 = bounds.getY();
        double y2 = bounds.getY() + bounds.getHeight();

        int b_x1 = binSt(x1, p2, true);
        int b_x2 = binSt(x2, p2, false);
        int b_y1 = binSt(y1, p1, true);
        int b_y2 = binSt(y2, p1, false);

        HashSet<Integer> ids = new HashSet<>();

        addBound(p1, b_y1, b_y2, ids);
        addBound(p2, b_x1, b_x2, ids);

        return ids;
    }

    /*
    returns the last index which is its value is
    smaller than the specified object

    set "where" to true if you want the "begin" to be returned.
    set where to false otherwise.
     */
    private static int binSt(double baseVal, Pair[] arr, boolean where){

        int begin = 0;
        int end = arr.length; // one past the actual last
        int middle;

        while (end > begin) {
            middle = begin + (end - begin) / 2;

            if (baseVal < arr[middle].num) {
                end = middle;

            } else if (arr[middle].num < baseVal) {
                begin = middle + 1;

            }else if(arr[middle].num == baseVal){
                return middle;
            }
        }

        if(where)
            return (begin - 1 >= 0? begin - 1 : 0);
        else
            return (end >= arr.length? end - 1 : end);
    }

    private static void addBound(Pair[] arr,
                                 int b, int e,
                                 HashSet<Integer> ids){
        for(int i = b; i <= e; i++)
            ids.add(arr[i].id);
    }

//    public static void main(String[] args) {
//
//        Pair[] p1 = new Pair[]{
//                new Pair(2, 1),
//                new Pair(10, 1),
//                new Pair(10, 2),
//                new Pair(10, 3),
//                new Pair(15, 3),
//                new Pair(15, 2),
//        };
//
//        Pair[] p2 = new Pair[]{
//                new Pair(1, 2),
//                new Pair(7, 1),
//                new Pair(10, 2),
//                new Pair(10, 3),
//                new Pair(14, 3),
//                new Pair(20, 1),
//        };
//
//        CameraBase camera = new StaticCamera(new Point2D.Double(0, 0)
//                , new Point2D.Double(0, 0), null);
//
//        CameraCord cameraCord = new CameraCord();
//        cameraCord.setCamera(camera);
//        cameraCord.setWindowDim(new Dimension(15, 20));
//
//        WhichRender render = new WhichRender(p1, p2, cameraCord);
//        System.out.println(render.calcRenderReq());
//        System.out.println(cameraCord.getBounds());
//    }
}
