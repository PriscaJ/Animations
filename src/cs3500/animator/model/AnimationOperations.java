package cs3500.animator.model;

import java.util.ArrayList;

/**
 * An interface holding method behaviors that all models should share in order to animate.
 */
public interface AnimationOperations {


  /**
   * add the given shape to the animation.
   */
  void setShapes(Shapes shape);

  /**
   * Provides the String representation of the animation.
   * @return String of the animation.
   */
  String readBack();
}
