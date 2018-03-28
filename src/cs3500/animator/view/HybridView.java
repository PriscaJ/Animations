package cs3500.animator.view;

import java.util.ArrayList;

import cs3500.animator.model.Shapes;

public class HybridView implements IHybridView {
  private VisualView visualView;
  private ArrayList<Shapes> allShapes;
  private ArrayList<Shapes> selectedShapes;
  private int endTime;
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
    visualView.stopTimer();
  }

  @Override
  public void makeVisible() {
    this.start();
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
    visualView = new VisualView(selectedShapes, endTime, tps, looping);
    visualView.makeVisible();
  }

  @Override
  public void setLooping() {
    looping = !looping;
  }

  // starting animation from beginning with selected shapes?
  @Override
  public void restart() {
    visualView.setTickToZero();
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

  public void startSelection() {
    selecting = true;
  }
  // occurs on click, mouse x and y coord provided
  @Override
  public void selectShape(int xCoord, int yCoord) {
    // selectedShapes = new ArrayList<>();

    selectedShapes.add();
  }

  private Shapes selectShape(int xCoord, int yCoord) {

  }

  // if the user has selected shapes
  // once the user has


}
