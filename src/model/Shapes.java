package model;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

/**
 * Representation of common behaviors tha shapes.
 * These behaviors will play a role in how a shape animates.
 */
public interface Shapes {

  /**
   * A String of the output of what the Shape is doing during an animation.
   * @return String of qualities of this shape.
   */
  String toString();

  /**
   * The animations the Shape will undergo during an animations
   * by adding it to the list of animations that a shape has.
   * @param newCommand Animation Command that will add to the list of commands the Shape will have.
   */
  void setAnimation(AnimationCommand newCommand);

  /**
   * Runs through the animations that the Shape has under its Commands.
   */
  void animateShape();

  /**
   * Various observational mmethods to retrieve and access private fields when needed.
   * @return various returns.
   */
  ArrayList<AnimationCommand> getCommands();

  String getName();

  ShapeType getType();

  Double getWidth();

  Double getHeight();

  int getAppears();

  int getDisappears();

  Point getPosition();

  Color getColor();

}
