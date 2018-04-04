package cs3500.animator.view;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.*;

import cs3500.animator.model.Shapes;

/**
 * Class that creates the visual view it is used in junction with the AnimationPanel class to
 * display a given animation.
 */

public class VisualView extends JFrame implements IInteractiveView {
  protected boolean looping;
  protected int lastTick;
  protected int ticksPerSec;
  private JScrollPane pane;
  private AnimationPanel aniPanel;
  private ArrayList<Shapes> allShapes;
  private ArrayList<Shapes> selectedShapes;
  private JButton incSpeed, decSpeed, stop, start, restart, loop, svgExport, runSelected, resume;
  private JTextField svgFileName;
  private JPanel buttonPanel;
  private JListShape shapeList;
  private JScrollPane scrollingShapes;
  private int initTPS;
  // looping is set to be false initially
  private String svgButtonText = "Type file name here:";
  private JLabel info;

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
    this.lastTick = lastTick;
    this.initTPS = ticksPerSec;
    this.ticksPerSec = ticksPerSec;
    this.allShapes = shapesList;

    //button panel
    buttonPanel = new JPanel();
    buttonPanel.setLayout(new FlowLayout());
    this.add(buttonPanel, BorderLayout.SOUTH);
    System.out.print("buttons placed");

    // set up for buttons

    // speed
    incSpeed = new JButton("Increase Speed");
    incSpeed.setActionCommand("Increase Speed");
    buttonPanel.add(incSpeed);
    //incSpeed.addActionListener((ActionEvent e) -> increaseSpeed());

    decSpeed = new JButton("Decrease Speed");
    decSpeed.setActionCommand("Decrease speed");
    buttonPanel.add(decSpeed);
    //decSpeed.addActionListener((ActionEvent e) -> decreaseSpeed());

    // stop/ start / restart
    stop = new JButton("Stop");
    stop.setActionCommand("Stop");
    buttonPanel.add(stop);
    //stop.addActionListener((ActionEvent e) -> stopTimer());

    resume = new JButton("Resume");
    resume.setActionCommand("Resume");
    buttonPanel.add(resume);

    start = new JButton("Start");
    start.setActionCommand("Start");
    buttonPanel.add(start);
    //start.addActionListener((ActionEvent e) -> start());

    restart = new JButton("Restart");
    restart.setActionCommand("Restart");
    buttonPanel.add(restart);
    loop = new JButton("Looping");
    loop.setActionCommand("Looping");
    //    loop.addActionListener((ActionEvent e) -> {
    //      setLooping();
    //    });
    buttonPanel.add(loop);

    // svg export
    // - text box for output file name
    svgFileName = new JTextField(svgButtonText);
    buttonPanel.add(svgFileName);
    // - jbutton to export
    svgExport = new JButton("Export SVG");
    svgExport.setActionCommand("Export SVG");
    buttonPanel.add(svgExport);

    // run selected
    runSelected = new JButton("Run Selected Shapes");
    runSelected.setActionCommand("Run Selected Shapes");
    buttonPanel.add(runSelected);

    // the list of shapes
    shapeList = new JListShape(shapesList);

    // pane that will display all the shapes
    //scrollingShapes.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    scrollingShapes = new JScrollPane(shapeList);
    scrollingShapes.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    scrollingShapes.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

    this.add(scrollingShapes, BorderLayout.EAST);

    // info about state of animation
    info = new JLabel();
    this.add(info, BorderLayout.NORTH);
    this.pack();
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

  public void setButtonListeners(ActionListener listener) {
    incSpeed.addActionListener(listener);
    decSpeed.addActionListener(listener);
    stop.addActionListener(listener);
    start.addActionListener(listener);
    restart.addActionListener(listener);
    loop.addActionListener(listener);
    svgExport.addActionListener(listener);
    runSelected.addActionListener(listener);
    resume.addActionListener(listener);
  }

  @Override
  public void makeVisible() {
    this.setVisible(true);
    System.out.println("VisualView made visible!");
  }

  @Override
  public void stopTimer() {
    aniPanel.stopTimer();
    info.setText("Animation paused.");
  }

  public void runSelected() {
    // On the next iteration of the looped animation
    // it will run with the selected shapes.
    selectedShapes = (ArrayList<Shapes>) shapeList.getSelected();
    aniPanel.setShapesList(selectedShapes);
    info.setText("Running with selected shapes.");

  }

  @Override
  public void increaseSpeed() {
    aniPanel.increaseSpeed();
    info.setText("Animation speed increased.");

  }

  @Override
  public void decreaseSpeed() {
    aniPanel.decreaseSpeed();
    info.setText("Animation speed decreased.");

  }

  protected void setInfoText(String text) {
    info.setText(text);
  }

  // Start the animation with the initial shapes.
  @Override
  public void start() {
    System.out.print("start plz");
    // creating a new instance outside of panel when declared new
    aniPanel.setShapesList(allShapes);
    aniPanel.setEndTime(lastTick);
    aniPanel.setTPS(initTPS);
    aniPanel.setLooping(looping);
    aniPanel.setTickToZero();
    aniPanel.startTimer();
    this.makeVisible();
    info.setText("Animation started! woohoo!");

    // visualView = new VisualView(allShapes, endTime, tps, looping);
    //    visualView.shapesList = allShapes;
    //    visualView.lastTick = endTime;
    //    visualView.ticksPerSec = tps;
    //    visualView.looping = looping;
    //    visualView.makeVisible();
  }

  @Override
  public void setLooping() {
    // toggle between turning looping on and off
    // aniPanel.setLooping(!looping);
    if (looping) {
      looping = !looping;
      aniPanel.setLooping(looping);
      info.setText("Animation is not looping.");

    } else {
      looping = true;
      aniPanel.setLooping(looping);
      info.setText("Animation is looping.");

    }
  }

  // starting animation from beginning with selected shapes?
  @Override
  public void restart() {
    aniPanel.setTickToZero();
    info.setText("Animation restarted.");

  }

  @Override
  public void resume() {
    aniPanel.startTimer();
    info.setText("Animation resumed.");
  }

  public int getSpeed() {
    return aniPanel.getSpeed();
  }

  public ArrayList<Shapes> getSelectedShapes() {
    if (shapeList.getSelected() == null) {
      return allShapes;
    }
    else {
      return (ArrayList<Shapes>) shapeList.getSelected();
    }
  }

  public void setSelectedShapes(ArrayList<Shapes> selectedShapes) {
    this.selectedShapes = selectedShapes;
    aniPanel.setShapesList(selectedShapes);
  }

  public String getFileName() {
    return svgFileName.getText();
  }
}

