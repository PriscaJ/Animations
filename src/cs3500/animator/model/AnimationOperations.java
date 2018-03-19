package cs3500.animator.model;

import java.util.List;

/**
 * An interface holding method behaviors that all models should share in order to animate.
 */
public interface AnimationOperations {


  /**
   * add the given shape to the animation.
   */
  void setShapes(Shapes shape);

  /**
   * A method to return a copy of all shapes in an animation.
   * @return
   */
  List<Shapes> getShapes();

  /**
   * A method to return a copy of all animations in an animation.
   * @return
   */
  List<Animations> getAnimations();

  int getEndTime();

  /**
   * Provides the String representation of the animation.
   * @return String of the animation.
   */
  //String readBack();
}
