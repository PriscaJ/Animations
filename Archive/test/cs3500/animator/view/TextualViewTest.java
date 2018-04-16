package cs3500.animator.view;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import cs3500.animator.model.Animations;
import cs3500.animator.model.ColorChange;
import cs3500.animator.model.ColorCommand;
import cs3500.animator.model.Move;
import cs3500.animator.model.MoveCommand;
import cs3500.animator.model.Oval;
import cs3500.animator.model.Rectangle;
import cs3500.animator.model.ScaleChange;
import cs3500.animator.model.ScaleCommand;
import cs3500.animator.model.Shapes;

import static org.junit.Assert.*;

/**
 * This is a test class to check the output of a textual view.
 */
public class TextualViewTest {
  @Test
  public void TextViewTest() {
    // set up the shapes and their actions
    Rectangle r = new Rectangle("R", 200f, 200f, 50f,
        100f, 1f, 0f, 0f, 1, 100);
    Oval o = new Oval("C", 500f, 100f, 60f, 30f,
        0f, 0f, 1f, 6, 100);
    Move c = new Move("R", 200, 200.0f,
        300.0f, 300.0f, 10, 50);
    Move m = new Move("C", 500, 100,
        500, 400, 20, 70);
    ColorChange cc = new ColorChange("C", 0f, 0, 1,
        0, 1, 0, 50, 80);
    ScaleChange s = new ScaleChange("R", 50, 100,
        25, 100, 51, 70);
    Move m2 = new Move("R", 300, 300,
        200, 200, 70, 100);

    // send the list of shapes to the model
    List<Shapes> shapes = new ArrayList<>();

    // send the list of animation actions to the model
    List<Animations> animationActions = new ArrayList<>();

    // load in to appropriate lists
    shapes.add(r);
    shapes.add(o);

    MoveCommand mc3 = new MoveCommand(c);
    animationActions.add(c);
    c.setAnimatingShape(r);
    r.addCommand(mc3);

    ColorCommand ac = new ColorCommand(cc);
    r.addCommand(ac);
    animationActions.add(cc);
    cc.setAnimatingShape(r);

    MoveCommand mc = new MoveCommand(m);
    MoveCommand mc2 = new MoveCommand(m2);
    animationActions.add(m);
    animationActions.add(m2);

    r.addCommand(mc);
    m.setAnimatingShape(r);
    o.addCommand(mc2);
    m2.setAnimatingShape(o);
    ScaleCommand sc = new ScaleCommand(s);
    animationActions.add(s);
    o.addCommand(sc);
    s.setAnimatingShape(o);
    TextualView view = new TextualView("", shapes, animationActions, 1);
    // actually try to run it
    assertEquals("Shapes:\n"
        + "Name: R\n"
        + "Type: rectangle\n"
        + "Min-corner: (200.0,200.0), Width: 50.0, Height: 100.0, Color: (1.0,0.0,0.0)\n"
        + "Appears at t=0.0s\n"
        + "Disappears at t=0.1s\n"
        + "\n"
        + "Name: C\n"
        + "Type: oval\n"
        + "Center: (500.0,100.0), X radius: 60.0, Y radius: 30.0, Color: (0.0,0.0,1.0)\n"
        + "Appears at t=0.0s\n"
        + "Disappears at t=0.1s\n"
        + "\n"
        + "Shape R moves from (200.0,200.0) to (300.0,300.0) from t=0.0s to t=0.1s\n"
        + "Shape C changes color from (0.0,0.0,1.0) to (0.0,1.0,0.0) from t=0.1s to t=0.1s\n"
        + "Shape C moves from (500.0,100.0) to (500.0,400.0) from t=0.0s to t=0.1s\n"
        + "Shape R moves from (300.0,300.0) to (200.0,200.0) from t=0.1s to t=0.1s\n"
        + "Shape R scales from Width: 50.0, Height: 100.0 to Width: 25.0, Height: 100.0 "
        + "from t=0.1s to t=0.1s", view.readBack());
  }

}