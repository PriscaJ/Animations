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
  private String name;
  private ShapeType type;
  private double width, height;
  private int appears, disappears;
  private Point position;
  private Color color;

  public Shape(String name, ShapeType type, double width, double height,
               int appears, int disapears, Point position, Color color) {
    this.commands = new ArrayList<AnimationCommand>();
    this.name = name;
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
    // does the action applied to the shape occur during the lifespan of the shape
    if(newCommand.getAnimation().getStart() < getAppears()) {
      throw new IllegalArgumentException("Cannot apply Action to shape");
    }
    if(newCommand.getAnimation().getFinish() > getDisappears()) {
      throw new IllegalArgumentException("Cannot apply Action to shape");
    }
    commands.add(newCommand);
  }

  @Override
  public void animateShape() {
    for (int i = 0; i < commands.size() - 1; i++) {
      commands.get(i).execute();
    }
  }

  @Override
  public ArrayList<AnimationCommand> getCommands() {
    return commands;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public ShapeType getType() {
    return type;
  }

  @Override
  public Double getWidth() {
    return width;
  }

  @Override
  public Double getHeight() {
    return height;
  }

  @Override
  public int getAppears() {
    return appears;
  }

  @Override
  public int getDisappears() {
    return disappears;
  }

  @Override
  public Point getPosition() {
    return position;
  }

  @Override
  public Color getColor() {
    return color;
  }

  @Override
  public String toString() {
    StringBuilder workString = new StringBuilder();
    workString.append("Name: " + getName()
            + "\nType: " + getType().toString());
    if (getType().equals(ShapeType.OVAL)) {
      workString.append("\nCenter: " + getPosition()
              + ", X radius: " + getWidth()
              + ", Y radius: " + getHeight()
              + ", Color: " + getColor());
    }
    else {
      workString.append("\nLower-left corner: ("
              + getPosition().getX() + " , " + getPosition().getY() + ") "
              + ", Width: " + getWidth()
              + ", Height: " + getHeight());
    }
    workString.append("\nAppears at t=" + getAppears()
    + "\nDisappears at t=" + getDisappears());
    for (AnimationCommand ac: getCommands()) {
      workString.append("\n" + getName() + " " + ac.toString());
    }
    return workString.toString();
  }

}
