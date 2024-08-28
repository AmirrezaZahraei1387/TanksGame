package com.gitub.AmirrezaZahraei1387.GameMap;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;

public interface TileGB {

    // called once this object collides with
    // one of the players
    default void onCollide(){}

    /*
    renders this tile whenever the view of
    camera changes. It is called by the tile manager.
    if you want to render your self just leave this method.
     */
    default void render(Graphics2D g2d){};

    /*
    returns an id which describes the type of
    object this tile is representing.
     */
    int getId();

    /*
    if it returns true then a player can not enter this tile
     */
    boolean isCollisionObj();

    byte ACTIVE = 0; // the tile is working, rendering should be preformed regularly.
    byte DESTROYED = 2; // the tile no longer need rendering and can be removed.
    byte REFERENCE = 4; // it is just a reference to a another tile.

    // returns one of the above status
    byte getStatus();

    // if it is a reference tile then override this method and return the
    // position that is being referenced
    default int getReference(){return -1;}
    default int getLayer(){return -1;}

    // whenever the camera changes this is called to set the
    // new position of the tile.
    default void setPos(Point point){};
}
