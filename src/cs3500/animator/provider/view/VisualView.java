package cs3500.animator.provider.view;

import java.awt.BorderLayout;
import java.awt.Dimension;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.Timer;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;


import cs3500.animator.provider.model.*;

/**
 * Visual view takes the information regarding what the animation contains / does and displays it
 * as a viewable animation that is played in Java. This view has a concept of a timer, which
 * will controller the time of the entire animation. We chose for this to be in the VisualView
 * instead of the controller because only the VisualView has a concept of passable time. Because of
 * this, we put all of the functions that use the timer inside of this view instead of the
 * controller.
 */
public class VisualView extends JFrame implements View, ActionListener {

  private List<Shape> shapes;
  private Timer timer;
  private int tick;

  /**
   * Constructor for Visual View.
   *
   * @param shapes the list of shapes in this animation
   * @param tempo how many ticks per second
   */
  public VisualView(List<Shape> shapes, int tempo) {
    super();

    this.shapes = shapes;
    this.timer = new Timer(1000 / tempo, this);
    this.tick = 0;

    this.setTitle("Animation!");
    this.setSize(700, 700);
    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    this.setLayout(new BorderLayout());
    JPanel panel = new JDraw(shapes);
    panel.setPreferredSize(new Dimension(700, 700));
    JScrollPane scrollPane = new JScrollPane(panel);
    this.add(panel, BorderLayout.CENTER);

    this.pack();
  }

  @Override
  public void showView() {
    this.setVisible(true);
    timer.start();
  }


  @Override
  public void actionPerformed(ActionEvent e) {

    for (Shape shape : shapes) {
      for (Command command : shape.getCommands()) {
        command.convertToTicks(1);
        command.execute(shape, tick);
      }
    }

    repaint();
    tick++;
  }
}
