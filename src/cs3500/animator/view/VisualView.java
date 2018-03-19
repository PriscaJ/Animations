package cs3500.animator.view;

import java.awt.*;


import javax.swing.*;

import cs3500.animator.model.IReadOnlyModel;

public class VisualView extends JFrame implements IView {
  private String visualView;
  private AnimationPanel aniPanel;
  private JLabel display;
  // todo: what data structure/information do we want here from the model?

  public VisualView() {
    super();
    this.setTitle("Easy Animator!");
    this.setSize(500,500);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    this.setLayout(new BorderLayout());
    this.aniPanel = new AnimationPanel();
    aniPanel.setPreferredSize(new Dimension(500,500));
    this.add(aniPanel,BorderLayout.CENTER);

    this.pack();
  }

  @Override
  public void makeVisible() {
    this.setVisible(true);
  }



}
