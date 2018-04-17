package cs3500.animator.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import cs3500.animator.model.AnimationOperations;
import cs3500.animator.provider.view.View;
import cs3500.animator.view.HybridView;
import cs3500.animator.view.IInteractiveView;
import cs3500.animator.view.IView;
import cs3500.animator.view.ViewAdapter;
import cs3500.animator.view.VisualView;

/**
 * Class that represents the controller which handles the communication between the model and the
 * the view. Runs the view based on the information of the model.
 */
public class Controller implements ActionListener {

  private IView view;
  private View view2;

  /**
   * Constructor for the controller.
   *
   * @param model The Model that the controller gets information from
   * @param view  The view that the controller sends information to.
   */
  public Controller(AnimationOperations model, IView view) {
    this.view = view;
  }


  public Controller(AnimationOperations model, View view2) {
    this.view2 = view2;
  }

  /**
   * Runs the view by making it visible to the user.
   */
  public void run() {
    if (view instanceof HybridView) {
      HybridView hybridView = (HybridView) view;
      hybridView.setButtonListeners(this);
    }
    if (view instanceof VisualView) {
      VisualView visualView = (VisualView) view;
      visualView.setButtonListeners(this);
    }

    if (view2 instanceof ViewAdapter) {
      ViewAdapter adapter = (ViewAdapter) view;
      try {
        adapter.showView();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    view.makeVisible();
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    String buttonPressed = e.getActionCommand();

    if (view instanceof IInteractiveView) {
      IInteractiveView v = (IInteractiveView) view;

      switch (buttonPressed) {
        case "Increase Speed":
          v.increaseSpeed();
          break;
        case "Decrease Speed":
          v.decreaseSpeed();
          break;
        case "Stop":
          v.stopTimer();
          break;
        case "Start":
          v.start();
          break;
        case "Restart":
          v.restart();
          break;
        case "Resume":
          v.resume();
          break;
        case "Looping":
          v.setLooping();
          break;
        case "Export SVG":
          if (view instanceof HybridView) {
            HybridView hView = (HybridView) v;
            hView.exportSVG();
          }
          break;
        case "Run Selected Shapes":
          v.runSelected();
          break;
        default:
          // should not fall in here
          buttonPressed = "";
      }
    }
  }
}

