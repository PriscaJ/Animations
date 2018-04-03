package cs3500.animator.view;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import cs3500.animator.model.AnimationType;
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

/**
 * This is a test class to check the output of an SVG view.
 */
public class SVGTest {
  @Test
  public void testSimpleSVG() {
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

    List<Shapes> shapes = new ArrayList<>();
    // add the shapes
    shapes.add(r);
    shapes.add(o);

    // add the animation to the command
    MoveCommand moveRectCommand = new MoveCommand(moveRect);
    MoveCommand moveEllipseCommand = new MoveCommand(moveEllipse);

    // add the commands to the shapes
    r.addCommand(moveRectCommand);
    o.addCommand(moveEllipseCommand);

    // set the animating shape of the animation
    moveRect.setAnimatingShape(r);
    moveEllipse.setAnimatingShape(o);

    SVGView view = new SVGView(shapes, "hi", 1, false);
    assertEquals( "<svg width= \"700\"  height= \"500\" version= \"1.1\" xmlns=\"http://www.w3.org/2000/svg\">\n"
        + "\n"
        + "<rect id=\"R\" x=\"200.0\" y=\"200.0\" width=\"50.0\" height=\"100.0\" fill=\"rgb(255,0,0)\" visibility=\"hidden\" >\n"
        + "<set attributeName=\"visibility\" attributeType=\"CSS\" to=\"visible\" begin=\"1000.0ms\" dur=\"4000.0ms\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"1000ms\" dur=\"4000ms\" attributeName=\"x\" from=\"200.0\" to=\"300.0\" fill=\"freeze\" />\n"
        + "\n"
        + "</rect>\n"
        + "\n"
        + "<ellipse id=\"C\" cx=\"500\" cy=\"100\" rx=\"60\" ry=\"30\" fill=\"rgb(0,0,255)\" visibility=\"hidden\" >\n"
        + "<set attributeName=\"visibility\" attributeType=\"CSS\" to=\"visible\" begin=\"2000.0ms\" dur=\"5000.0ms\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"2000ms\" dur=\"5000ms\" attributeName=\"cx\" from=\"500.0\" to=\"600.0\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"2000ms\" dur=\"5000ms\" attributeName=\"cy\" from=\"100.0\" to=\"400.0\" fill=\"freeze\" />\n"
        + "\n"
        + "</ellipse>\n"
        + "\n"
        + "</svg>", view.svgOutput());
  }

  @Test
  public void testGetAttributes() {
    List<Shapes> shapes = new ArrayList<>();
    SVGView view = new SVGView(shapes, "hi", 1, true);
    assertEquals("width", view.getAttributeXName("rect", AnimationType.SCALECHANGE));
  }

  @Test
  public void testSVGLooping() {
    Rectangle r = new Rectangle("R", 200f, 200f, 50f,
        100f, 1f, 0f, 0f, 1, 5);
    Oval o = new Oval("C", 500f, 100f, 60f, 30f,
        0f, 0f, 1f, 2, 7);

    Move moveRect = new Move("R", 200, 200.0f,
        300.0f, 200.0f, 1, 5);
    Move moveEllipse = new Move("C", 500, 100,
        600, 400, 2, 7);

    List<Shapes> shapes = new ArrayList<>();
    // add the shapes
    shapes.add(r);
    shapes.add(o);

    // add the animation to the command
    MoveCommand moveRectCommand = new MoveCommand(moveRect);
    MoveCommand moveEllipseCommand = new MoveCommand(moveEllipse);

    // add the commands to the shapes
    r.addCommand(moveRectCommand);
    o.addCommand(moveEllipseCommand);

    // set the animating shape of the animation
    moveRect.setAnimatingShape(r);
    moveEllipse.setAnimatingShape(o);

    SVGView view = new SVGView(shapes, "hi", 1, true);
    assertEquals( "<svg width= \"700\"  height= \"500\" version= \"1.1\" xmlns=\"http://www.w3.org/2000/svg\">\n"
        + "\n"
        + "<rect>\n"
        + "<animate id=\"base\" begin=\"0;base.end\" dur=\"7000.0ms\" attributeName=\"visibility\" from=\"hide\" to=\"hide\"/>\n"
        + "</rect>\n"
        + "\n"
        + "<rect id=\"R\" x=\"200.0\" y=\"200.0\" width=\"50.0\" height=\"100.0\" fill=\"rgb(255,0,0)\" visibility=\"hidden\" >\n"
        + "<set attributeName=\"visibility\" attributeType=\"CSS\" to=\"visible\" begin=\"base.begin+1000.0ms\" dur=\"4000.0ms\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"base.begin+1000ms\" dur=\"4000ms\" attributeName=\"x\" from=\"200.0\" to=\"300.0\" fill=\"freeze\" />\n"
        + "\n"
        + "<animate attributeType=\"xml\" begin=\"base.end\" dur=\"1ms\" attributeName=\"x\" to=\"200.0\" fill=\"freeze\" />\n"
        + "</rect>\n"
        + "\n"
        + "<ellipse id=\"C\" cx=\"500\" cy=\"100\" rx=\"60\" ry=\"30\" fill=\"rgb(0,0,255)\" visibility=\"hidden\" >\n"
        + "<set attributeName=\"visibility\" attributeType=\"CSS\" to=\"visible\" begin=\"base.begin+2000.0ms\" dur=\"5000.0ms\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"base.begin+2000ms\" dur=\"5000ms\" attributeName=\"cx\" from=\"500.0\" to=\"600.0\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"base.begin+2000ms\" dur=\"5000ms\" attributeName=\"cy\" from=\"100.0\" to=\"400.0\" fill=\"freeze\" />\n"
        + "\n"
        + "<animate attributeType=\"xml\" begin=\"base.end\" dur=\"1ms\" attributeName=\"cx\" to=\"500.0\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"base.end\" dur=\"1ms\" attributeName=\"cy\" to=\"100.0\" fill=\"freeze\" />\n"
        + "</ellipse>\n"
        + "\n"
        + "</svg>", view.svgOutput());
  }
}
