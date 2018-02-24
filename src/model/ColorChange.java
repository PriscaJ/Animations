package model;

import java.util.HashMap;

/**
 * Class that handles the shifts in colors within a shape during an animation.
 */
public class ColorChange extends AbstractAnimation {
  private Double[] initialStage;
  private Double[] finalStage;

  public ColorChange(int start, int finish, Double[] initialStage, Double[] finalStage) {
    super(start, finish, new Double[3], new Double[3]);
    // set to an array that is only of length 3
    //this.initialStage = new Double[3];
    //this.finalStage = new Double[3];
  }

  @Override
  public void apply() {
    // how long the move will last for
    int duration = getFinish() - getStart();
    // Point intermediateMove = new Point();

    double rTransition = initialStage[1] - finalStage[1];
    double gTransition = initialStage[2] - finalStage[2];
    double bTransition = initialStage[3] - finalStage[3];


    double rInc = rTransition / duration;
    double gInc = gTransition / duration;
    double bInc = bTransition / duration;
    // distance to goal location broken up into X and Y
    // log the color at each time

    HashMap<Integer, Double[]> hmTcolors = new HashMap<Integer, Double[]>();

    for (double t = getStart(), r = initialStage[1], g = initialStage[2],
         b = initialStage[3]; t < getFinish(); t++, r += rInc, g += gInc, b += bInc) {
      Double[] workArray = new Double[3];
      workArray[1] = r;
      workArray[2] = g;
      workArray[3] = b;
      hmTcolors.put((int) t, workArray);
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
