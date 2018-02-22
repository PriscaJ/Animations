package model;

/**
 * Interface for commands to decouple the Shape from the animation.
 */
public interface AnimationCommand {

  void execute();
}
