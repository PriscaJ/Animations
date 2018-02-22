package model;

import java.awt.Point;
import java.awt.Color;
import java.util.ArrayList;

/**
 * Class that shapes can extend to keep similar functionality in one place.
 */

// simple version
  // todo make a list or array of commands that the shape invokes
public class Shape {
  // the animations that the shape invokes
  private ArrayList<AnimationCommand> commands;
  // qualities of a shape
  private ShapeType type;
  private int width, height, appears, disappears;
  private Point x, y;
  private Color color;

  public Shape() {

  }

  public void setAnimation(AnimationCommand newCommand) {
    commands.add(newCommand);
  }

  public void animateShape() {
    for (int i = 0; i < commands.size() - 1; i++) {
      commands.get(i).execute();
    }

  }

}
