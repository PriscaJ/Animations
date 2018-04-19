package cs3500.animator.model;

import java.util.ArrayList;

/**
 * Representation of common behaviors tha shapes. These behaviors will play a role in how a shape
 * animates.
 */
public interface Shapes {

  int getLayer();

  float getCenterX();

  float getCenterY();

  /**
   * A String of the output of what the AbstractShape is doing during an animation.
   *
   * @return String of qualities of this shape.
   */
  String toString();

  /**
   * The animations the AbstractShape will undergo during an animations by adding it to the list of
   * animations that a shape has.
   *
   * @param newCommand Animation Command that will add to the list of commands the AbstractShape
   *                   will have.
   */
  void setAnimation(AnimationCommand newCommand);

  /**
   * Various observational methods to retrieve and access private fields when needed.
   *
   * @return various returns.
   */
  ArrayList<AnimationCommand> getCommands();

  /**
   * Get the name of the shape.
   *
   * @return the name of the shape.
   */
  String getName();

  /**
   * Get the width or x-radius of the shape.
   *
   * @return the width.
   */
  Float getWidth();

  /**
   * Get the height or y-radius of the shape.
   *
   * @return the height.
   */
  Float getHeight();

  /**
   * Get the time the shape appears.
   *
   * @return the shape start time.
   */
  int getAppears();

  /**
   * Get the time the shape disappears.
   *
   * @return the shape's end time.
   */
  int getDisappears();

  /**
   * Get the x coordinate of the shape. It is the center for an oval and the left for the rect.
   *
   * @return the x coordinate.
   */
  Float getXPosition();

  /**
   * Get the y coordinate of the shape. It is the center for an oval and the bottom for the rect.
   *
   * @return the y coordinate.
   */
  Float getYPosition();

  /**
   * Get the red RGB value.
   *
   * @return the red color value.
   */
  Float getRed();

  /**
   * Change the red value of the shape to be this value.
   *
   * @param v the new red value.
   */
  void setRed(float v);

  /**
   * Get the green RGB value of the shape.
   *
   * @return the green value.
   */
  Float getGreen();

  /**
   * Change the green RGB value of the shape to be this value.
   *
   * @param v the new G value of the shape.
   */
  void setGreen(float v);

  /**
   * Get the blue RGB value of the shape.
   *
   * @return the current B value of the shape.
   */
  Float getBlue();

  /**
   * Change the blue RGB value of the shape to be this value.
   *
   * @param v the new B value of the shape.
   */
  void setBlue(float v);

  /**
   * Get the string representation of the shape with the given speed.
   *
   * @param tps the speed of the animation.
   * @return the string representation of the shape.
   */
  String getDescription(int tps);

  /**
   * Check if the shape is an Oval.
   *
   * @return true if a shape is an Oval.
   */
  boolean isOval();

  /**
   * Check if the shape is a Rectangle.
   *
   * @return true if the shape is a Rectangle.
   */
  boolean isRect();

  /**
   * Change the current x coordinate of the shape.
   *
   * @param v the shape's new x coordinate.
   */
  void setXPosn(float v);

  /**
   * Change the shapes current y coordinate.
   *
   * @param v the shape's new y coordinate.
   */
  void setYPosn(float v);

  /**
   * Change the x dimension of the shape.
   *
   * @param v the new x dimension of the shape.
   */
  void setXDimension(float v);

  /**
   * Change the y dimension of the shape.
   *
   * @param v the new y dimension of the shape.
   */
  void setYDimension(float v);

  /**
   * Add a command to the list of commands in the shape.
   *
   * @param command the command that the shape is a part of.
   */
  void addCommand(AnimationCommand command);

  /**
   * Change the current radian of the shape.
   * @param v the new degree of rotation.
   */
  void setRadian(float v);
}
