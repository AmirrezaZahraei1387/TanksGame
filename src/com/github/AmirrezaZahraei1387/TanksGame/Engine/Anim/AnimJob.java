package com.github.AmirrezaZahraei1387.TanksGame.Engine.Anim;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;

class AnimJob{
    private final Animation anim; // animation's id in animations map

    // the alignment information to apply to the animation relative to
    // sprite itself.
    private final Alignment alignment;

    private int currentFrame = -1;

    // the most recent time a new frame was shown(in milliseconds)
    private long prevTime = 0;

    public AnimJob(Animation anim, Alignment align){
        this.anim = anim;
        this.alignment = align;

    }


    public void draw(Graphics _g2d){

        Graphics2D g2d = (Graphics2D) _g2d;

        if(!isFinished()){
            long currentTime = System.currentTimeMillis();

            if(currentTime - prevTime <= anim.getTimePerFrame()){

                AffineTransform oldT = g2d.getTransform();
                AffineTransform newT = alignment.getTransform();

                //sprite alignment for the animation
                Point sA = alignment.getPlace(null);
                //animation's current Frame alignment
                Point aA = anim.getAlignment(currentFrame);

                if(newT != null)
                    g2d.setTransform(newT);

                g2d.drawImage(anim.get(currentFrame), sA.x - aA.x, sA.y - aA.y, null);

                g2d.setTransform(oldT);

            }else{
                ++currentFrame;
                prevTime = currentTime;
            }

        }
    }



    public boolean isFinished(){
        return currentFrame == anim.size();
    }

}
