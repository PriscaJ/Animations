package cs3500.animator.model;

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
    if (tick >= startTime && tick <= endTime) {
      animatingShape.setXPosn(calculateChange(this.startX, this.endX, tick));
      animatingShape.setYPosn(calculateChange(this.startY, this.endY, tick));
    }
  }

  @Override
  public String getDescription(int tps) {
    return String.format("Shape %s moves from (%.1f,%.1f) to (%.1f,%.1f) from t=%.1fs to t=%.1fs",
        name, startX, startY, endX, endY, (float) startTime * tps / 1000, (float) endTime * tps / 1000);
  }


}
