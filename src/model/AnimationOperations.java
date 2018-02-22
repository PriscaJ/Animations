package model;

/**
 * An interface holding method behaviors that all models should share in order to animate.
 */
public interface AnimationOperations {

  /**
   * Observational method to return a shape when called.
   * @return AShape the called shape.
   */
  Shape getShape();

  /**
   * Provides the String representation of the animation.
   * @return String of the animation.
   */
  String readBack();
}
