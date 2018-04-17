package cs3500.animator.provider.view;

import com.sun.tools.corba.se.idl.toJavaPortable.Helper;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.WindowConstants;

import cs3500.animator.provider.misc.IHelper;
import cs3500.animator.provider.model.Command;
import cs3500.animator.provider.model.Shape;

//import cs3500.commands.Command;
//import cs3500.animator.provider.model.Shape;

/**
 * Hybrid view takes the information regarding what the animation contains / does and displays it
 * as a viewable animation that is played in Java. It also allows the user to manipulate the
 * animation by pausing, resuming, restarting, changing whether or not the animation loops,
 * hiding or displaying a specific shape and speeding up and slowing down. It also provides the
 * ability for the user to enter a path and save an svg describing the animation.
 */
public class HybridView extends JFrame implements View, ActionListener {

  private List<Shape> shapes;
  private final List<Shape> initialShapes;
  private JPanel panel;
  private Timer timer;
  private int tick;
  private JTextField shapeName;
  private String findShape;
  private JButton loop;
  private JTextField updateSpeedText;
  private String loopbackString;
  private double animationEndTime;
  private JTextField path;

  /**
   * Constructor for Hybrid View.
   * @param shapes the shapes that make up this animation
   * @param tempo how many ticks per second
   */
  public HybridView(List<Shape> shapes, int tempo) throws IOException {
    super();

    this.shapes = shapes;
    this.timer = new Timer(1000 / tempo, this);
    this.tick = 0;
    this.loopbackString =  "Off";

    this.initialShapes = new ArrayList<>();
    deepCopyShapes(initialShapes, shapes);

    this.animationEndTime = IHelper.findEndTimeOfAnimation(shapes);

    this.setTitle("Hybrid Animation!");
    this.setSize(700,700);
    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    this.setLayout(new BorderLayout());
    panel = new JDraw(shapes);
    panel.setPreferredSize(new Dimension(700,700));
    this.add(panel,BorderLayout.CENTER);

    JPanel buttonPanel = new JPanel();
    buttonPanel.setLayout(new GridLayout(2,5));
    this.add(buttonPanel,BorderLayout.SOUTH);

    JButton startFromBeginning;
    startFromBeginning = new JButton("Start From Beginning");
    startFromBeginning.addActionListener((ActionEvent e) -> {
      startFromBeginning();
    });

    JButton pause = new JButton("Pause");
    pause.addActionListener((ActionEvent e) -> {
      timer.stop();
    });

    JButton resume = new JButton("Resume");
    resume.addActionListener((ActionEvent e) -> {
      timer.start();
    });

    loop = new JButton("Loopback: " + loopbackString);
    loop.addActionListener((ActionEvent e) -> {
      toggleLoopbackButtonName();
    });

    updateSpeedText = new JTextField(Integer.toString(tempo));
    updateSpeedText.setColumns(4);
    updateSpeedText.setHorizontalAlignment(JTextField.CENTER);

    JButton updateSpeed = new JButton("Update Speed");
    updateSpeed.addActionListener((ActionEvent e) -> {

      int newSpeed = new Integer(updateSpeedText.getText());
      timer.setDelay(1000 / newSpeed);
    });

    shapeName = new JTextField("Enter Shape To Show/Hide");
    shapeName.setHorizontalAlignment(JTextField.CENTER);

    JButton show = new JButton("Show Shape");
    show.addActionListener((ActionEvent e) -> {

      findShape = shapeName.getText();

      // find shape with the name given in shapeNameString
      // set its visibility to true
      toggleVisibility(findShape, true);
      panel.repaint();

    });

    JButton hide = new JButton("Hide Shape");
    hide.addActionListener((ActionEvent e) -> {

      findShape = shapeName.getText();

      // find shape with the name given in shapeNameString
      // set its visibility to true
      toggleVisibility(findShape, false);
      panel.repaint();


    });

    JButton svg = new JButton("Save SVG");
    svg.addActionListener((ActionEvent e) -> {

      String svgText = IHelper.printSVG(this.shapes, true, tempo);

      if (path.getText().equals("")) {
        System.out.println(svgText);
      } else {
        try {
          IHelper.writeToFile(svgText, path.getText());
        } catch (IOException e1) {
          e1.printStackTrace();
        }
      }

    });

    path = new JTextField("Enter Path For SVG");
    path.setHorizontalAlignment(JTextField.CENTER);

    JPanel showHidePanel = new JPanel();
    showHidePanel.setLayout(new GridLayout(1,2));
    showHidePanel.add(show);
    showHidePanel.add(hide);

    // Row 1
    buttonPanel.add(startFromBeginning);
    buttonPanel.add(pause);
    buttonPanel.add(updateSpeed);
    buttonPanel.add(showHidePanel);
    buttonPanel.add(svg);

    // Row 2
    buttonPanel.add(loop);
    buttonPanel.add(resume);
    buttonPanel.add(updateSpeedText);
    buttonPanel.add(shapeName);
    buttonPanel.add(path);

    this.pack();
  }

  /**
   * Find a specific shape based on its name and either turn its visibility on or off.
   * @param findShape the name of the shape to be affected
   * @param visible true if the shape should be visible
   */
  private void toggleVisibility(String findShape, boolean visible) {
    for (Shape s: shapes) {
      if (s.getName().toLowerCase().equals(findShape.toLowerCase())) {
        s.setVisibility(visible);
      }
    }
  }

  /**
   * Make a deep copy of the list of shapes that is identical, but cannot be edited because
   * it is not a reference.
   * @param shapesTarget the new list of shapes
   * @param shapesToCopy the original list of shapes
   */
  private void deepCopyShapes(List<Shape> shapesTarget, List<Shape> shapesToCopy) {
    for (Shape shape : shapesToCopy) {
      shapesTarget.add(shape.clone());
    }
  }

  /**
   * Make a deep copy of the list of shape's visible that is identical, but cannot be edited
   * because it is not a reference.
   * @param listToGetCopy the new list of visibilities
   * @param listToCopy the old list of visibilities.
   */
  private void copyVisibility(List<Shape> listToGetCopy, List<Shape> listToCopy) {
    for (int i = 0; i < shapes.size(); i++) {
      listToGetCopy.get(i).setVisibility(listToCopy.get(i).getVisibility());
    }
  }

  @Override
  public void showView() {
    this.setVisible(true);
  }

  @Override
  public void actionPerformed(ActionEvent e) {

    for (Shape shape : shapes) {
      for (Command command: shape.getCommands()) {
        command.convertToTicks(1);
        command.execute(shape, tick);
      }
    }

    repaint();
    if (tick < animationEndTime) {
      tick++;
    } else if (loopbackString.equals("On")) {
      startFromBeginning();
    } else {
      timer.stop();
    }
  }

  /**
   * Switch the text on the loopback button based on the current state of the button.
   */
  private void toggleLoopbackButtonName() {
    if (loopbackString.equals("Off")) {
      loopbackString = "On";
    }
    else {
      loopbackString = "Off";
    }
    loop.setText("Loopback: " + loopbackString);
  }

  /**
   * Restart the entire animation from the beginning.
   */
  private void startFromBeginning() {
    List<Shape> currentShapes = new ArrayList<>();
    deepCopyShapes(currentShapes, shapes);

    copyVisibility(currentShapes, shapes);

    shapes.clear();
    deepCopyShapes(shapes, initialShapes);

    copyVisibility(shapes, currentShapes);

    tick = 0;
    timer.start();
  }
}

