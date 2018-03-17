package cs3500.animator.model;

public class Oval extends AbstractShape {
  /**
   * This constructor initializes the oval with the necessary info.
   */
  public Oval(String name,
      float cx, float cy, float xRadius, float yRadius,
      float red, float green, float blue,
      int startOfLife, int endOfLife) {
    super(name, cx, cy, xRadius, yRadius, red, green, blue, startOfLife, endOfLife);
  }

  @Override
  public String getDescription() {
    return String.format("Name: %s\n"
            + "Type: oval\n"
            + "Center: (%.1f,%.1f), X radius: %.1f, Y radius: %.1f, Color: (%.1f,%.1f,%.1f)\n"
            + "Appears at t=%d\n"
            + "Disappears at t=%d",
        name, xPosn, yPosn, xDimension, yDimension, red, green, blue, startOfLife, endOfLife);
  }
}
