package cs3500.animator.view;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
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
//    Comparator<Shapes> employeeNameComparator
//        = Comparator.comparing(Shapes::getAppears);
//    Comparator<Shapes> employeeNameComparator
//        = Comparator.comparing(
//        Shapes::getAppears, Comparator.naturalOrder());
//    Comparator<Shapes> shapeNameComparator
//        = Comparator.comparing(
//        Shapes::getName);
//    allShapes.sort(shapeNameComparator);
//    allShapes.sort(employeeNameComparator);
    String start = "<svg version= \"1.1\" "
        + "xmlns=\"http://www.w3.org/2000/svg\">\n\n";
    String end = "</svg>";
    String output = start + format(allShapes) + end;
    if (fileName.equals("out")) {
      System.out.print(output);
    } else {
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
                + "fill=\"rgb(%.0f,%.0f,%.0f)\" visibility=\"hidden\" >\n", s.getName(), s.getXPosition(), s.getYPosition(), s.getWidth(), s.getHeight(),
            s.getRed() * 255, s.getGreen() * 255, s.getBlue() * 255));
        workString.append(String.format("<set attributeName=\"visibility\" attributeType=\"CSS\" to=\"visible\" begin=\"%.1fms\" dur=\"%.1fms\" fill=\"freeze\" />",(float) s.getAppears() * ticksPerSec, (float) (s.getDisappears()-s.getAppears()) * ticksPerSec)
);
        workString.append("\n");

        workString.append(formatCmd(s.getCommands(), "ellipse"));
        workString.append("</" + "ellipse" + ">\n\n");
      }

      if (s instanceof Rectangle) {
        workString.append(String.format("<rect id=\"%s\" x=\"%.1f\" y=\"%.1f\" width=\"%.1f\" "
                + "height=\"%.1f\" fill=\"rgb(%.0f,%.0f,%.0f)\" visibility=\"hidden\" >\n",
            s.getName(), s.getXPosition(), s.getYPosition(), s.getWidth(), s.getHeight(),
            s.getRed() * 255, s.getGreen() * 255, s.getBlue() * 255));
        workString.append(String.format("<set attributeName=\"visibility\" attributeType=\"CSS\" to=\"visible\" begin=\"%.1fms\" dur=\"%.1fms\" fill=\"freeze\" />",(float) s.getAppears() * ticksPerSec, (float) (s.getDisappears()-s.getAppears()) * ticksPerSec)
        );
        workString.append("\n");
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
    String workString2;
    for (AnimationCommand a : cmds) {
      workString2 = "<animate attributeType=\"xml\" begin=\"";
      workString2 += a.getAnimation().getStart() * ticksPerSec + "ms\" dur=\"" + ((a.getAnimation().getFinish() - a.getAnimation().getStart()) * ticksPerSec) + "ms\" attributeName=\"" //+ attributeCmd()
      ;
      switch (a.getAnimation().getType()) {
        case MOVE:
          workString.append(formatMove((Move) a.getAnimation(), shapeType, workString2));
          break;
        case COLORCHANGE:
          workString.append(formatColor((ColorChange) a.getAnimation(), workString2));
          break;
        case SCALECHANGE:
          workString.append(formatScale((ScaleChange) a.getAnimation(), shapeType, workString2));
          break;
      }
      // todo: fill freeze or fill remove??
      //workString.append("fill=\"freeze\" />\n");
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
  private String formatMove(Move move, String shape, String starter) {
    String workString = "";
    String additional = "";
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
      workString = starter + attributeX + "\" "
          + "from=\"" + move.getStartX() + "\" to=\"" + move.getEndX() + "\" "
      + "fill=\"freeze\" />\n";
    }
    if (move.getStartY() != move.getEndY()) {
      // if the x has also changed, make a new tag
        additional += starter + attributeY + "\" "
            + "from=\"" + move.getStartY() + "\" to=\"" + move.getEndY() + "\" ";
        additional += "fill=\"freeze\" />\n";
    }
    return workString + additional;
  }

  /**
   * Formats the Command move as SVG animate tag.
   *
   * @return The full animate tag for this command.
   */
  private String formatColor(ColorChange c, String starter) {
    String workString = "";

    workString += starter + "fill\"";
    workString += String.format(" from=\"rgb(%.0f,%.0f,%.0f)\" to=\"rgb(%.0f,%.0f,%.0f)\"", c.getOldR() * 255, c.getOldG() * 255, c.getOldB() * 255, c.getNewR() * 255, c.getNewG() * 255, c.getNewB() * 255);
    workString += " fill=\"freeze\" />\n";
    return workString;
  }

  /**
   * Formats the Command move as SVG animate tag.
   *
   * @param sChange Takes in a Move class in order to ask for fields
   * @param shape Determines what kind of shape it is to change attributeName.
   * @return The full animate tag for this command.
   */
  private String formatScale(ScaleChange sChange, String shape, String starter) {
    String workString = "";
    String workString2 = "";
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
      workString = starter + attributeX + "\" "
          + "from=\"" + sChange.getStartX()
          + "\" to=\"" + sChange.getEndX() + "\" " + "fill=\"freeze\" />\n";
    }
    if (sChange.getStartY() != sChange.getEndY()) {
      // if it was changed make a new tag
      if (!workString.equals("")) {
        workString2 = starter + attributeY + "\" "
            + "from=\"" + sChange.getStartY()
            + "\" to=\"" + sChange.getEndY() + "\" ";
        workString2 += "fill=\"freeze\" />\n";
      }
      else {
        workString2 = starter + attributeY + "\" "
            + "from=\"" + sChange.getStartY()
            + "\" to=\"" + sChange.getEndY() + "\" " + "fill=\"freeze\" />\n";
      }
    }
    return workString + workString2;
  }
}
