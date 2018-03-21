package cs3500.animator.view;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import cs3500.animator.model.ColorChange;
import cs3500.animator.model.ColorCommand;
import cs3500.animator.model.Move;
import cs3500.animator.model.MoveCommand;
import cs3500.animator.model.Oval;
import cs3500.animator.model.Rectangle;
import cs3500.animator.model.ScaleChange;
import cs3500.animator.model.ScaleCommand;
import cs3500.animator.model.Shapes;

import static org.junit.Assert.assertEquals;

// This is a test class to check the output of an SVG view.
public class SVGTest {
  @Test
  public void testSVG() {
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
    ScaleChange s = new ScaleChange("R", 50, 100, 25, 100, 51, 70);
    Move m2 = new Move("R", 300, 300,
        200, 200, 70, 100);
    List<Shapes> shapes = new ArrayList<>();
    shapes.add(r);
    shapes.add(o);
    MoveCommand mc3 = new MoveCommand(c);
    c.setAnimatingShape(r);
    r.addCommand(mc3);
    ColorCommand ac = new ColorCommand(cc);
    r.addCommand(ac);
    cc.setAnimatingShape(r);
    MoveCommand mc = new MoveCommand(m);
    MoveCommand mc2 = new MoveCommand(m2);
    r.addCommand(mc);
    m.setAnimatingShape(r);
    o.addCommand(mc2);
    m2.setAnimatingShape(o);
    ScaleCommand sc = new ScaleCommand(s);
    o.addCommand(sc);
    s.setAnimatingShape(o);
    SVGView view = new SVGView(shapes, "hi", 1);
    assertEquals("<svg width= \"700\"  height= \"500\" version= \"1.1\" xmlns=\"http://www.w3.org/2000/svg\">\n"
        + "\n"
        + "<rect id=\"R\" x=\"200.0\" y=\"200.0\" width=\"50.0\" height=\"100.0\" fill=\"rgb(255,0,0)\" visibility=\"hidden\" >\n"
        + "<set attributeName=\"visibility\" attributeType=\"CSS\" to=\"visible\" begin=\"1.0ms\" dur=\"99.0ms\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"10ms\" dur=\"40ms\" attributeName=\"x\" from=\"200.0\" to=\"300.0\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"10ms\" dur=\"40ms\" attributeName=\"y\" from=\"200.0\" to=\"300.0\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"50ms\" dur=\"30ms\" attributeName=\"fill\" from=\"rgb(0,0,255)\" to=\"rgb(0,255,0)\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"20ms\" dur=\"50ms\" attributeName=\"y\" from=\"100.0\" to=\"400.0\" fill=\"freeze\" />\n"
        + "</rect>\n"
        + "\n"
        + "<ellipse id=\"C\" cx=\"500.0\" cy=\"100.0\" rx=\"120.0\" ry=\"60.0\" fill=\"rgb(0,0,255)\" visibility=\"hidden\" >\n"
        + "<set attributeName=\"visibility\" attributeType=\"CSS\" to=\"visible\" begin=\"6.0ms\" dur=\"94.0ms\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"70ms\" dur=\"30ms\" attributeName=\"cx\" from=\"300.0\" to=\"200.0\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"70ms\" dur=\"30ms\" attributeName=\"cy\" from=\"300.0\" to=\"200.0\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"51ms\" dur=\"19ms\" attributeName=\"rx\" from=\"50.0\" to=\"25.0\" fill=\"freeze\" />\n"
        + "</ellipse>\n\n", view.svgOutput());
  }
}
