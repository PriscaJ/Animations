package cs3500.animator.view;

import java.awt.*;
import java.util.ArrayList;


import javax.swing.*;

import cs3500.animator.model.IReadOnlyModel;
import cs3500.animator.model.Shapes;

/**
 * Class that creates the visual view it is used in junction with the AnimationPanel class
 * to display a given animation.
 */

public class VisualView extends JFrame implements IView {
  private String visualView;
  private AnimationPanel aniPanel;
  private JLabel display;
  JScrollPane pane;

  public VisualView(ArrayList<Shapes> shapesList, int lastTick, int ticksPerSec) {
    this.setTitle("Easy Animator!");
    this.setSize(1000, 1000);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLayout(new BorderLayout());
    // animation panel
    this.aniPanel = new AnimationPanel(shapesList, lastTick, ticksPerSec);
    this.aniPanel.setPreferredSize(new Dimension(1000, 1000));
    this.add(this.aniPanel, BorderLayout.CENTER);
    // scroll pane
    this.pane = new JScrollPane(this.aniPanel);
    pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    pane.setSize(1000,1000);
    this.add(this.pane);
    this.pack();
  }

  public void makeVisible() {
    this.setVisible(true);
    System.out.println("VisualView made visible!");
  }
}

