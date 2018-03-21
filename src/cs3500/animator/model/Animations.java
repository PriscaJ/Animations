package cs3500.animator.model;

/**
 * An interface for various kinds of animations that Shapes can have.
 */
public interface Animations {

  float getOldR();

  float getOldG();

  float getOldB();

  float getNewR();

  float getNewG();

  float getNewB();

  /**
   * Applies the Animation actions and mutates its shape at the given time.
   */
  void apply(int tick);

  /**
   * Various observational methods to retrieve and access private fields when needed.
   * @return int or an Object depending.
   */
  int getFinish();

  int getStart();

  String getDescription(int tps);

  void setAnimatingShape(Shapes s);

  /**
   * Depending on what time the animation is at it will show the incrementation of the of an Action.
   * @param startValue The value it begins at.
   * @param endValue The value it should end at.
   * @param tick The moment in time the action and subsequent change is taking place.
   * @return Float The difference between the start and the end values.
   */
  float calculateChange(float startValue, float endValue, float tick);

  AnimationType getType();

  String getName();

  float getStartX();

  float getEndX();

  float getStartY();

  float getEndY();
}
