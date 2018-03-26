package cs3500.animator.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

import cs3500.animator.model.AnimationCommand;
import cs3500.animator.model.Shapes;


/**
 * This panel represents the area where the shapes and actions of an animation will be displayed.
 */
public class AnimationPanel extends JPanel implements ActionListener {
  // todo have a controller actionlistener to include in the constructor???

  private int tick;
  private Timer t;
  private ArrayList<Shapes> shapesList;
  private int lastTick;
  private boolean looping = false;

  /**
   * The constructor for the Animation Panel
   *
   * @param shapesList The list of shapes in an Animation.
   * @param lastTick The last tick in an Aniation marking its end.
   * @param ticksPerSec The speed of animation in ticks per second.
   */
  public AnimationPanel(ArrayList<Shapes> shapesList, int lastTick, int ticksPerSec) {
    // find a way to instantiate the model by using same fields passed into it
    this.shapesList = shapesList;
    this.lastTick = lastTick;
    this.setBackground(Color.WHITE);
    this.setPreferredSize(new Dimension(800, 800));
    this.tick = -1;
    this.t = new Timer(ticksPerSec, this);

    t.start();
  }

  //public AnimationPanel(ArrayList<Shapes> shapesList, int lastTick, int ticksPerSec, boolean looping) {
   // looping = looping;

    protected void stopTimer(){
    t.stop();
  }

  protected  void resetTicks() {
    tick = 0;
  }

  @Override
  protected void paintComponent(Graphics g) {

    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;

    for (Shapes shape : activeShapes(tick)) {
      float r = shape.getRed();
      float gg = shape.getGreen();
      float b = shape.getBlue();
      Color c = new Color(r, gg, b);
      g2d.setColor(c);
      if (shape.isOval()) {
        g2d.fillOval(shape.getXPosition().intValue() - shape.getWidth().intValue() / 2,
            shape.getYPosition().intValue() - shape.getHeight().intValue() / 2,
            shape.getWidth().intValue(), shape.getHeight().intValue());

      } else if (shape.isRect()) {
        Rectangle r2 = new Rectangle(
            new Point(shape.getXPosition().intValue(), shape.getYPosition().intValue()),
            new Dimension(shape.getWidth().intValue(), shape.getHeight().intValue()));
        g2d.fill(r2);
      }
    }
  }

  /**
   * Returns the list of Shapes that are currently running at a particular tick.
   *
   * @param time The current tick.
   * @return The list of shapes running at the given time.
   */
  private ArrayList<Shapes> activeShapes(int time) {
    ArrayList<Shapes> currentShapes = new ArrayList<>();
    for (Shapes s : shapesList) {
      if (time >= s.getAppears() && time <= s.getDisappears()) {
        currentShapes.add(s);
      }
    }
    return currentShapes;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    // when the model's last animation stops, stop the timer
    if (tick >= lastTick) {
      if (looping) {
        tick = 0;
      }
      else {
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
