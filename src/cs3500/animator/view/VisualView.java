package cs3500.animator.view;

import java.awt.*;

import javax.swing.*;

public class VisualView extends JFrame implements IView {
  private String visualView;
  private AnimationPanel aniPanel;
  private JLabel display;
  // todo: what data structure/information do we want here from the model?

  public VisualView() {
    super();
    this.setTitle("Turtles!");
    this.setSize(500,500);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    this.aniPanel = new AnimationPanel();
    aniPanel.setPreferredSize(new Dimension(500,500));
    this.add(aniPanel,BorderLayout.CENTER);
  }

  // todo create private setters to send to the animation panel
  // todo AND add method to get the Animation command

  @Override
  public void makeVisible() {
    this.setVisible(true);
  }



}
