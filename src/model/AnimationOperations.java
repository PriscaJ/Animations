package model;

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
   * Observational method to return a shape when called.
   * @return AShape the called shape.
   */
  ArrayList<Shapes> getShape();

  /**
   * Provides the String representation of the animation.
   * @return String of the animation.
   */
  String readBack();
}
