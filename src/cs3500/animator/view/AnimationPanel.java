package cs3500.animator.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;

import cs3500.animator.controller.Controller;
import cs3500.animator.model.IReadOnlyModel;
import cs3500.animator.model.Shapes;


/**
 * This panel represents the area where the shapes and actions of an animation will be displayed.
 */
public class AnimationPanel extends JPanel implements ActionListener {
  // todo have a controller actionlistener to include in the constructor???
  private int tick;
  private Timer t;
  private Map<String, Shapes> shapesInAnimation;

  public AnimationPanel() {
    this.setBackground(Color.WHITE);
    this.tick = 0;
    this.t = new Timer(0, this);
    this.shapesInAnimation = new HashMap<String, Shapes>();
  }

  public void setShapes(Map<String, Shapes> shapes) {
    this.shapesInAnimation = shapes;
  }

  @Override
  protected void paintComponent(Graphics g) {

    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;

    for (Shapes shape: shapesInAnimation.values()) {
      if (shape.isOval()) {
        g2d.drawOval(shape.getXPosition().intValue(), shape.getYPosition().intValue(),
                getWidth(), getHeight());
      }
      else if(shape.isRect()) {
        g2d.drawRect(shape.getXPosition().intValue(), shape.getYPosition().intValue(),
                getWidth(), getHeight());
      }
    }

  }

  @Override
  public void actionPerformed(ActionEvent e) {
    for (int i = 0; i < animationTime; i++) {
      // update the tick in each iteration
      tick = i;


    }
  }
}
