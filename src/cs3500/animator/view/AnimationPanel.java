package cs3500.animator.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;

import cs3500.animator.controller.Controller;
import cs3500.animator.model.AnimationCommand;
import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.IReadOnlyModel;
import cs3500.animator.model.Shapes;


/**
 * This panel represents the area where the shapes and actions of an animation will be displayed.
 */
public class AnimationPanel extends JPanel implements ActionListener {
  // todo have a controller actionlistener to include in the constructor???
  // I don't think we need an actionlistener yet because the user isn't interacting wiith the
  // animation. Once we have to implement the interactive view we'll need one!!

  private int tick;
  private Timer t;
  private ArrayList<Shapes> shapesList;
  private int lastTick;
  private int ticksPerSec;
  // private Map<String, Shapes> shapesInAnimation;

  /**
   * The constructor for the Animation Panel
   * @param shapesList The list of shapes in an Animation.
   * @param lastTick The last tick in an Aniation marking its end.
   * @param ticksPerSec The speed of animation in ticks per second.
   */
  public AnimationPanel(ArrayList<Shapes> shapesList, int lastTick, int ticksPerSec) {
    // find a way to instantiate the model by using same fields passed into it
    this.shapesList = shapesList;
    this.lastTick = lastTick;
    this.ticksPerSec = ticksPerSec;
    this.setBackground(Color.WHITE);
    this.tick = 0;
    this.t = new Timer(ticksPerSec, this);
    // this.shapesInAnimation = new HashMap<String, Shapes>();
  }

  /*
  public void setShapes(Map<String, Shapes> shapes) {
    this.shapesInAnimation = new HashMap<String, Shapes>().putAll(shapes);
  }*/

  @Override
  protected void paintComponent(Graphics g) {

    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;

    for (Shapes shape: activeShapes(tick)) {
      g2d.setColor(new Color(shape.getRed(), shape.getGreen(), shape.getBlue()));
      if (shape.isOval()) {
        //g2d.setColor(new Color(shape.getRed(), shape.getGreen(), shape.getBlue()));
        g2d.fillOval(shape.getXPosition().intValue(), shape.getYPosition().intValue(),
                getWidth(), getHeight());
      }
      else if(shape.isRect()) {
        //g2d.setColor(new Color(shape.getRed(), shape.getGreen(), shape.getBlue()));
        g2d.fillRect(shape.getXPosition().intValue(), shape.getYPosition().intValue(),
                getWidth(), getHeight());
      }
    }
  }

  /**
   * Returns the list of Shapes that are currently running at a particular tick.
   * @param time The current tick.
   * @return The list of shapes running at the given time.
   */
  private ArrayList<Shapes> activeShapes(int time){
    ArrayList<Shapes> currentShapes = new ArrayList<Shapes>();

    for (Shapes s: shapesList) {
      if (time >= s.getAppears() && time <= s.getDisappears()) {
        currentShapes.add(s);
      }
    }
    return currentShapes;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    // for every shape call its command to execute the action.
    for (Shapes s: activeShapes(tick)) {
      for(AnimationCommand cmd: s.getCommands()) {
        cmd.execute(tick);
      }
    }
    tick++;
    repaint();

    // when the model's last animation stops, stop the timer
    if (tick > lastTick) {
      t.stop();
    }
  }
}
