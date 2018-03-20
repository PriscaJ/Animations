package cs3500.animator.view;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import cs3500.animator.model.AnimationCommand;
import cs3500.animator.model.Animations;
import cs3500.animator.model.ColorChange;
import cs3500.animator.model.Move;
import cs3500.animator.model.Oval;
import cs3500.animator.model.Rectangle;
import cs3500.animator.model.ScaleChange;
import cs3500.animator.model.Shapes;

public class SVGView implements IView {

  private List<Shapes> allShapes;
  private String fileName;
  private int ticksPerSec;

  public SVGView(List<Shapes> allShapes, String fileName, int ticksPerSec) {
    this.allShapes = allShapes;
    this.fileName = fileName;
    this.ticksPerSec = ticksPerSec;
  }

  @Override
  public void makeVisible() {
    asSVG();
  }

  /**
   * Formats the SVG for the entire animation.
   *
   * @return The String representation of an SVG for the animation
   */
  private void asSVG() {
    String start = "<svg width= \"700\"  height= \"500\" version= \"1.1\" "
        + "xmlns=\"http://www.w3.org/2000/svg\">\n\n";
    String end = "</svg>";
    String output = start + format(allShapes) + end;
    if (fileName.equals("out")) {
      System.out.print(output);
    }
    else {
      try {
        File file = new File(fileName);
        //Write Content
        FileWriter writer = new FileWriter(file);
        writer.write(output);
        writer.close();
      } catch (IOException ioe) {
        //
      }
    }
  }

  // FOR TESTING GET RID OF THIS
  public String svgOutput() {
    String start = "<svg width= \"700\"  height= \"500\" version= \"1.1\" "
        + "xmlns=\"http://www.w3.org/2000/svg\">\n\n";
    return start + format(allShapes);
  }

  /**
   * Formats the rest of the SVG depending on which shape it is.
   *
   * @param shapes The list of shapes to be parsed.
   * @return The String SVG of the rest of the animation.
   */
  private String format(List<Shapes> shapes) {
    StringBuilder workString = new StringBuilder();
    for (Shapes s : shapes) {
      // list of animations for this shape
      // as.getAnimations();
      if (s instanceof Oval) {
        workString.append(String.format("<ellipse id=\"%s\" cx=\"%.1f\" cy=\"%.1f\" rx=\"%.1f\" ry=\"%.1f\" "
                + "fill=\"rgb(%.0f,%.0f,%.0f)\" visibility=\"visible\" >\n", s.getName(), s.getXPosition(), s.getYPosition(), s.getWidth(), s.getHeight(),
             s.getRed() * 255, s.getGreen() * 255, s.getBlue() * 255));

        workString.append(formatCmd(s.getCommands(), "ellipse"));
        workString.append("</" + "ellipse" + ">\n\n");
      }

      if (s instanceof Rectangle) {
        workString.append(String.format("<rect id=\"%s\" x=\"%.1f\" y=\"%.1f\" width=\"%.1f\" "
                + "height=\"%.1f\" fill=\"rgb(%.0f,%.0f,%.0f)\" visibility=\"visible\" >\n",
            s.getName(), s.getXPosition(), s.getYPosition(), s.getWidth(), s.getHeight(),
            s.getRed() * 255, s.getGreen() * 255, s.getBlue() * 255));
        workString.append(formatCmd(s.getCommands(), "rect"));
        workString.append("</" + "rect" + ">\n\n");
      }
    }
    return workString.toString();
  }

  /**
   * Takes a copy of the commands and parses through to properly format SVG.
   *
   * @param cmds copy of the animation commands that a shape has.
   * @return Proper SVG format for Animation commands.
   */
  private String formatCmd(List<AnimationCommand> cmds, String shapeType) {
    StringBuilder workString = new StringBuilder();

    for (AnimationCommand a : cmds) {
      workString.append("<animate attributeType=\"xml\" begin=\"");
      workString.append(a.getAnimation().getStart() *ticksPerSec).append("ms\" dur=\"").append((a.getAnimation().getFinish() - a.getAnimation().getStart())*ticksPerSec).append("ms\" attributeName=\"") //+ attributeCmd()
      ;
      switch (a.getAnimation().getType()) {
        case MOVE:
          workString.append(formatMove((Move) a.getAnimation(), shapeType));
          break;
        case COLORCHANGE:
          workString.append(formatColor((ColorChange) a.getAnimation()));
          break;
        case SCALECHANGE:
          workString.append(formatScale((ScaleChange) a.getAnimation(), shapeType));
          break;
      }
      // todo: fill freeze or fill remove??
      workString.append("fill=\"freeze\" />\n");
    }
    return workString.toString();
  }

  /**
   * Formats the Command move as SVG animate tag.
   *
   * @param move Takes in a Move class in order to ask for fields
   * @param shape Determines what kind of shape it is to change attributeName.
   * @return The full animate tag for this command.
   */
  private String formatMove(Move move, String shape) {
    String workString = "";
    String attributeX = "";
    String attributeY = "";

    // used to determine how to change the attribute name
    if (shape.equals("rect")) {
      attributeX = "x";
      attributeY = "y";
    } else if (shape.equals("ellipse")) {
      attributeX = "cx";
      attributeY = "cy";
    }
    if (move.getStartX() != move.getEndX()) {
      workString = attributeX + "\" "
          + "from=\"" + move.getStartX() + "\" to=\"" + move.getEndX() + "\" ";
    }
    if (move.getStartY() != move.getEndY()) {
      workString = attributeY + "\" "
          + "from=\"" + move.getStartY() + "\" to=\"" + move.getEndY() + "\" ";
    }
    return workString;
  }

  /**
   * Formats the Command move as SVG animate tag.
   *
   * @param cChange Takes in a Move class in order to ask for fields
   * @return The full animate tag for this command.
   */
  private String formatColor(ColorChange cChange) {
    String workString = "";

    if (cChange.getOldR() != cChange.getNewR()) {
      workString = "r\" " + " from=\"" + cChange.getNewR()
          + "\" to=\"" + cChange.getOldR() * 255 + "\" ";
    }
    if (cChange.getOldG() != cChange.getNewG()) {
      workString = "g\" " + "from=\"" + cChange.getOldG()
          + "\" to=\"" + cChange.getNewG() * 255 + "\" ";
    }
    if (cChange.getOldB() != cChange.getNewB()) {
      workString = "b\" " + "from=\"" + cChange.getOldB()
          + "\" to=\"" + cChange.getNewB() * 255 + "\" ";
    }
    return workString;
  }

  /**
   * Formats the Command move as SVG animate tag.
   *
   * @param sChange Takes in a Move class in order to ask for fields
   * @param shape Determines what kind of shape it is to change attributeName.
   * @return The full animate tag for this command.
   */
  private String formatScale(ScaleChange sChange, String shape) {
    String workString = "";
    String attributeX = "";
    String attributeY = "";

    // used to determine how to change the attribute name
    if (shape.equals("rect")) {
      attributeX = "width";
      attributeY = "height";
    } else if (shape.equals("ellipse")) {
      attributeX = "rx";
      attributeY = "ry";
    }

    if (sChange.getStartX() != sChange.getEndX()) {
      workString = attributeX + "\" "
          + "from=\"" + sChange.getStartX()
          + "\" to=\"" + sChange.getEndX() + "\" ";
    }
    if (sChange.getStartY() != sChange.getEndY()) {
      workString = attributeY + "\" "
          + "from=\"" + sChange.getStartY()
          + "\" to=\"" + sChange.getEndY() + "\" ";
    }
    return workString;
  }
}
