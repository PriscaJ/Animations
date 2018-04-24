package cs3500.animator.model;

/**
 * Class that handles the shifts in colors within a shape during an animation.
 */
public class ColorChange extends AbstractAnimation {
  private float oldR;
  private float oldG;
  private float oldB;
  private float newR;
  private float newG;
  private float newB;

  private float ogR;
  private float ogG;
  private float ogB;
  /**
   * Create a colorchange object that will change the color of a shape.
   */
  public ColorChange(String name, float oldR, float oldG, float oldB,
      float newR, float newG, float newB, int startTime, int endTime) {
    super(name, startTime, endTime);
    this.oldR = oldR;
    this.oldG = oldG;
    this.oldB = oldB;
    this.newR = newR;
    this.newG = newG;
    this.newB = newB;
    this.ogR = newR;
    this.ogG = newG;
    this.ogB = newB;

    this.type = AnimationType.COLORCHANGE;
  }

  @Override
  public float getOldR() {
    return oldR;
  }

  @Override
  public float getOldG() {
    return oldG;
  }

  @Override
  public float getOldB() {
    return oldB;
  }

  @Override
  public float getNewR() {
    return newR;
  }

  @Override
  public float getNewG() {
    return newG;
  }

  @Override
  public float getNewB() {
    return newB;
  }

  @Override
  public float getFromRadian() {
    throw new RuntimeException("No radians in a move");
  }

  @Override
  public float getToRadian() {
    throw new RuntimeException("No radians in a move");
  }

  @Override
  public void apply(int tick) {
    if (tick >= startTime && tick < endTime) {
      animatingShape.setRed(calculateChange(oldR, newR, tick));
      animatingShape.setGreen(calculateChange(oldG, newG, tick));
      animatingShape.setBlue(calculateChange(oldB, newB, tick));
    }
  }

  @Override
  public String getDescription(int tps) {
    return String.format("Shape %s changes color from (%.1f,%.1f,%.1f) to (%.1f,%.1f,%.1f) "
            + "from t=%.1fs to t=%.1fs",
        name, oldR, oldG, oldB, newR, newG, newB, (float) startTime * tps / 1000,
            (float) endTime * tps / 1000);
  }

  @Override
  public ColorChange getCopy(Shapes copyShape) {
    ColorChange copy = new ColorChange(name, oldR, oldG, oldB, newR, newG, newB, startTime, endTime);
    copy.setAnimatingShape(copyShape);
    return copy;
  }
}
