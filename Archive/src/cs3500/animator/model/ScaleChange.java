package cs3500.animator.model;

/**
 * Class that alters a shapes dimensions when called by its corresponding command by a shape.
 */
public class ScaleChange extends AbstractAnimation {

  /**
   * Constructor for a Scale Change.
   *
   * @param name Name of Shape.
   * @param fromSx Starting dimension.
   * @param fromSy Starting dimension.
   * @param toSx Ending dimension.
   * @param toSy Ending dimension.
   * @param startTime Start time.
   * @param endTime End time.
   */
  public ScaleChange(String name, float fromSx, float fromSy, float toSx, float toSy,
      int startTime, int endTime) {
    super(name, fromSx, fromSy, toSx, toSy, startTime, endTime);
    this.type = AnimationType.SCALECHANGE;
  }

  @Override
  public float getOldR() {
    throw new RuntimeException("No color in a move");
  }

  @Override
  public float getOldG() {
    throw new RuntimeException("No color in a move");
  }

  @Override
  public float getOldB() {
    throw new RuntimeException("No color in a move");
  }

  @Override
  public float getNewR() {
    throw new RuntimeException("No color in a move");
  }

  @Override
  public float getNewG() {
    throw new RuntimeException("No color in a move");
  }

  @Override
  public float getNewB() {
    throw new RuntimeException("No color in a move");
  }

  @Override
  public void apply(int tick) {
    if (tick >= startTime && tick < endTime) {
      animatingShape.setXDimension(calculateChange(startX, endX, tick));
      animatingShape.setYDimension(calculateChange(startY, endY, tick));
    }
  }

  @Override
  public String getDescription(int tps) {
    return String.format("Shape %s scales from Width: %.1f, Height: %.1f to "
            + "Width: %.1f, Height: %.1f from t=%.1fs to t=%.1fs",
        this.name, this.startX, startY, endX, endY,
        (float) startTime * tps / 1000, (float) endTime * tps / 1000);
  }
}
