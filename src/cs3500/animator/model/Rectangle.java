package cs3500.animator.model;

public class Rectangle extends AbstractShape{


  public Rectangle(String name, float xPosn, float yPosn, float xDimension, float yDimension,
      float red, float green, float blue, int startOfLife, int endOfLife) {
    super(name, xPosn, yPosn, xDimension, yDimension, red, green, blue, startOfLife, endOfLife);
  }

  @Override
  public String getDescription() {
    return String.format("Name: %s\n"
            + "Type: rectangle\n"
            + "Min-corner: (%.1f,%.1f), Width: %.1f, Height: %.1f, Color: (%.1f,%.1f,%.1f)\n"
            + "Appears at t=%d\n"
            + "Disappears at t=%d",
        name, xPosn, yPosn, xDimension, yDimension, red, green, blue, startOfLife, endOfLife);
  }
}
