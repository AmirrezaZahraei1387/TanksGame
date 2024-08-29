package com.gitub.AmirrezaZahraei1387.AnimDis;

import com.gitub.AmirrezaZahraei1387.Camera.CameraHandler;
import com.gitub.AmirrezaZahraei1387.common.Alignment;
import com.gitub.AmirrezaZahraei1387.common.Transformation;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.PriorityQueue;
import javax.swing.JComponent;
import javax.swing.Timer;

public class AnimationExecutor extends JComponent {

    private final Animation[] anims;
    private PriorityQueue<AnimationJob> jobs;
    private final Timer timer;
    private final CameraHandler cam;

    public AnimationExecutor(Animation[] anims, CameraHandler cam) {
        this.anims = anims;
        jobs = new PriorityQueue<>();
        this.cam = cam;

        timer = new Timer(3, e -> {

        });
    }

    public void addAnim(int id, Alignment user_al, Alignment anim_al, Transformation t){
        jobs.add(new AnimationJob(anims[id], user_al, anim_al, t));
    }

    @Override
    public void paintComponent(Graphics _g2d){
        Graphics2D g2d = (Graphics2D) _g2d;
        cam.setGraphics(g2d);

        PriorityQueue<AnimationJob> queue = new PriorityQueue<>();

        while(!jobs.isEmpty()){
            AnimationJob job = jobs.remove();
            job.paint(g2d);

            if(!job.idDone()){
                queue.add(job);
            }
        }

        jobs = queue;
    }

    public void start(){
        timer.start();
    }

    public void stop(){
        timer.stop();
    }
}
