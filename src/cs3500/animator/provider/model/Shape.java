package cs3500.animator.provider.model;


import java.util.List;

import cs3500.commands.Command;
import cs3500.misc.Position2D;

/**
 * Interface representing shapes. Has getters so that commands can access particular parameters
 * of this shape.
 */
public interface Shape {

  /**
   * Print the shape in a way that can be understood as words.
   * @return the string that describes the shape
   */
  String printShape(int tempo);

  /**
   * Get a the position of this shape.
   * @return a copy of the position
   */
  Position2D getPosition();

  /**
   * Move this shape by a given amount in the x and y directions.
   * @param x how much to move in x direction
   * @param y how much to move in y direction
   */
  void moveBy(double x, double y);

  /**
   * Move this shape to a given position.
   * @param position where should this shape be moved to
   */
  void moveTo(Position2D position);

  /**
   * Find the name of this shape as a string.
   * @return String representing the name of this shape
   */
  String getName();

  /**
   * Change the size of this shape by a given factor in each dimension.
   */
  void scale(double newWidth, double newHeight);

  /**
   * Change the color of this shape to a given color.
   * @param c what color the shape should be changed to
   */
  void changeColor(Colors c);

  /**
   * Get the type of this shape.
   * @return the type as a string
   */
  String getType();

  /**
   * Get the stating point of this shape.
   * @return The position representing the start.
   */
  Position2D getAnchor();

  /**
   * Get the width of this shape.
   * @return the width as a double
   */
  double getWidth();

  /**
   * Get the height of this shape.
   * @return the height as a double
   */
  double getHeight();

  /**
   * Get the color of this shape.
   * @return the color
   */
  Colors getColor();

  /**
   * Get all the commands affecting this shape.
   * @return a copy of the list of commands
   */
  List<Command> getCommands();

  /**
   * Get the start time for this shape.
   * @return the time at which this shape comes into play
   */
  int getStartTime();

  /**
   * Get the end time for this shape.
   * @return the time at which this shape disappears
   */
  int getEndTime();

  /**
   * Add a command to the list of commands acting upon this shape.
   * @param c Command to be added
   */
  void addCommand(Command c);

  /**
   * Add multiple commands to the list of commands acting upon this shape.
   * @param commands the commands to be added
   */
  void addListCommands(List<Command> commands);

  /**
   * Make a deep copy of this shape.
   * @return an instance of this shape that is identical, but cannot be referenced
   */
  Shape clone();

  /**
   * Is this shape visible according to the visibility boolean flag.
   * @return true if visible
   */
  boolean isVisible();

  /**
   * Set the visibility of this shape.
   * @param visible true if shape should be able to be seen
   */
  void setVisibility(boolean visible);

  /**
   * Find out the visibility of this shape.
   * @return true if this shape is visible.
   */
  boolean getVisibility();

}
