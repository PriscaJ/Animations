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
    animatingShape.setXPosn(calculateChange(startX, endX, tick));
    animatingShape.setYPosn(calculateChange(startY, endY, tick));
  }

  @Override
  public String getDescription(int tps) {
    return String.format("Shape %s moves from (%.1f,%.1f) to (%.1f,%.1f) from t=%d to t=%d",
        name, startX, startY, endX, endY, startTime * tps, endTime * tps);
  }


}
