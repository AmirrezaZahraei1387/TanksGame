package com.github.AmirrezaZahraei1387.HitDec;

import com.github.AmirrezaZahraei1387.mapCam.CameraCord;
import com.github.AmirrezaZahraei1387.mapCam.GameGround;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class HitDetector {

    private final GameGround gameGround;

    private HashMap<Integer, HashSet<HitObj>>[][] map;

    public HitDetector(GameGround gameGround) {
        this.gameGround = gameGround;

        map = new HashMap[gameGround.getMapSize().width][gameGround.getMapSize().height];

        for (int x = 0; x < gameGround.getMapSize().width; x++)
            for (int y = 0; y < gameGround.getMapSize().height; y++)
                map[x][y] = new HashMap<>();
    }

    public void add(HitObj hitObj, int id) {
        Rectangle hitRect = hitObj.getHitRect();
        Point p1 = new Point(hitRect.x, hitRect.y);
        Point p2 = new Point(hitRect.x + hitRect.width, hitRect.y);
        Point p3 = new Point(hitRect.x + hitRect.width, hitRect.y + hitRect.height);
        Point p4 = new Point(hitRect.x, hitRect.y + hitRect.height);

        add(hitObj, id, p1);
        add(hitObj, id, p2);
        add(hitObj, id, p3);
        add(hitObj, id, p4);
    }

    public void remove(HitObj hitObj, int id) {
        Rectangle hitRect = hitObj.getHitRect();
        Point p1 = new Point(hitRect.x, hitRect.y);
        Point p2 = new Point(hitRect.x + hitRect.width, hitRect.y);
        Point p3 = new Point(hitRect.x + hitRect.width, hitRect.y + hitRect.height);
        Point p4 = new Point(hitRect.x, hitRect.y + hitRect.height);

        remove(hitObj, id, p1);
        remove(hitObj, id, p2);
        remove(hitObj, id, p3);
        remove(hitObj, id, p4);
    }

    public ArrayList getHitObj(CameraCord c, int id) {

        ArrayList<HitObj> yes = new ArrayList<>();
        ArrayList<Integer> curr = c.getCurrGrounds();

        for (Integer ground : curr){
            Point pos = gameGround.translate(ground);
            HashSet<HitObj> set = map[pos.x][pos.x].get(id);

            if(set != null)
                yes.addAll(set);

        }

        return yes;
    }

    private void add(HitObj obj, int id, Point p){
        HashSet<HitObj> d = map[p.x][p.y].get(id);

        if(d != null){
            d.add(obj);
        }else{
            d = new HashSet<>();
            d.add(obj);
            map[p.x][p.y].put(id, d);
        }
    }

    private void remove(HitObj obj, int id, Point p){
        HashSet<HitObj> d = map[p.x][p.y].get(id);

        if(d != null){
            d.remove(obj);
        }
    }
}
