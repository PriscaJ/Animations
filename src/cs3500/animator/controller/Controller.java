package cs3500.animator.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import cs3500.animator.model.AnimationOperations;
import cs3500.animator.view.HybridView;
import cs3500.animator.view.IView;

/**
 * Class that represents the controller which handles the communication between the model and the
 * the view. Runs the view based on the information of the model.
 */
public class Controller implements ActionListener{

  private AnimationOperations model;
  private IView view;

  /**
   * Constructor for the controller.
   *
   * @param model The Model that the controller gets information from
   * @param view  The view that the controller sends information to.
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

  @Override
  public void actionPerformed(ActionEvent e) {
    switch (e.getActionCommand()) {
      //read from the input textfield
      case "Echo Button":
        String text = view.getInputString();
        //send text to the model
        model.setString(text);

        //clear input textfield
        view.clearInputString();
        //finally echo the string in view
        text = model.getString();
        view.setEchoOutput(text);

        //set focus back to main frame so that keyboard events work
        view.resetFocus();

        break;
  }
}
