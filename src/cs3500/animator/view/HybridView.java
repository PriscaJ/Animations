package cs3500.animator.view;

import java.util.ArrayList;

import javax.swing.*;

import cs3500.animator.model.Shapes;

public class HybridView extends JFrame implements IHybridView{
  private VisualView visualView;
  private ArrayList<Shapes> allShapes;
  private ArrayList<Shapes> selectedShapes;
  private int endTime;
  private JPanel buttonPanel;
  private InteractivePanel interactivePanel;
  // looping is set to be false initially
  private boolean looping = false;
  private boolean selecting = false;
  private String outputDest;
  private int tps;


  public HybridView(ArrayList<Shapes> shapes, int endTime, String outputDest, int tps) {
    this.allShapes = shapes;
    this.selectedShapes = shapes;
    this.endTime = endTime;
    this.outputDest = outputDest;
    this.tps = tps;
  }

  // Pause animation
  @Override
  public void stopTimer() {
    interactivePanel.stopTimer();
  }

  @Override
  public void makeVisible() {
    this.start();
  }

  @Override
  public void increaseSpeed() {
    interactivePanel.increaseSpeed();
  }

  @Override
  public void decreaseSpeed() {
    interactivePanel.decreaseSpeed();
  }

  // Start the animation with the initial shapes.
  @Override
  public void start() {
    visualView = new VisualView(selectedShapes, endTime, tps, looping);
    visualView.makeVisible();
  }

  protected void loadShapes() {




  }

  @Override
  public void setLooping() {
    looping = !looping;
  }

  // starting animation from beginning with selected shapes?
  @Override
  public void restart() {
    interactivePanel.setTickToZero();
  }

  // in interface
  @Override
  public void exportSVG() {
    SVGView svgView;
    if (looping) {
      svgView = new SVGView(selectedShapes, outputDest, tps, true);
    } else {
      svgView = new SVGView(selectedShapes, outputDest, tps, false);
    }
    svgView.makeVisible();
  }



}
