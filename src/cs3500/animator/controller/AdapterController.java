package cs3500.animator.controller;

import java.io.IOException;

import cs3500.animator.model.ModelAdapter;
import cs3500.animator.provider.view.View;

public class AdapterController {
  private View view;

  public AdapterController(ModelAdapter model, View view) {
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

//package cs3500.animator.controller;
//
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.io.IOException;
//
//import cs3500.animator.model.ModelAdapter;
//import cs3500.animator.provider.view.View;
//import cs3500.animator.view.ViewAdapter;
//
//public class AdapterController {
//  private ViewAdapter view;
//
//  public AdapterController(ModelAdapter model, ViewAdapter view) {
//    this.view = view;
//  }
//
//  public void run() {
//      try {
//        view.showView();
//      } catch (IOException e) {
//        e.printStackTrace();
//      }
//  }
//
//  /** @Override
//  public void actionPerformed(ActionEvent e) {
//    // in the adapter
//  } **/
//}
