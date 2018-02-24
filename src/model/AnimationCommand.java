package model;

/**
 * Interface for commands to decouple the Shape from the animation.
 */
public interface AnimationCommand {

  Animations getAnimation();

  void execute();

  String toString();
}
