package cs3500.animator.view;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.util.List;

import cs3500.animator.model.AbstractAnimation;
import cs3500.animator.model.AnimationCommand;
import cs3500.animator.model.AnimationType;

import cs3500.animator.model.ColorChange;
import cs3500.animator.model.Move;
import cs3500.animator.model.Oval;
import cs3500.animator.model.Rectangle;
import cs3500.animator.model.ScaleChange;
import cs3500.animator.model.Shapes;
import cs3500.animator.model.Rotate;

/**
 * This is a class to represent an animation in an SVG format. Class that represent a view that is
 * in an SVG format where it can be run on a browser due to its various tags.
 */
public class SVGView implements IView {

  private List<Shapes> allShapes;
  private String fileName;
  private int ticksPerSec;
  private boolean looping;
  private int endTime;

  {
    // if it was changed make a new tag
    workString2 = starter + attributeY + "\" "
        + "from=\"" + sChange.getStartY()
        + "\" to=\"" + sChange.getEndY() + "\" ";
    workString2 += "fill=\"freeze\" />\n";
  }

  /**
   * The constructor for an SVG view.
   *
   * @param shapes All the shapes running in the animation.
   * @param outputDest The file name that the SVG will output to.
   * @param tps The speed that the Animation will run.
   * @param looping Whether or not an animation is looping.
   */
  public SVGView(List<Shapes> shapes, String outputDest, int tps, boolean looping) {
    this.allShapes = shapes;
    this.fileName = outputDest;
    this.ticksPerSec = tps;
    this.looping = looping;
    int max = 0;
    for (Shapes shape : shapes) {
      int comp = shape.getDisappears();
      if (comp > max) {
        max = comp;
      }
    }
    this.endTime = max;
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
    String start = "<svg version= \"1.1\" "
        + "xmlns=\"http://www.w3.org/2000/svg\">";
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
        System.out.print("IOException caught.");
      }
    }
  }

  /**
   * This is a method to test the output of an SVG View.
   *
   * @return the svg output.
   */
  protected String svgOutput() {
    String start = "<svg width= \"700\"  height= \"500\" version= \"1.1\" "
        + "xmlns=\"http://www.w3.org/2000/svg\">";

    return start + format(allShapes) + "\n\n</svg>";
  }

  /**
   * Formats the rest of the SVG depending on which shape it is.
   *
   * @param shapes The list of shapes to be parsed.
   * @return The String SVG of the rest of the animation.
   */
  private String format(List<Shapes> shapes) {
    StringBuilder workString = new StringBuilder();
    if (looping) {
      // MAKE THE DUMMY SHAPE FOR LOOPING
      workString.append(String.format("\n\n<rect>\n<animate id=\"base\" begin=\"0;base.end\" "
          + "dur=\"%.1fms\" "
          + "attributeName=\"visibility\" from=\"hide\" to=\"hide\"/>"
          + "\n</rect>", (float) endTime * ticksPerSec));
    }

    // FORMAT THE SHAPE INFO, THEN CALL FORMATCMD
    for (Shapes s : shapes) {
      if (s instanceof Oval) {
        workString.append(
            String.format("\n\n<ellipse id=\"%s\" cx=\"%.0f\" cy=\"%.0f\" rx=\"%.0f\" ry=\""
                    + "%.0f\" "
                    + "fill=\"rgb(%.0f,%.0f,%.0f)\" visibility=\"hidden\" >\n",
                s.getName(), s.getXPosition(), s.getYPosition(), s.getWidth(),
                s.getHeight(), s.getRed() * 255, s.getGreen() * 255, s.getBlue() * 255));
        workString.append("<set attributeName=\"visibility\" attributeType=\"CSS\" to=\"visible\" "
            + "begin=\"");
        if (looping) {
          workString.append("base.begin+");
        }
        workString.append(String.format("%.1fms\" dur=\"%.1fms\" fill=\"freeze\" />",
            (float) s.getAppears() * ticksPerSec,
            (float) (s.getDisappears() - s.getAppears()) * ticksPerSec));

        workString.append("\n");
        workString.append(formatCmd(s.getCommands(), "ellipse"));
        workString.append("\n</" + "ellipse" + ">");
      }

      if (s instanceof Rectangle) {
        workString.append(String.format("\n\n<rect id=\"%s\" x=\"%.1f\" y=\"%.1f\" width=\"%.1f\" "
                + "height=\"%.1f\" fill=\"rgb(%.0f,%.0f,%.0f)\" visibility=\"hidden\" >\n",
            s.getName(), s.getXPosition(), s.getYPosition(), s.getWidth(), s.getHeight(),
            s.getRed() * 255, s.getGreen() * 255, s.getBlue() * 255));
        workString.append("<set attributeName=\"visibility\" attributeType=\"CSS\" to=\"visible\" "
            + "begin=\"");
        if (looping) {
          workString.append("base.begin+");
        }
        workString.append(String.format("%.1fms\" dur=\"%.1fms\" fill=\"freeze\" />",
            (float) s.getAppears() * ticksPerSec,
            (float) (s.getDisappears() - s.getAppears()) * ticksPerSec));
        workString.append("\n");

        // FORMAT COMMAND
        workString.append(formatCmd(s.getCommands(), "rect"));
        workString.append("\n</" + "rect" + ">");
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
    StringBuilder workString2;
    for (AnimationCommand a : cmds) {
      workString2 = new StringBuilder();
      workString2.append("<animate attributeType=\"xml\" begin=\"");
      if (looping) {
        workString2.append("base.begin+");
      }
      // NORMAL!!!!!
      workString2.append(a.getAnimation().getStart() * ticksPerSec);
      workString2.append("ms\" dur=\"")
          .append((a.getAnimation().getFinish() - a.getAnimation().getStart()) * ticksPerSec)
          .append("ms\" attributeName=\"");
      switch (a.getAnimation().getType()) {
        case MOVE:
          workString.append(formatMove((Move) a.getAnimation(), shapeType, workString2.toString()));
          break;
        case COLORCHANGE:
          workString.append(formatColor((ColorChange) a.getAnimation(), workString2.toString()));
          break;
        case SCALECHANGE:
          workString.append(formatScale((ScaleChange) a.getAnimation(), shapeType,
              workString2.toString()));
          break;
        case ROTATE:
          workString.append(formatRotate((Rotate) a.getAnimation(), shapeType,
              workString2.toString()));
        default:
          // do nothing.
      }
    }
    // LOOPING
    if (looping) {
      for (AnimationCommand a : cmds) {
        String startString =
            "\n<animate attributeType=\"xml\" begin=\"base.end\" dur=\"1ms\" "
                + "attributeName=\"";
        workString.append(formatReset(a, shapeType, startString));
      }
    }
    return workString.toString();
  }
  
  private String formatRotate(Rotate animation, String shapeType, String s) {
    return s + "\"transform\" attributeType=\"XML\" type=\"rotate\" "
        + "from=\"" + animation.getFromRadian() + " " + animation.getAnimatingShape().getCenterX()
        + " " + animation.getAnimatingShape().getCenterY() + "\" to=\""
        + animation.getToRadian() + " " + animation.getAnimatingShape().getCenterX() + " "
        + animation.getAnimatingShape().getCenterY() + "\" " + "dur=\""
        + (animation.getFinish() - animation.getStart()) + "s" + "repeatCount=\"indefinite\"/>\n";
  }

  /**
   * This is a helper method to get the correct attribute name given the shape and animation.
   *
   * @param shapeType the type of shape that is being printed.
   * @param aType the type of animation that is being performed on the shape.
   * @return the correct x attribute.
   */
  private String getAttributeXName(String shapeType, AnimationType aType) {
    if (aType == (AnimationType.MOVE)) {
      if (shapeType.equals("rect")) {
        return "x";
      } else if (shapeType.equals("ellipse")) {
        return "cx";
      }
    } else if (aType == (AnimationType.SCALECHANGE)) {
      if (shapeType.equals("rect")) {
        return "width";
      } else if (shapeType.equals("ellipse")) {
        return "rx";
      }
    }
    throw new IllegalArgumentException("Unable to get attribute x name");
  }

  /**
   * This is a helper method to get the correct attribute name given the shape and animation.
   *
   * @param shapeType the type of shape that is being printed.
   * @param aType the type of animation that is being performed on the shape.
   * @return the correct y attribute.
   */
  private String getAttributeYName(String shapeType, AnimationType aType) {
    if (aType == (AnimationType.MOVE)) {
      if (shapeType.equals("rect")) {
        return "y";
      } else if (shapeType.equals("ellipse")) {
        return "cy";
      }
    } else if (aType == (AnimationType.SCALECHANGE)) {
      if (shapeType.equals("rect")) {
        return "height";
      } else if (shapeType.equals("ellipse")) {
        return "ry";
      }
    }
    throw new IllegalArgumentException("Unable to get attribute y name");
  }

  /**
   * This is a helper method to format the lines needed to reset an animation if it is looping.
   *
   * @param a is the AnimationCommand that is being formatted.
   * @param shapeType is the type of shape the command is being performed on.
   * @param startString is the initial string that all reset tags require.
   * @return the entire tag needed to set a shape to its original attributes.
   */
  private String formatReset(AnimationCommand a, String shapeType, String startString) {
    String attributeX;
    String attributeY;
    StringBuilder workString = new StringBuilder();
    AbstractAnimation animation = a.getAnimation();
    switch (animation.getType()) {
      case MOVE:
        String additional = "";
        attributeX = getAttributeXName(shapeType, AnimationType.MOVE);
        attributeY = getAttributeYName(shapeType, AnimationType.MOVE);

        if (animation.getStartX() != animation.getEndX()) {
          workString.append(startString)
              .append(attributeX)
              .append("\" to=\"")
              .append(animation.getStartX())
              .append("\" fill=\"freeze\" />");
        }
        if (animation.getStartY() != animation.getEndY()) {
          additional += startString + attributeY
              + "\" to=\"" + animation.getStartY() + "\" ";
          additional += "fill=\"freeze\" />";
        }
        workString.append(additional);
        break;
      case COLORCHANGE:
        workString.append(startString).append("fill\"");
        workString.append(String.format(" to=\"rgb(%.0f,%.0f,%.0f)\"",
            animation.getOldR() * 255, animation.getOldG() * 255, animation.getOldB() * 255));
        workString.append(" fill=\"freeze\" />");
        break;
      case SCALECHANGE:
        attributeX = getAttributeXName(shapeType, AnimationType.SCALECHANGE);
        attributeY = getAttributeYName(shapeType, AnimationType.SCALECHANGE);
        if (animation.getStartX() != animation.getEndX()) {
          workString.append(startString)
              .append(attributeX)
              .append("\" to=\"")
              .append(animation.getStartX())
              .append("\" fill=\"freeze\" />");
        }
        if (animation.getStartY() != animation.getEndY()) {
          workString.append(startString)
              .append(attributeY).append("\" ")
              .append("\" to=\"")
              .append(animation.getStartY())
              .append("\" fill=\"freeze\" />");
        }
        break;
      default:
        // do nothing.
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
    String attributeX;
    String attributeY;

    // used to determine how to change the attribute name
    attributeX = getAttributeXName(shape, AnimationType.MOVE);
    attributeY = getAttributeYName(shape, AnimationType.MOVE);
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
    workString += String.format(" from=\"rgb(%.0f,%.0f,%.0f)\" to=\"rgb(%.0f,%.0f,%.0f)\"",
        c.getOldR() * 255, c.getOldG() * 255, c.getOldB() * 255, c.getNewR() * 255, c.getNewG()
            * 255, c.getNewB() * 255);
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
      workString2 = starter + attributeY + "\" "
          + "from=\"" + sChange.getStartY()
          + "\" to=\"" + sChange.getEndY() + "\" ";
      workString2 += "fill=\"freeze\" />\n";
    }
    return workString + workString2;
  }

  /**
   * This is a method that returns the last tick a shape is visible.
   *
   * @param endTime is the last tick in the animation.
   */
  protected void setEndTime(int endTime) {
    this.endTime = endTime;
  }
}
