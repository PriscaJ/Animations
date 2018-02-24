package model;

import java.awt.*;
import java.util.HashMap;

import javafx.util.Pair;

/**
 * Class that alters a shapes dimensions when called by its corresponding command by a shape.
 */
public class ScaleChange extends AbstractAnimation {
  private Pair<Double, Double> initialStage;
  private Pair<Double, Double> finalStage;

  public ScaleChange(int start, int finish,
                     Pair<Double, Double> initialStage, Pair<Double, Double> finalStage) {
    super(start, finish, initialStage, finalStage);
    //this.initialStage = initialStage;
    //this.finalStage = finalStage;
  }

  @Override
  public void apply() {
    // how long the move will last for
    int duration = getFinish() - getStart();
    Point intermediateMove = new Point();

    // distance to goal location broken up into X and Y
    double xScale = initialStage.getKey() - finalStage.getKey();
    double yScale = initialStage.getValue() - finalStage.getValue();

    // how much the shape should move at each time t
    double incrementX = xScale / duration;
    double incrementY = yScale / duration;

    HashMap<Integer, Pair> hmTscale = new HashMap<Integer, Pair>();

    for (int t = getStart(),
         x = initialStage.getKey().intValue(), y = initialStage.getValue().intValue();
         t < getFinish(); t++, x += incrementX, y += incrementY) {
      //new Pair<Double, Double>((double) x, (double) y);
      hmTscale.put(t, new Pair<Double, Double>((double) x, (double) y));
    }
  }

  @Override
  public String toString() {
    StringBuilder workString = new StringBuilder();

    workString.append(" changes from " + getInitialStage()
            + " to " + getFinalStage()
            + "from t=" + getStart()
            + "to t=" + getFinish());
    return workString.toString();
  }
}
