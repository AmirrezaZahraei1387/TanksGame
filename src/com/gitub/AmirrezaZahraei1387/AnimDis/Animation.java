package com.gitub.AmirrezaZahraei1387.AnimDis;

import java.awt.image.BufferedImage;

public class Animation {
    final BufferedImage[] images; // all the images that
    // construct the animation
    final int timePerFrame; // time to wait for each
    // frame(represented in milliseconds)

    public Animation(BufferedImage[] images, int timePerFrame) {
        this.images = images;
        this.timePerFrame = timePerFrame;
    }
}
