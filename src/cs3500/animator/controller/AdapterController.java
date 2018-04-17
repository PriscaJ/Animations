package cs3500.animator.controller;

import java.io.IOException;

import cs3500.animator.model.ModelAdapter;
import cs3500.animator.provider.view.View;
import cs3500.animator.view.ViewAdapter;

public class AdapterController {

  private View view;
  // putting our own controller inside to pass funtionality through.
  private Controller controller;

  public AdapterController(ModelAdapter model, ViewAdapter view) {
    this.view = view;
  }

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