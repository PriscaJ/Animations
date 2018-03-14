package model;

import java.awt.Point;
import java.util.HashMap;

/**
 * Class that handles moving a shape in an animation coupled to the shape by its command.
 */
public class Move extends AbstractAnimation {
  private Point initialStage;
  private Point finalStage;

  /**
   * Constructor.
   * @param start start
   * @param finish finish
   * @param initialStage inital
   * @param finalStage finish
   */
  public Move(int start, int finish, Point initialStage, Point finalStage) {
    super(start, finish, initialStage, finalStage);
    //this.initialStage = initialStage;
    //this.finalStage = finalStage;
  }


  @Override
  public void apply() {
    // how long the move will last for
    int duration = getFinish() - getStart();
    // Point intermediateMove = new Point();

    // distance to goal location broken up into X and Y
    double distX = initialStage.getX() - finalStage.getX();
    double distY = initialStage.getY() - finalStage.getY();

    // how much the shape should move at each time t
    double incrementX = distX / duration;
    double incrementY = distY / duration;

    for (int i = 0; i < duration; i++) {
      // todo fix the apply method
    }

    // todo change the shape's position to the start position of the move
    // it can be "teleported" into this spot
    // curr.p = this.start; // mutating the inital position


    // create a hashmap of all the positions while transitioning
    // from one position to the next within the amount of time.
    HashMap<Integer, Point> hmTpoint = new HashMap<Integer, Point>();

    for (int t = getStart(), x = initialStage.x, y = initialStage.y;
         t < getFinish(); t++, x += incrementX, y += incrementY) {
      hmTpoint.put(t, new Point((int) x, (int) y));
    }
  }

  @Override
  public String toString() {
    StringBuilder workString = new StringBuilder();
    workString.append("moves from " + getInitialStage()
            +" to " + getFinalStage()
            + "from time t=" + getStart()
            + " to t=" + getFinish());
    return workString.toString();
  }


}
