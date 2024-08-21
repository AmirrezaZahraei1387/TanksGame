package com.github.AmirrezaZahraei1387.TanksGame.Engine.Anim;

import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JComponent;
import javax.swing.Timer;

public class AnimationExecutor extends JComponent {

    private final Animation[] animations;
    private final ArrayList<AnimJob> jobs;
    private final Timer timer;

    public AnimationExecutor(Animation[] animations){
        this.animations = animations;
        this.jobs = new ArrayList<>();
        this.timer = new Timer(4, e -> {
            repaint(1);
        }
        );
    }

    @Override
    public void paintComponent(Graphics _g2d){

        for(int i = 0; i < jobs.size(); ++i){

            AnimJob job = jobs.get(i);

            job.draw(_g2d);

            if(job.isFinished())
                jobs.remove(i);
        }

    }

    public void addAnim(int id, Alignment align){
        jobs.add(new AnimJob(animations[id], align));
    }

    public void start(){
        timer.start();
    }

    public void stop(){
        timer.stop();
    }
}
