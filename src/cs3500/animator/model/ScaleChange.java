package cs3500.animator.model;

import java.awt.Point;
import java.util.HashMap;

import javafx.util.Pair;

/**
 * Class that alters a shapes dimensions when called by its corresponding command by a shape.
 */
public class ScaleChange extends AbstractAnimation {

  // ADD CONSTRUCTOR COMMENT HERE
  public ScaleChange(String name, float fromSx, float fromSy, float toSx, float toSy,
      int startTime, int endTime) {
    super(name, fromSx, fromSy, toSx, toSy, startTime, endTime);
    this.type = AnimationType.SCALECHANGE;
  }

  @Override
  public void apply(int tick) {

  }

  @Override
  public String getDescription() {
    return String.format("Shape %s scales from Width: %.1f, Height: %.1f to "
            + "Width: %.1f, Height: %.1f from t=%d to t=%d",
        this.name, this.startX, startY, endX, endY, startTime, endTime);
  }
}
