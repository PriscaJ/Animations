package cs3500.animator.view;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.*;

import cs3500.animator.model.AnimationCommand;
import cs3500.animator.model.Shapes;

public class InteractivePanel extends AnimationPanel{
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


  }


  //public AnimationPanel(ArrayList<Shapes> shapesList,
  // int lastTick, int ticksPerSec, boolean looping) {
  // looping = looping;

  protected void stopTimer() {
    t.stop();
  }

  protected void setTickToZero() {
    this.tick = 0;
  }

  protected void increaseSpeed() {
    Timer newTimer = new Timer(t.getDelay() + 10, this);
    t = newTimer;

  }

  protected void decreaseSpeed() {
    if (t.getDelay() > 10) {
      Timer newTimer = new Timer(t.getDelay() - 10, this);
      t = newTimer;
    }

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



}
