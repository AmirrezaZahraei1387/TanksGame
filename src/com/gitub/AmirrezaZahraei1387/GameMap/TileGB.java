package com.gitub.AmirrezaZahraei1387.GameMap;

import java.awt.Dimension;
import java.awt.Graphics2D;

public interface TileGB {

    // called once this object collides with
    // one of the players
    default void onCollide(){}


    /*
    renders this tile whenever the view of
    camera changes.
     */
    void render(Graphics2D g2d);

    /*
    returns an id which describes the type of
    object this tile is representing.
     */
    int getId();

    /*
    if it returns true then player a player can enter this tile
     */
    boolean isCollisionObj();

    byte ACTIVE = 0; // the tile is working, rendering should be preformed regularly.
    byte DESTROYED = 2; // the tile no longer need rendering and can be removed.
    byte REFERENCE = 4; // it is just a reference to a another tile.

    // returns one of the above status
    byte getStatus();

    // if it is a reference tile then override this method and return the
    // position that is being referenced
    default int getReference(){return -1;};
}
