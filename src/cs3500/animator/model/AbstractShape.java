package cs3500.animator.model;

import java.util.ArrayList;

/**
 * Class that shapes can extend to keep similar functionality in one place.
 */

public abstract class AbstractShape implements Shapes {
  // the animations that the shape invokes
  private ArrayList<AnimationCommand> commands = new ArrayList<>();
  // qualities of a shape
  protected String name;
  protected float xPosn;
  protected float yPosn;
  protected float xDimension;
  protected float yDimension;
  protected float red;
  protected float green;
  protected float blue;
  protected int startOfLife;
  protected int endOfLife;

  public AbstractShape (String name,
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
  }

  public void addCommand(AnimationCommand c) {
    commands.add(c);
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
  public void animateShape(int tick) {
    for (int i = 0; i < commands.size() - 1; i++) {
      commands.get(i).execute(tick);
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
  public Float getGreen() {
    return green;
  }

  @Override
  public Float getBlue() {
    return blue;
  }
}
