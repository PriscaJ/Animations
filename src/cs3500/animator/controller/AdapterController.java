package cs3500.animator.controller;

import java.io.IOException;


import cs3500.animator.provider.model.SimpleAnimation;
import cs3500.animator.provider.view.View;


/**
 * Adapter for controllers.
 */
public class AdapterController {

  private View view;
  // putting our own controller inside to pass funtionality through.
  private Controller controller;

  public AdapterController(SimpleAnimation model, View view) {
    this.view = view;
  }

  /**
   * Runs the controller for animations.
   */
  public void run() {
    try {
      view.showView();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /** @Override public void actionPerformed(ActionEvent e) {
  // in the adapter
  } **/
}