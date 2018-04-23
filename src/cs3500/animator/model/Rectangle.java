package cs3500.animator.model;

/**
 * This is a class to represent a rectangle in an animation.
 */
public class Rectangle extends AbstractShape {

  /**
   * Constructor for a Rectangle.
   *
   * @param name The unique name of this Rectangle.
   * @param xPosn The x position for this Rectangle.
   * @param yPosn The y position for this rectangle.
   * @param xDimension The width of the rectangle.
   * @param yDimension The height of the rectangle.
   * @param red The r value of color.
   * @param green The g value of color.
   * @param blue The b value of color.
   * @param startOfLife When this rectangle appears.
   * @param endOfLife When this rectangle disappears.
   */
  public Rectangle(String name, float xPosn, float yPosn, float xDimension, float yDimension,
      float red, float green, float blue, int startOfLife, int endOfLife, int layer, float radian) {
    super(name, xPosn, yPosn, xDimension, yDimension, red, green, blue, startOfLife, endOfLife, layer, radian);
  }


  @Override
  public Shapes getCopy() {
    return new Rectangle(name, xPosn, yPosn, xDimension, yDimension, red, green, blue,
        startOfLife, endOfLife, layer, radian);
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
  public float getCenterX() {
    return this.xPosn + xDimension / 2 ;
  }

  @Override
  public float getCenterY() {
    return this.yPosn - yDimension / 2;
  }

  @Override
  public String getDescription(int tps) {
    return String.format("Name: %s\n"
            + "Type: rectangle\n"
            + "Min-corner: (%.1f,%.1f), Width: %.1f, Height: %.1f, "
            + "Color: (%.1f,%.1f,%.1f)\n"
            + "Appears at t=%.1fs\n"
            + "Disappears at t=%.1fs",
        name, xPosn, yPosn, xDimension, yDimension, red, green, blue,
        (float) startOfLife * tps / 1000, (float) endOfLife * tps / 1000);
  }
}
