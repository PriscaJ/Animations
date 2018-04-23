package cs3500.animator.model;

/**
 * Class that represents an Oval a type of shape in an animation.
 */
public class Oval extends AbstractShape {
  /**
   * This constructor initializes the oval with the necessary info.
   */
  public Oval(String name,
      float cx, float cy, float xRadius, float yRadius,
      float red, float green, float blue,
      int startOfLife, int endOfLife, int layer, float radian) {
    super(name, cx, cy, xRadius, yRadius, red, green, blue,
        startOfLife, endOfLife, layer, radian);
  }

  @Override
  public boolean isOval() {
    return true;
  }

  @Override
  public boolean isRect() {
    return false;
  }

  @Override
  public Shapes getCopy() {
    return new Oval(name, xPosn, yPosn, xDimension, yDimension, red, green, blue,
        startOfLife, endOfLife, layer, radian);
  }

  @Override
  public float getCenterX() {
    return xPosn;
  }

  @Override
  public float getCenterY() {
    return yPosn;
  }

  @Override
  public String getDescription(int tps) {
    return String.format("Name: %s\n"
            + "Type: oval\n"
            + "Center: (%.1f,%.1f), X radius: %.1f, Y radius: %.1f, "
            + "Color: (%.1f,%.1f,%.1f)\n"
            + "Appears at t=%.1fs\n"
            + "Disappears at t=%.1fs",
        name, xPosn, yPosn, xDimension, yDimension, red, green, blue,
        (float) startOfLife * tps / 1000, (float) endOfLife * tps / 1000);
  }
}
