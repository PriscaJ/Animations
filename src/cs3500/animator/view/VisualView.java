package cs3500.animator.view;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

import cs3500.animator.model.Shapes;

/**
 * Class that creates the visual view it is used in junction with the AnimationPanel class to
 * display a given animation.
 */

public class VisualView extends JFrame implements IView {
  private JScrollPane pane;
  private AnimationPanel aniPanel;
  private boolean looping;

  /**
   * The Constructor for the visual view.
   *
   * @param shapesList The list of shapes in the animation.
   * @param lastTick The last tick marking the end of an animation.
   * @param ticksPerSec The speed of the animation.
   */
  public VisualView(ArrayList<Shapes> shapesList, int lastTick, int ticksPerSec) {
    initView();
    initAnimationPanel(shapesList, lastTick, ticksPerSec);
    this.looping = false;
  }

  public VisualView(ArrayList<Shapes> shapes, int endTime, int tps, boolean looping) {
    initView();
    initAnimationPanel(shapes, endTime, tps);
    this.looping = looping;
  }

  private void initView() {
    this.setTitle("Easy Animator!");
    this.setSize(800, 800);
    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    this.setLayout(new BorderLayout());
    this.setLocation(0, 0);
    this.setResizable(true);
  }

  private void initAnimationPanel(ArrayList<Shapes> shapesList, int lastTick, int ticksPerSec) {
    this.aniPanel = new AnimationPanel(shapesList, lastTick, ticksPerSec);
    this.aniPanel.setPreferredSize(new Dimension(800, 800));
    this.add(this.aniPanel);
    this.pane = new JScrollPane(this.aniPanel);
    pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    pane.setPreferredSize(new Dimension(800, 800));
    pane.setSize(800, 800);
    this.add(this.pane);
    this.pack();
  }

  @Override
  public void makeVisible() {
    this.setVisible(true);
    System.out.println("VisualView made visible!");
  }
}

