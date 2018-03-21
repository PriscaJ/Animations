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
  // todo: what data structure/information do we want here from the model?

  /**
   * The constructor for a visual view.
   * @param shapesList The List of shapes that are present in the animation.
   * @param lastTick The ending tick in an animation, signalling its end.
   * @param ticksPerSec Controls the speed of the animation.
   */
  public VisualView(ArrayList<Shapes> shapesList, int lastTick, int ticksPerSec) {
    super();
    this.setTitle("Easy Animator!");
    this.setSize(500,500);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    this.setLayout(new BorderLayout());
    this.aniPanel = new AnimationPanel(shapesList, lastTick, ticksPerSec);
    aniPanel.setPreferredSize(new Dimension(500,500));
    this.add(aniPanel,BorderLayout.CENTER);

    this.pack();
  }

  @Override
  public void makeVisible() {
    this.setVisible(true);
  }



}
