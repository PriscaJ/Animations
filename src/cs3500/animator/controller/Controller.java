package cs3500.animator.controller;

import cs3500.animator.model.AnimationOperations;
import cs3500.animator.view.IView;

public class Controller {

  private AnimationOperations model;
  private IView view;

  public Controller(AnimationOperations model, IView view) {
    this.model = model;
    this.view = view;
  }

  public void run() {
    view.makeVisible();
  }
}
