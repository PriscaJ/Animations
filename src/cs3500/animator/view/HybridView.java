package cs3500.animator.view;

import java.util.ArrayList;

import cs3500.animator.model.Shapes;

public class HybridView extends VisualView{

  public HybridView(ArrayList<Shapes> shapes, int endTime, String outputDest, int tps) {
    super(shapes, endTime, tps);
  }

  @Override
  public void makeVisible() {

  }
}
