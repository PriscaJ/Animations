package cs3500.animator.model;

import java.util.ArrayList;

/**
 * Class that shapes can extend to keep similar functionality in one place.
 */

public abstract class AbstractShape implements Shapes {
  // qualities of a shape
  protected String name;
  protected float xPosn;
  protected float yPosn;
  protected float xDimension;
  protected float yDimension;
  protected float red;
  protected float green;
  protected float blue;
  protected float radian;
  protected int startOfLife;
  protected int endOfLife;
  protected int layer;
  // the animations that the shape invokes
  protected ArrayList<AnimationCommand> commands = new ArrayList<>();

  /**
   * Constructor for a shape.
   *
   * @param name shape name.
   * @param xPosn Shape postion.
   * @param yPosn Shape y position.
   * @param xDimension The width of a Shape.
   * @param yDimension The Height of a Shape.
   * @param red The red color.
   * @param green The green color.
   * @param blue The blue color.
   * @param startOfLife The appears time.
   * @param endOfLife The disappears time.
   */
  public AbstractShape(String name,
                       float xPosn, float yPosn, float xDimension, float yDimension,
                       float red, float green, float blue,
                       int startOfLife, int endOfLife) {
    this.name = name;
    this.xPosn = xPosn;
    this.yPosn = yPosn;
    this.xDimension = xDimension;
    this.yDimension = yDimension;
    this.red = red;
    this.green = green;
    this.blue = blue;
    this.startOfLife = startOfLife;
    this.endOfLife = endOfLife;
    this.layer = 1;
    this.radian = 0;
  }

  // constructor that handles the layers and radians
  public AbstractShape(String name,
      float xPosn, float yPosn, float xDimension, float yDimension,
      float red, float green, float blue,
      int startOfLife, int endOfLife, int layer, float radian) {
    this.name = name;
    this.xPosn = xPosn;
    this.yPosn = yPosn;
    this.xDimension = xDimension;
    this.yDimension = yDimension;
    this.red = red;
    this.green = green;
    this.blue = blue;
    this.startOfLife = startOfLife;
    this.endOfLife = endOfLife;
    this.layer = layer;
    this.radian = radian;
  }

  @Override
  public void addCommand(AnimationCommand c) {
    commands.add(c);
  }

  @Override
  public void setAnimation(AnimationCommand newCommand) {
    // does the action applied to the shape occur during the lifespan of the shape
    if (newCommand.getAnimation().getStart() < getAppears()) {
      throw new IllegalArgumentException("Cannot apply Action to shape");
    }
    if (newCommand.getAnimation().getFinish() > getDisappears()) {
      throw new IllegalArgumentException("Cannot apply Action to shape");
    }
    commands.add(newCommand);
  }

  @Override
  public int getLayer() {
    return this.layer;
  }

  @Override
  public ArrayList<AnimationCommand> getCommands() {
    return commands;
  }

  @Override
  public void setXPosn(float v) {
    this.xPosn = v;
  }

  @Override
  public void setYPosn(float v) {
    this.yPosn = v;
  }

  @Override
  public void setXDimension(float v) {
    this.xDimension = v;
  }

  @Override
  public void setYDimension(float v) {
    this.yDimension = v;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public Float getWidth() {
    return xDimension;
  }

  @Override
  public Float getHeight() {
    return yDimension;
  }

  @Override
  public int getAppears() {
    return startOfLife;
  }

  @Override
  public int getDisappears() {
    return endOfLife;
  }

  @Override
  public Float getXPosition() {
    return xPosn;
  }

  @Override
  public Float getYPosition() {
    return yPosn;
  }

  @Override
  public Float getRed() {
    return red;
  }

  @Override
  public void setRed(float v) {
    this.red = v;
  }

  @Override
  public Float getGreen() {
    return green;
  }

  @Override
  public void setGreen(float v) {
    this.green = v;
  }

  @Override
  public Float getBlue() {
    return blue;
  }

  @Override
  public void setBlue(float v) {
    this.blue = v;
  }

  @Override
  public Float getRadian() {
    System.out.print("getRadian= " + radian + "\n");

    return this.radian;
  }

  @Override
  public void setRadian(float v) {
    System.out.print(v + "\n");
    this.radian = v;
  }
}
