package cs3500.animator.controller;

import org.junit.Before;

import java.util.ArrayList;


import cs3500.animator.model.Move;
import cs3500.animator.model.Oval;
import cs3500.animator.model.Rectangle;
import cs3500.animator.model.Shapes;
import cs3500.animator.view.IInteractiveView;
import cs3500.animator.view.VisualView;


public class ControllerTest {
  VisualView visualView;
  Controller controller;
  IInteractiveView iv;
  @Before
  public void initData() {
    //VisualView visualView = new VisualView()
    // simple example
    //
    Rectangle r = new Rectangle("R", 200f, 200f, 50f,
            100f, 1f, 0f, 0f, 1, 5);
    Oval o = new Oval("C", 500f, 100f, 60f, 30f,
            0f, 0f, 1f, 2, 7);

    Move moveRect = new Move("R", 200, 200.0f,
            300.0f, 200.0f, 1, 5);
    Move moveEllipse = new Move("C", 500, 100,
            600, 400, 2, 7);

    ArrayList<Shapes> shapes = new ArrayList<>();
    // add the shapes
    shapes.add(r);
    shapes.add(o);
    iv = new VisualView(shapes, 200, 20);
  }

 /**@Test
  public void testActionPerformed() {
    // static error
    assertEquals(controller.actionPerformed((ActionEvent e) -> IInteractiveView.stopTimer());
  }**/


}
