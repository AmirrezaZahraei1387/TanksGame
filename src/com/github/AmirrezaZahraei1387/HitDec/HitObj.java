package com.github.AmirrezaZahraei1387.HitDec;

import java.awt.Rectangle;

public interface HitObj {

    void onHit(HitObj obj, int obj_id);

    Rectangle getHitRect();
}
