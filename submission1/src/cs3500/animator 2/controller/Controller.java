package cs3500.animator.controller;

import cs3500.animator.model.AnimationOperations;
import cs3500.animator.view.IView;

/**
 * Class that represents the controller which handles the communication between the model and the
 * the view. Runs the view based on the information of the model.
 */
public class Controller {

  private AnimationOperations model;
  private IView view;

  /**
   * Constructor for the controller.
   * @param model The Model that the controller gets information from
   * @param view The view that the controller sends information to.
   */
  public Controller(AnimationOperations model, IView view) {
    this.model = model;
    this.view = view;
  }

  /**
   * Runs the view by making it visible to the user.
   */
  public void run() {
    view.makeVisible();
  }
}
