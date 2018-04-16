package cs3500.animator.model;

import java.util.List;

public interface ModelAdapter {

  /**
   * Animate all commands that are in this model.
   */
  void animate() throws IllegalArgumentException;

  /**
   * Print the animation in a way that can be understood as words.
   * @return the string that describes the animation
   */
  String printAnimation();

  /**
   * Add a shape to this model's internal list.
   * @param shape the shape to be added
   * @throws IllegalArgumentException if shape is null
   */
  void addShape(Shape shape) throws IllegalArgumentException;

  /**
   * Add a command to this model's internal list.
   * @param command the command to be added
   * @param s the shape this command acts upon
   * @throws IllegalArgumentException if command is null
   * @throws IllegalArgumentException if shape is null
   */
  void addCommand(Command command, Shape s) throws IllegalArgumentException;

  /**
   * Get the Shape that has this string as its name.
   * @param name the name to look for.
   * @return The shape who's name is found.
   */
  Shape getShapeWithName(String name);

  /**
   * Get a copy of the shapes in the model. Changing this list will not affect the model.
   * @return a copy of the list of shapes.
   */
  List<Shape> getShapes();


}
