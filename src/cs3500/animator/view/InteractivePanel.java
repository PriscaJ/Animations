package cs3500.animator.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

import cs3500.animator.model.AnimationCommand;
import cs3500.animator.model.Shapes;

public class InteractivePanel extends AnimationPanel implements ActionListener{
  private int tick;
  private Timer t;
  private ArrayList<Shapes> shapesList;
  private int lastTick;
  private boolean looping;
  private JList<Shapes> shapesInAnimation;

  /**
   * The constructor for the Animation Panel
   *
   * @param shapesList The list of shapes in an Animation.
   * @param lastTick The last tick in an Animation marking its end.
   * @param ticksPerSec The speed of animation in ticks per second.
   */
  public InteractivePanel(ArrayList<Shapes> shapesList, int lastTick, int ticksPerSec) {
    super(shapesList, lastTick, ticksPerSec);
    this.shapesList = shapesList;
    this.lastTick = lastTick;
    this.setBackground(Color.WHITE);
    // this.setPreferredSize(new Dimension(300, 300));
    this.tick = -1;
    this.t = new Timer(ticksPerSec, this);

    t.start();

  }

  //public AnimationPanel(ArrayList<Shapes> shapesList,
  // int lastTick, int ticksPerSec, boolean looping) {
  // looping = looping;
  protected void startTimer() {
    t.start();
  }


  protected void stopTimer() {
    t.stop();
  }

  protected void setTickToZero() {
    this.tick = 0;
  }

  protected void increaseSpeed() {
    t = new Timer(t.getDelay() + 10, this);
  }

  protected void decreaseSpeed() {
    if (t.getDelay() > 10) {
      t = new Timer(t.getDelay() - 10, this);
    }
  }

  protected int getSpeed() {
    return t.getDelay();
  }


  @Override
  public void actionPerformed(ActionEvent e) {
    // when the model's last animation stops, stop the timer
    if (tick >= lastTick) {
      if (looping) {
        tick = 0;
      } else {
        t.stop();
      }
    }
    // for every shape call its command to execute the action.
    for (Shapes s : activeShapes(tick)) {
      for (AnimationCommand cmd : s.getCommands()) {
        if (cmd.getAnimation().getStart() <= tick && tick <= cmd.getAnimation().getFinish()) {
          cmd.execute(tick);
        }
      }
    }
    tick++;
    repaint();
  }

  protected void setEndTime(int endTime) {
    this.lastTick = endTime;
  }

  protected void setTPS(int TPS) {
    t = new Timer(TPS, this);
  }

  protected void setLooping(boolean looping) {
    this.looping = looping;
  }
}
