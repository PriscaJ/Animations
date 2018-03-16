package cs3500.animator.model;

import java.awt.Point;
import java.util.HashMap;

/**
 * Class that handles moving a shape in an animation coupled to the shape by its command.
 */
public class Move extends AbstractAnimation {

  /**
   * This is the constructor for a move. It initializes the fields!
   */
  public Move(String name, float moveFromX, float moveFromY, float moveToX, float moveToY,
      int startTime, int endTime) {
    super(name, moveFromX, moveFromY, moveToX, moveToY, startTime, endTime);
    this.type = AnimationType.MOVE;
  }

  @Override
  public void apply(int tick) {
    // how long the move will last for
    int duration = getFinish() - getStart();
    // Point intermediateMove = new Point();

    // distance to goal location broken up into X and Y
    double distX = calculateChange(startX, endX, tick);
    double distY = calculateChange(startY, endY, tick);
  }

  @Override
  public String getDescription() {
    return String.format("Shape %s moves from (%.1f,%.1f) to (%.1f,%.1f) from t=%d to t=%d",
        name, startX, startY, endX, endY, startTime, endTime);
  }

}
