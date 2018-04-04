package cs3500.animator.view;

import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

import cs3500.animator.model.Shapes;

public class HybridView implements IInteractiveView {
  private VisualView visualView;
  private ArrayList<Shapes> selectedShapes;
  // looping is set to be false initially
  private String outputDest;
  private String svgButtonText = "Type file name here:";

  public HybridView(ArrayList<Shapes> shapes, int endTime, String outputDest, int tps) {
    super();
    this.visualView = new VisualView(shapes, endTime, tps);
    this.selectedShapes = shapes;
    this.outputDest = outputDest;
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
  public void resume() {
    visualView.resume();
  }

  @Override
  public void setLooping() {
    // toggle between turning looping on and off
    visualView.setLooping();
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
    if (visualView.getSelectedShapes() != null && visualView.getSelectedShapes().size() > 0) {
      selectedShapes = visualView.getSelectedShapes();
    }
    if (!(visualView.getFileName().equals(svgButtonText) || visualView.getFileName().equals(""))) {
      outputDest = visualView.getFileName();
    } else {
      outputDest = "out";
    }
    if (visualView.looping) {
      svgView = new SVGView(selectedShapes, outputDest, visualView.getSpeed(), true);
    } else {
      svgView = new SVGView(selectedShapes, outputDest, visualView.getSpeed(), false);
    }
    svgView.setEndTime(visualView.lastTick);
    svgView.makeVisible();
    visualView.setInfoText("SVG exported as " + outputDest);
  }

  @Override
  public void runSelected() {
    visualView.setSelectedShapes(visualView.getSelectedShapes());
  }
}
