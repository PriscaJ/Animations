package cs3500.animator.model;

/**
 * An interface for various kinds of animations that Shapes can have.
 */
public interface Animations {

  /**
   * Applies the Animation actions and mutates its shape at the given time.
   */
  void apply(int tick);

  /**
   * Various observational mmethods to retrieve and access private fields when needed.
   * @return int or an Object depending
   */
  int getFinish();

  int getStart();

  Object getInitialStage();

  Object getFinalStage();

  String getDescription();

  float calculateChange(float startValue, float endValue, float tick);
}
