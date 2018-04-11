package cs3500.animator.model;

/**
 * An interface for various kinds of animations that Shapes can have.
 */
public interface Animations {

  /**
   * Observational method for color.
   *
   * @return Float the color value.
   */
  float getOldR();

  /**
   * Gets the start G value of a color in a color change.
   *
   * @return the G RGB value.
   */
  float getOldG();

  /**
   * Gets the start B value of a color in a color change.
   *
   * @return the B RGB value.
   */
  float getOldB();

  /**
   * Gets the end R value of a color in a color change.
   *
   * @return the R RGB value.
   */
  float getNewR();

  /**
   * Gets the end G value of a color in a color change.
   *
   * @return the G RGB value.
   */
  float getNewG();

  /**
   * Gets the end B value of a color in a color change.
   *
   * @return the B RGB value.
   */
  float getNewB();

  /**
   * Applies the Animation actions and mutates its shape at the given time.
   */
  void apply(int tick);

  /**
   * Various observational methods to retrieve and access private fields when needed.
   *
   * @return int or an Object depending.
   */
  int getFinish();

  /**
   * Get the time this animation begins.
   *
   * @return the start time of the animation.
   */
  int getStart();

  /**
   * Get the string representation of the animation.
   *
   * @param tps the speed of the animation.
   * @return the description of the animation.
   */
  String getDescription(int tps);

  /**
   * Initialize the shape the animation is performing on.
   *
   * @param s the shape animated by the animation.
   */
  void setAnimatingShape(Shapes s);

  /**
   * Depending on what time the animation is at it will show the incrementation of the of an
   * Action.
   *
   * @param startValue The value it begins at.
   * @param endValue The value it should end at.
   * @param tick The moment in time the action and subsequent change is taking place.
   * @return Float The difference between the start and the end values.
   */
  float calculateChange(float startValue, float endValue, float tick);

  /**
   * Gets the type of animation.
   *
   * @return the AnimationType of the animation.
   */
  AnimationType getType();

  /**
   * Get the name of the shape the animation is acting upon.
   *
   * @return animation's shape's name.
   */
  String getName();

  /**
   * Get the starting x value of the animation.
   *
   * @return the x value the animation's shape starts at.
   */
  float getStartX();

  /**
   * Get the ending x value of the animation.
   *
   * @return the x value the animation's shape ends at.
   */
  float getEndX();

  /**
   * Get the starting y value of the animation.
   *
   * @return the y value the animation's shape starts at.
   */
  float getStartY();


  /**
   * Get the ending y value of the animation.
   *
   * @return the y value the animation's shape ends at.
   */
  float getEndY();
}
