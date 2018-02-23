package model;

import java.awt.Point;
import java.awt.Color;
import java.util.ArrayList;

/**
 * Class that shapes can extend to keep similar functionality in one place.
 */

public class Shape implements Shapes {
  // the animations that the shape invokes
  private ArrayList<AnimationCommand> commands;
  // qualities of a shape
  private ShapeType type;
  private int width, height, appears, disappears;
  private Point position;
  private Color color;

  public Shape(ShapeType type, int width, int height,
               int appears, int disapears, Point position, Color color) {
    this.commands = new ArrayList<AnimationCommand>();
    this.type = type;
    this.width = width;
    this.height = height;
    this.appears = appears;
    this.disappears = disapears;
    this.position = position;
    this.color = color;


  }

  @Override
  public void setAnimation(AnimationCommand newCommand) {
    commands.add(newCommand);
  }

  @Override
  public void animateShape() {
    for (int i = 0; i < commands.size() - 1; i++) {
      commands.get(i).execute();
    }
  }

  @Override
  public String toString() {
    return "";
  }

}
