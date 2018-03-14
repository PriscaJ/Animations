package model;

/**
 * An interface for various kinds of animations that Shapes can have.
 */
public interface Animations {

  /**
   * Applies the Animation actions and stores them, in order for them to be used.
   */
  void apply();

  /**
   * Various observational mmethods to retrieve and access private fields when needed.
   * @return int or an Object depending
   */
  int getFinish();

  int getStart();

  Object getInitialStage();

  Object getFinalStage();
}
