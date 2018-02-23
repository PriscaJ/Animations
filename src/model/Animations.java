package model;

/**
 * An interface for various kinds of animations that Shapes can have.
 */
public interface Animations {

  void apply();

  int getFinish();

  int getStart();

  Object getInitialStage();

  Object getFinalStage();
}
