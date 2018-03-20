package cs3500.animator.model;

/**
 * This is a class to represent a rectangle in an animation.
 */
public class Rectangle extends AbstractShape{


  public Rectangle(String name, float xPosn, float yPosn, float xDimension, float yDimension,
      float red, float green, float blue, int startOfLife, int endOfLife) {
    super(name, xPosn, yPosn, xDimension, yDimension, red, green, blue, startOfLife, endOfLife);
  }

  @Override
  public boolean isOval() {
    return false;
  }

  @Override
  public boolean isRect() {
    return true;
  }

  @Override
  public String getDescription(int tps) {
    return String.format("Name: %s\n"
            + "Type: rectangle\n"
            + "Min-corner: (%.1f,%.1f), Width: %.1f, Height: %.1f, Color: (%.1f,%.1f,%.1f)\n"
            + "Appears at t=%.1fs\n"
            + "Disappears at t=%.1fs",
        name, xPosn, yPosn, xDimension, yDimension, red, green, blue, (float)startOfLife * tps /1000, (float)endOfLife * tps /1000);
  }
}
