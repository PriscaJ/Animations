package cs3500.animator.view;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.swing.JPanel;
import javax.swing.Timer;

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
  private Map<Integer, ArrayList<Shapes>> layers = new HashMap<>();
  /**
   * The constructor for the Animation Panel.
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

  /**
   * This method uses the given shapes in the animation.
   *
   * @param shapes to be used.
   */
  protected void setShapesList(ArrayList<Shapes> shapes) {
    this.shapesList = shapes;
    t.start();
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;
    for (Shapes s : activeShapes(tick)) {
      if (layers.containsKey(s.getLayer())) {
        ArrayList<Shapes> shapes = layers.get(s.getLayer());
        shapes.add(s);
        layers.put(s.getLayer(), shapes);
      }
      else {
        ArrayList<Shapes> newList = new ArrayList<>();
        newList.add(s);
        layers.put(s.getLayer(), newList);
      }
    }
    List<Integer> layerNums = new ArrayList<>(layers.keySet());
    // sorted list of layers
    Collections.sort(layerNums);
    List<Shapes> sortedShapes = new ArrayList<>();
    for (Integer i : layerNums) {
      List<Shapes> shapesInLayer = layers.get(i);
      sortedShapes.addAll(shapesInLayer);
    }
    
    for (Shapes shape : sortedShapes) {
      float r = shape.getRed();
      float gg = shape.getGreen();
      float b = shape.getBlue();
      Color c = new Color(r, gg, b);
      g2d.setColor(c);
      if (shape.isOval()) {
        g2d.fillOval(shape.getXPosition().intValue() - shape.getWidth().intValue() / 2,
            shape.getYPosition().intValue() - shape.getHeight().intValue() / 2,
            shape.getWidth().intValue() * 2, shape.getHeight().intValue() * 2);

      } else if (shape.isRect()) {
        Rectangle r2 = new Rectangle(
            new Point(shape.getXPosition().intValue(), shape.getYPosition().intValue()),
            new Dimension(shape.getWidth().intValue(), shape.getHeight().intValue()));
        g2d.fill(r2);
      }
    }
  }

  /**
   * This method allows the view to start the timer if it has been paused.
   */
  protected void startTimer() {
    t.start();
  }

  /**
   * This method allows the view to stop the timer.
   */
  protected void stopTimer() {
    t.stop();
  }

  /**
   * This method allows the view to restart the animation by resetting the tick to -1.
   */
  protected void setTickToZero() {
    this.tick = -1;
  }

  /**
   * This method allows the view to increase the delay of the timer.
   */
  protected void increaseSpeed() {
    t = new Timer(t.getDelay() + 15, this);
  }

  /**
   * This method allows the view to decrease the delay of the timer.
   */
  protected void decreaseSpeed() {
    if (t.getDelay() > 15) {
      t = new Timer(t.getDelay() - 15, this);
    }
  }

  /**
   * This method allows the view to get the current speed to tell the svg view.
   */
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

  /**
   * Sets the end time of the animation.
   *
   * @param endTime is the last tick a shape is present.
   */
  protected void setEndTime(int endTime) {
    this.lastTick = endTime;
  }

  /**
   * Sets the ticks per second (speed) of the animation.
   *
   * @param tps is the ticks per second.
   */
  protected void setTPS(int tps) {
    t.setDelay(tps);
  }

  /**
   * Sets whether the animation is looping or not.
   *
   * @param looping is whether an animation will repeat after ending.
   */
  protected void setLooping(boolean looping) {
    this.looping = looping;
  }

  /**
   * Returns the list of Shapes that are currently running at a particular tick.
   *
   * @param time The current tick.
   * @return The list of shapes running at the given time.
   */
  protected ArrayList<Shapes> activeShapes(int time) {
    ArrayList<Shapes> currentShapes = new ArrayList<>();
    for (Shapes s : shapesList) {
      if (time >= s.getAppears() && time <= s.getDisappears()) {
        currentShapes.add(s);
      }
    }
    return currentShapes;
  }

}
