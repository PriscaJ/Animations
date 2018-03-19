package cs3500.animator.model;

import java.util.HashMap;

/**
 * Class that handles the shifts in colors within a shape during an animation.
 */
public class ColorChange extends AbstractAnimation {
  private AbstractShape s;
  private float oldR;
  private float oldG;
  private float oldB;
  private float newR;
  private float newG;
  private float newB;

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
  }

  public float getOldR() {
    return oldR;
  }

  public float getOldG() {
    return oldG;
  }

  public float getOldB() {
    return oldB;
  }

  public float getNewR() {
    return newR;
  }

  public float getNewG() {
    return newG;
  }

  public float getNewB() {
    return newB;
  }


  @Override
  public void apply(int tick) {
    s.red = calculateChange(oldR, newR, tick);
    s.green = calculateChange(oldG, newG, tick);
    s.blue = calculateChange(oldB, newB, tick);
  }

  @Override
  public String getDescription() {
    return String.format("Shape %s changes color from (%.1f,%.1f,%.1f) to (%.1f,%.1f,%.1f) "
            + "from t=%d to t=%d",
        name, oldR, oldG, oldB, newR, newG, newB, startTime, endTime);
  }
}
