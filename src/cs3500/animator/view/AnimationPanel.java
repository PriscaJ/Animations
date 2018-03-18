package cs3500.animator.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import cs3500.animator.controller.Controller;
import cs3500.animator.model.IReadOnlyModel;


/**
 * This panel represents the area where the shapes and actions of an animation will be displayed.
 */
public class AnimationPanel extends JPanel implements ActionListener {
  // todo have a controller actionlistener to include in the constructor???
  // I don't think we need an actionlistener yet because the user isn't interacting wiith the
  // animation. Once we have to implement the interactive view we'll need one!!
  private int tick;
  private Timer t;

  public AnimationPanel() {
    this.setBackground(Color.WHITE);
    this.tick = -1;
    this.t = new Timer(0, this);
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);


  }

  @Override
  public void actionPerformed(ActionEvent e) {

  }
}
