package cs3500.animator.view;

import java.awt.event.ActionListener;

/**
 * Represents the behaviors of a hybrid view. A Hybrid has some functionality that it takes from the
 * visual view, but also additional functionality that it provides in a button panel.
 */
public interface IInteractiveView extends IView {
  void setButtonListeners(ActionListener listener);

  /**
   * Stops the timer which in turn stops the animation.
   */
  void stopTimer();

  /**
   * Increases the speed of the animation by a certain increment.
   */
  void increaseSpeed();

  /**
   * Decreases the speed of the animation by a certain increment.
   */
  void decreaseSpeed();

  /**
   * Kicks off the animation at the beginning with the initial set of shapes.
   */
  void start();

  void resume();

  /**
   * Toggles between turning looping on and off.
   */
  void setLooping();

  /**
   * Reset animation by looping with current shapes.
   */
  void restart();

  /**
   * Runs the animation only with the shapes selected.
   */
  void runSelected();
}
