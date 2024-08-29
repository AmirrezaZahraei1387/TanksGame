package com.gitub.AmirrezaZahraei1387.AnimDis;

import com.gitub.AmirrezaZahraei1387.common.Alignment;
import com.gitub.AmirrezaZahraei1387.common.Transformation;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

class AnimationJob implements Comparable<AnimationJob> {

    private final Transformation tf;

    // anim_al is the coordinates within the animation space
    // and will be aligned with the user_al point in the worldSpace.
    private final Alignment anim_al;
    private final Alignment user_al;

    private long futureTime; // time in future that the anim must play.
    // actual time does not match
    private final Animation anim; // the animation being played
    private int currentFrame;

    public AnimationJob(Animation anim,
                        Alignment anim_al,
                        Alignment user_al,
                        Transformation tf) {
        this.anim = anim;
        this.anim_al = anim_al;
        this.user_al = user_al;
        this.tf = tf;

        futureTime = -1;
        currentFrame = 0;
    }

    /*
    the given graphics2D is assumed to be
    set to the appropriate camera view.
     */
    public void paint(Graphics2D g2d) {
        long currentTime = System.currentTimeMillis();
        paintFrame(g2d);

        if(currentTime >= futureTime) {
            ++currentFrame;
            futureTime = currentTime + anim.timePerFrame;
        }

    }

    public boolean idDone(){
        return currentFrame >= anim.images.length;
    }

    public long getFutureTime(){
        return futureTime;
    }

    @Override
    public int compareTo(AnimationJob other) {
        return Long.compare(this.futureTime, other.futureTime);
    }

    private void paintFrame(Graphics2D g2d) {
        AffineTransform prevT = g2d.getTransform();
        BufferedImage curr_image = anim.images[currentFrame];

        Point user_a = user_al.getAlignment(0, 0);
        Point anim_a = anim_al.getAlignment(curr_image.getWidth(), curr_image.getHeight());

        g2d.translate( user_a.x -anim_a.x,  user_a.y - anim_a.y);
        AffineTransform t = tf.getTransform();

        if(t != null)
            g2d.transform(t);

        g2d.drawImage(anim.images[currentFrame],0, 0 , null);

        g2d.setTransform(prevT);
    }
}
