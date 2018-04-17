package cs3500.animator.provider.misc;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import cs3500.animator.provider.model.Command;
import cs3500.animator.provider.model.Shape;


/**
 * A class that contains helper methods used by the view. It is implemented as it's own
 * class that is composed within each helper so it can be called because an AbstractView is
 * not an option due to the fact that some views already extend JFrame (and therefore
 * cannot extend another class).
 */
public interface IHelper {

  /**
   * Creates a file at the given path location with the given text as content.
   * @param text the content to be placed into the file
   * @param path the location to create the file at
   * @throws IOException if the file cannot be written to
   */
  static void writeToFile(String text, String path) throws IOException {
    try {
      PrintWriter writer = new PrintWriter(path, "UTF-8");
      writer.println(text);
      writer.close();
    } catch (IOException e) {
      throw new IOException();
    }
  }

  /**
   * Generate a string representing the svg version of the animation that is applied
   * to the given list of shapes.
   * @param shapes the list of shapes in the animation
   * @param loop does the animation repeat
   * @param speedFactor number of ticks per seconds
   * @return the formatted string
   */
  static String printSVG(List<Shape> shapes, boolean loop, int speedFactor) {
    // open the svg tag
    String svg = "<svg width=\"700\" height=\"500\" version=\"1.1\" " +
        "xmlns=\"http://www.w3.org/2000/svg\">\n";


    if (loop) {
      // add a loopback reference
      svg = svg + "<rect>\n" +
          "    <!-- This is the loop back. Set duration for the duration of one\n" +
          "    animation before loopback. Ensure that this number is greater than \n" +
          "    the end of one complete animation-->\n" +
          "    <!-- this example loops back after 10 seconds -->\n" +
          "   <animate id=\"base\" begin=\"0;base.end\" dur=\"" +
          (((findEndTimeOfAnimation(shapes) / speedFactor) * 1000) + 500) +
          "ms\" " +
          "attributeName=\"visibility\" from=\"hide\" to=\"hide\"/>\n" +
          "</rect>\n";
    }


    // look at each shape
    for (Shape s: shapes) {
      switch (s.getType().toLowerCase()) {
        case "oval":
          svg = svg + "\n<ellipse id=\"Shape " + s.getName() + "\" cx=\"" + s.getAnchor().getX() +
              "\" cy=\"" + s.getAnchor().getY() + "\" rx=\"" + s.getWidth() + "\" ry=\"" +
              s.getHeight()
              + "\" fill=\"rgb" + s.getColor().print255Format() + "\" visibility=\"" +
              s.getVisibility() + "\" >\n";

          // look at each command
          for (Command c: s.getCommands()) {
            svg = svg + c.printAnimation(s, speedFactor, loop);

          }

          // close circle
          svg = svg + "\n</ellipse>";
          break;

        case "rectangle":
          svg = svg + "\n<rect id=\"Shape " + s.getName() + "\" x=\"" + s.getAnchor().getX() +
              "\" y=\"" + s.getAnchor().getY() + "\" width=\"" + s.getWidth() + "\" height=\""
              + s.getHeight()
              + "\" fill=\"rgb" + s.getColor().print255Format() + "\" visibility=\"" +
              s.getVisibility() + "\" >\n";


          // look at each command
          for (Command c: s.getCommands()) {
            svg = svg + c.printAnimation(s, speedFactor, loop);

          }

          // close rect
          svg = svg + "\n</rect>";
          break;

        case "circle":
          break;
        case "square":
          break;
        default:
          break;
      }
    }

    // close the svg tag
    svg = svg + "\n</svg>";
    return svg;
  }

  /**
   * Find the time at which this animation is complete.
   * @param shapes the list of shapes in this animation
   * @return double representing end time
   */
  static double findEndTimeOfAnimation(List<Shape> shapes) {
    double finalTime = 0;

    for (Shape shape : shapes) {

      for (Command command : shape.getCommands()) {
        if (command.getEndTime() > finalTime) {
          finalTime = command.getEndTime();
        }
      }

      if (shape.getEndTime() > finalTime) {
        finalTime = shape.getEndTime();
      }
    }

    return finalTime;
  }

  /**
   * Print the animation as a readable text.
   * @param shapes the list of shapes that makes up this animation
   * @return the formatted string
   */
  static String printAnimation(List<Shape> shapes, int tempo) {
    String printShapes = "";
    String printCommands = "";

    for (Shape s: shapes) {
      // add the shape
      printShapes = printShapes + s.printShape(tempo) + "\n\n";

      for (Command c: s.getCommands()) {
        // add the command
        printCommands = printCommands + c.printCommand(s, tempo) + "\n";
      }
    }
    return printShapes + printCommands;
  }
}
