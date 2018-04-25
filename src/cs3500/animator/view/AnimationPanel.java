package cs3500.animator.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.JSlider;


import cs3500.animator.model.AnimationCommand;
import cs3500.animator.model.Shapes;

// start with a list of all shapes in correct order
// maintain order of shapes, but sort by layer number
//

/**
 * This panel represents the area where the shapes and actions of an animation will be displayed.
 */
public class AnimationPanel extends JPanel implements ActionListener {
  // todo have a controller actionlistener to include in the constructor???

  private int tick;
  private Timer t;
  private ArrayList<Shapes> currentShapesList;
  private ArrayList<Shapes> currentShapesListCopy;
  // a copy is made whenever a new list is instantiated

  private int lastTick;
  private boolean looping = false;

  private Map<Integer, Map<Integer, ArrayList<Shapes>>> tickToLayersToShapes = new HashMap<>();

  // has reference of progress?
  private JSlider progress;

  /**
   * The constructor for the Animation Panel.
   *
   * @param shapesList The list of shapes in an Animation.
   * @param lastTick The last tick in an Aniation marking its end.
   * @param ticksPerSec The speed of animation in ticks per second.
   */
  public AnimationPanel(ArrayList<Shapes> shapesList, int lastTick, int ticksPerSec) {
    // find a way to instantiate the model by using same fields passed into it
    this.currentShapesList = shapesList;
    this.lastTick = lastTick;
    this.setBackground(Color.WHITE);
    this.setPreferredSize(new Dimension(800, 800));
    this.tick = -1;
    this.t = new Timer(ticksPerSec, this);
    initLayersMap(shapesList);
    //t.start();
  }

  /**
   * Sets the map of active shapes in order of layer
   */
  // starts with all shapes
  private void initLayersMap(ArrayList<Shapes> listOfShapes) {
    tickToLayersToShapes.clear();
    for (Shapes s : listOfShapes) {
      // add the shape to this time
      for (int i = s.getAppears(); i < s.getDisappears(); i++) {
        // --- if it has the tick, get the hashmap from the current tick
        if (tickToLayersToShapes.containsKey(i)) {
          Map<Integer, ArrayList<Shapes>> mapAtTick = tickToLayersToShapes.get(i);
          // if the hashmap for this current tick contains the layer
          if (tickToLayersToShapes.get(i).containsKey(s.getLayer())) {
            ArrayList<Shapes> shapesInLayer = tickToLayersToShapes.get(i).get(s.getLayer());
            shapesInLayer.add(s);
            mapAtTick.put(s.getLayer(), shapesInLayer);
          } else {
            ArrayList<Shapes> curr = new ArrayList<>();
            curr.add(s);
            mapAtTick.put(s.getLayer(), curr);
          }
        } else {
          // it doesnt have a tick -> hashmap
          // create a new hashmap and add the shape to the correct layer
          Map<Integer, ArrayList<Shapes>> newMapAtTick = new HashMap<>();
          ArrayList<Shapes> shapesInLayer = new ArrayList<>();
          shapesInLayer.add(s);
          newMapAtTick.put(s.getLayer(), shapesInLayer);
          tickToLayersToShapes.put(i, newMapAtTick);
        }
      }
    }
  }

  /**
   * This method uses the given shapes in the animation.
   *
   * @param shapes to be used.
   */
  protected void setShapesList(ArrayList<Shapes> shapes) {
    this.currentShapesList = shapes;
    currentShapesListCopy = new ArrayList<>();
    for (Shapes s: currentShapesList) {
      currentShapesListCopy.add(s.getCopy());
    }
    initLayersMap(currentShapesList);
  }

  protected void getProgress(JSlider progress) {
    this.progress = progress;
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;
    if (tickToLayersToShapes.containsKey(tick)) {
      //      List<Integer> layerNums = new ArrayList<>(layers.keySet());
      //      // sorted list of layers
      //      Collections.sort(layerNums);
      List<Integer> layersInAnimation = new ArrayList<>(tickToLayersToShapes.get(tick).keySet());
      Collections.sort(layersInAnimation);
      for (Integer i : layersInAnimation) {
        if (tickToLayersToShapes.get(tick).containsKey(i)) {
          for (Shapes shape : tickToLayersToShapes.get(tick).get(i)) {
            float r = shape.getRed();
            float gg = shape.getGreen();
            float b = shape.getBlue();
            Color c = new Color(r, gg, b);
            g2d.setColor(c);

            if (shape.isOval()) {
//              Ellipse2D oval = new Ellipse2D.Double(shape.getXPosition(), shape.getYPosition(),
//                  shape.getWidth(), shape.getHeight());
//              //AffineTransform transform = new AffineTransform();
//              AffineTransform at = AffineTransform.getRotateInstance(
//                  Math.toRadians(shape.getRadian()), shape.getCenterX(), shape.getCenterY());
//              System.out.print(shape.getRadian() + "\n");
//              g2d.transform(at);
//              at.createTransformedShape(oval);
//              g2d.setPaint(c);
//              g2d.fill(oval);
//
//              g2d.fillOval(shape.getXPosition().intValue() - shape.getWidth().intValue() / 2,
//                  shape.getYPosition().intValue() - shape.getHeight().intValue() / 2,
//                  shape.getWidth().intValue() * 2, shape.getHeight().intValue() * 2);

              // PRISCA's VERSION //
//              g2d.translate(shape.getCenterX(), shape.getCenterY());
//              g2d.rotate( shape.getRadian(), shape.getCenterX() * 2, shape.getCenterY() * 2);
//              g2d.translate(-shape.getCenterX(), -shape.getCenterY());
//
//              g2d.fillOval(shape.getXPosition().intValue() - shape.getWidth().intValue() / 2,
//                  shape.getYPosition().intValue() - shape.getHeight().intValue() / 2,
//                  shape.getWidth().intValue() * 2, shape.getHeight().intValue() * 2);
//              g2d.rotate(-Math.toRadians((double) shape.getRadian()), shape.getCenterX(), shape.getCenterY());
//              System.out.print("rotation check");

              AffineTransform transform = new AffineTransform();
              transform.rotate(Math.toRadians(shape.getRadian()), shape.getCenterX(), shape.getCenterY());
              AffineTransform old = g2d.getTransform();

              g2d.transform(transform);

              g2d.fillOval(shape.getXPosition().intValue() - shape.getWidth().intValue() / 2,
                  shape.getYPosition().intValue() - shape.getHeight().intValue() / 2,
                  shape.getWidth().intValue() * 2, shape.getHeight().intValue() * 2);
              // draw your rectangle here...

              g2d.setTransform(old);

            } else if (shape.isRect()) {
              g2d.setColor(c);
              
              AffineTransform transform = new AffineTransform();
              transform.rotate(Math.toRadians(shape.getRadian()), shape.getCenterX(), shape.getCenterY());
              AffineTransform old = g2d.getTransform();
              g2d.transform(transform);

              g2d.fillRect(shape.getXPosition().intValue(),
                  shape.getYPosition().intValue(),
                  shape.getWidth().intValue(), shape.getHeight().intValue());

              // draw your rectangle here...

              g2d.setTransform(old);

//              g2d.rotate(Math.toRadians((double) shape.getRadian()));
//              g2d.fillRect(shape.getXPosition().intValue(),
//                  shape.getYPosition().intValue(),
//                  shape.getWidth().intValue(), shape.getHeight().intValue());
//              System.out.print("rotation check");
            }
          }
        }
      }
    }
    // return the shapes back to their original state.
//    g2d.setTransform(old);
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
    this.tick = 0;
  }

  /**
   * This method allows the view to increase the delay of the timer.
   */
  protected void increaseSpeed() {
    t.stop();
    if (t.getDelay() - 15 > 1) {
      t = new Timer(t.getDelay() - 15, this);
    }
    t.start();
  }

  /**
   * This method allows the view to decrease the delay of the timer.
   */
  protected void decreaseSpeed() {
    t.stop();
    t = new Timer(t.getDelay() + 15, this);
    t.start();
  }

  /**
   * This method allows the view to get the current speed to tell the svg view.
   */
  protected int getSpeed() {
    return t.getDelay();
  }


  @Override
  public void actionPerformed(ActionEvent e) {
    progress.setValue(tick);
    // when the model's last animation stops, stop the timer
    if (tick >= lastTick) {
      if (looping) {
        setShapesList(currentShapesListCopy);
        tick = 0;
      } else {
        t.stop();
      }
    }
    if (tickToLayersToShapes.containsKey(tick)) {
      List<Integer> layersInAnimation = new ArrayList<>(tickToLayersToShapes.get(tick).keySet());
      Collections.sort(layersInAnimation);
      for (Integer layer : layersInAnimation) {
        if (tickToLayersToShapes.get(tick).containsKey(layer)) {
          for (Shapes s : tickToLayersToShapes.get(tick).get(layer)) {
            for (AnimationCommand cmd : s.getCommands()) {
              cmd.execute(tick);
            }
          }
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

  public void setTick(int tick) {
    this.tick = tick;
  }
}
