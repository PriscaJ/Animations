package cs3500.animator.view;

import java.util.ArrayList;
import java.util.List;

import cs3500.animator.model.Shapes;

public class HybridView implements IView{
  VisualView visualView;
  SVGView svgView;
  List<Shapes> allShapes;
  List<Shapes> selectedShapes;
  int endTime;
  boolean looping;

  public HybridView(ArrayList<Shapes> shapes, int endTime, String outputDest, int tps) {
    this.allShapes = shapes;
    this.selectedShapes = shapes;
    this.endTime = endTime;
    this.visualView = new VisualView(shapes, endTime, tps, looping);
    this.svgView = new SVGView(shapes, outputDest, tps, looping);
  }

  @Override
  public void makeVisible() {
    visualView.makeVisible();
  }


  void getTick() {
    tick = visualView.getTick();
    if (tick = endTime) {
      visualView.resetTicks();
    }
  }

  // if tick = last tick --> reset tick

  // check if the visualView's animationPanel's tick is at the last tick
  // if (looping) { reset the tick }
  // else {end program}

  // start()
  // tick = 0;
  // timer.start()

  // increaseSpeed()
  // this.timer = new Timer(oaweefa)

  // pause
  // this.timer.stop()
  // visualView.stopTimer() --> stop the animation panel's timer

  // resume()
  // this.timer.start()

  // restart()
  // view.resetTicks();
  // starting animation from beginning with selected shapes?




  // in interface
  public void exportSVG() {
    if (looping) {

    }
    svgView = new SVGView(selectedShapes, outputDest, tps);
    svgView.makeVisible();
  }

  // if the user has selected shapes
  // once the user has


}
