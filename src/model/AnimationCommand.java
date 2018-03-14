package model;

/**
 * Interface for commands to decouple the Shape from the animation.
 */
public interface AnimationCommand {

  /**
   * Gets the animation that this command acts on.
   * @return Animations that the command is acting on.
   */
  Animations getAnimation();

  /**
   * Used to call the Animation action it acts on. Used to invoke the command.
   */
  void execute();

  /**
   * Helper to the readback in order to get the
   * string representation of the actionon the particular shape.
   * @return String of what the animation action is doing to pass to the Shape.
   */
  String toString();
}
