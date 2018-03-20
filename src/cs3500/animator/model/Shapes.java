package cs3500.animator.model;

import java.util.ArrayList;

/**
 * Representation of common behaviors tha shapes.
 * These behaviors will play a role in how a shape animates.
 */
public interface Shapes {

  /**
   * A String of the output of what the AbstractShape is doing during an animation.
   * @return String of qualities of this shape.
   */
  String toString();

  /**
   * The animations the AbstractShape will undergo during an animations
   * by adding it to the list of animations that a shape has.
   * @param newCommand Animation Command that will add to the list of commands the AbstractShape will have.
   */
  void setAnimation(AnimationCommand newCommand);

  /**
   * Various observational mmethods to retrieve and access private fields when needed.
   * @return various returns.
   */
  ArrayList<AnimationCommand> getCommands();

  String getName();

  Float getWidth();

  Float getHeight();

  int getAppears();

  int getDisappears();

  Float getXPosition();

  Float getYPosition();

  Float getRed();
  Float getGreen();

  Float getBlue();

  String getDescription(int tps);

  boolean isOval();

  boolean isRect();

  void setXPosn(float v);

  void setYPosn(float v);

  void setXDimension(float v);

  void setYDimension(float v);

  void setRed(float v);

  void setGreen(float v);

  void setBlue(float v);

  void addCommand(AnimationCommand command);
}
