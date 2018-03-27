package cs3500.animator.view;

import java.util.ArrayList;

import cs3500.animator.model.Shapes;

public class HybridView implements IHybridView{
  private VisualView visualView;
  private ArrayList<Shapes> allShapes;
  private ArrayList<Shapes> selectedShapes;
  private int endTime;
  // looping is set to be false initially
  private boolean looping = false;
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

  // Reset animation = loop with current shapes
  @Override
  public void setTickToZero() {
    visualView.setTickToZero();
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
    visualView = new VisualView(allShapes, endTime, tps, looping);
    visualView.makeVisible();
  }

  @Override
  public void setLooping() {
    looping = !looping;
  }

  // pause
  // this.timer.stop()
  // visualView.stopTimer() --> stop the animation panel's timer

  // resume()
  // this.timer.start()

  // restart()
  // view.resetTicks();
  // starting animation from beginning with selected shapes?




  // in interface
  @Override
  public void exportSVG() {
    SVGView svgView;
    if (looping) {
      svgView = new SVGView(selectedShapes, outputDest, tps, true);
    }
    else {
      svgView = new SVGView(selectedShapes, outputDest, tps, false);
    }
    svgView.makeVisible();
  }

  // if the user has selected shapes
  // once the user has


}
