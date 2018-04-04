package cs3500.animator.view;

import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

import cs3500.animator.model.Shapes;

public class HybridView implements IInteractiveView {
  private VisualView visualView;
  private ArrayList<Shapes> allShapes;
  private ArrayList<Shapes> selectedShapes;
  private int endTime;
  private JTextField svgFileName;
  private JPanel buttonPanel;
  private JListShape shapeList;
  // looping is set to be false initially
  private boolean looping = false;
  private String outputDest;
  private int tps;
  private String svgButtonText = "Type file name here:";

  private JScrollPane pane;


  public HybridView(ArrayList<Shapes> shapes, int endTime, String outputDest, int tps) {
    super();
    this.visualView = new VisualView(shapes, endTime, tps);
    this.allShapes = shapes;
    this.selectedShapes = shapes;
    this.endTime = endTime;
    this.outputDest = outputDest;
    this.tps = tps;
  }

  // Pause animation
  @Override
  public void stopTimer() {
    visualView.stopTimer();
  }

  public void setButtonListeners(ActionListener listener) {
    visualView.setButtonListeners(listener);
  }

  @Override
  public void makeVisible() {
    visualView.setVisible(true);
  }

  @Override
  public void increaseSpeed() {
    visualView.increaseSpeed();

  }

  @Override
  public void decreaseSpeed() {
    visualView.decreaseSpeed();
  }

  // Start the animation with the initial shapes.
  @Override
  public void start() {
    visualView.start();
  }

  @Override
  public void setLooping() {
    // toggle between turning looping on and off
    looping = !looping;
  }

  // starting animation from beginning with selected shapes?
  @Override
  public void restart() {
    visualView.restart();
  }


  /**
   * Turn the current animation into an SVG.
   */
  // in interface
  // ON BUTTON CLICK, THE OUTPUT DEST SHOULD BE UPDATED BY WHAT THE USER SPECIFIES
  public void exportSVG() {
    SVGView svgView;
    if (shapeList.getSelected() != null) {
      selectedShapes = (ArrayList<Shapes>) shapeList.getSelected();
    }
    if (!(svgFileName.getText().equals(svgButtonText) || svgFileName.getText().equals(""))) {
      outputDest = svgFileName.getText();
    }
    else {
      outputDest = "out";
    }
    if (looping) {
      svgView = new SVGView(selectedShapes, outputDest, visualView.getSpeed(), true);
    } else {
      svgView = new SVGView(selectedShapes, outputDest, visualView.getSpeed(), false);
    }
    svgView.setEndTime(endTime);
    svgView.makeVisible();
  }

  @Override
  public void runSelected() {
    if (looping) {
      // On the next iteration of the looped animation
      // it will run with the selected shapes.
      selectedShapes = (ArrayList<Shapes>) shapeList.getSelected();
    }
    // otherwise do nothing

  }
}
