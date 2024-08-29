package com.gitub.AmirrezaZahraei1387.AnimDis;

import com.gitub.AmirrezaZahraei1387.common.Alignment;
import com.gitub.AmirrezaZahraei1387.common.Transformation;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class AnimationJob {


    private static long counter = 0;

    private final Transformation tf;

    // anim_al is the coordinates within the animation space
    // and will be aligned with the user_al point in the worldSpace.
    private final Alignment anim_al;
    private final Alignment user_al;

    private final long origin;
    private long prevTime; // time in future that the anim must play.
    // actual time does not match
    private final Animation anim; // the animation being played
    private int currentFrame;

    public AnimationJob(Animation anim,
                        Alignment anim_al,
                        Alignment user_al,
                        Transformation tf) {
        this.anim = anim;
        this.origin = ++counter;
        this.anim_al = anim_al;
        this.user_al = user_al;
        this.tf = tf;

        prevTime = 0;
        currentFrame = -1;
    }

    /*
    the given graphics2D is assumed to be
    set to the appropriate camera view.
     */
    public void paint(Graphics2D g2d) {
        long currentTime = System.currentTimeMillis();

        if(currentTime - prevTime <= anim.timePerFrame){

            AffineTransform prevT = g2d.getTransform();
            BufferedImage curr_image = anim.images[currentFrame];

            Point user_a = user_al.getAlignment(curr_image.getWidth(), curr_image.getHeight());
            prevT.transform(user_a, user_a);  // transforming the point to window space
            Point anim_a = anim_al.getAlignment(curr_image.getWidth(), curr_image.getHeight());

            g2d.translate(anim_a.x - user_a.x, anim_a.y - user_a.y);
            AffineTransform t = tf.getTransform();

            if(t != null)
                g2d.transform(t);

            g2d.drawImage(anim.images[currentFrame],0, 0 , null);

            g2d.setTransform(prevT);
        }else{
            ++currentFrame;
            prevTime = currentTime;
        }
    }

    public boolean idDone(){
        return currentFrame >= anim.images.length;
    }

    @Override
    public int hashCode(){
        // no two animations can have the same origin.
        return (int) this.origin;
    }

    public boolean equals(Object other){
        if(other instanceof AnimationJob j) {
            return this.origin == j.origin;
        }
        return false;
    }
}
