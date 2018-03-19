package cs3500.animator.view;

import java.io.FileWriter;
import java.util.List;

import cs3500.animator.model.AbstractShape;
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
  private List<Animations> allAnimations;
  private String fileName;

  public SVGView(List<Shapes> allShapes, List<Animations> allAnimations, String fileName) {
    this.allShapes = allShapes;
    this.allAnimations = allAnimations;
    this.fileName = fileName;
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
        + "xmlns=\"http://www.w3.org/2000/svg\">\n";
    String output = format(allShapes);

    // return start.concat(output);
    try (FileWriter out = new FileWriter(fileName)) {
      out.write(start.concat(output));
      out.flush();
      out.close();
    } catch (Exception e) {
      // do nothing
      System.out.print(e);
    }
  }

  /**
   * Formats the rest of the SVG depending on which shape it is.
   *
   * @param shapes The list of shapes to be parsed.
   * @return The String SVG of the rest of the animation.
   */
  private String format(List<Shapes> shapes) {
    String workString = "";
    for (Shapes s : shapes) {
      // list of animations for this shape
      // as.getAnimations();

      String ellipse = "";
      if (s instanceof Oval) {
        workString = "<" + "ellipse" + formatShape(s, "ellipse") + ">\n"
            + formatCmd(s.getCommands(), "ellipse")
            + "\n</" + "ellipse" + ">\n";
      }
      if (s instanceof Rectangle) {
        workString = "<" + "rect" + formatShape(s, "rect") + ">\n"
            + formatCmd(s.getCommands(), "rect")
            + "\n</" + "rect" + ">\n";
      }
    }
    return workString;
  }

  /**
   * Formats the fields of a Shape in SVG.
   *
   * @param as The AbstractShape that is being formatted
   * @return The String of the shape characteristics.
   */
  private String formatShape(Shapes as, String type) {
    String svg = " ";
    switch (type) {
      switch:


    if (as.getType().equals("rect")) {
      svg = "id=\"" + as.getName() + "\" x=\"" + as.getPoint().x + "\" y=\"" + as.getPoint().y
          + "\" width=\"" + as.getX() + "\" height=\"" + as.getY()
          + "fill=\"rgb("
          + as.getColor().getRed() + as.getColor().getGreen() + as.getColor().getBlue()
          + "\" visibility=\"visible\"";
    } else if (as.getType().equals("oval")) {
      svg = "id=\"" + as.getName() + "\" cx=\"" + as.getPoint().x + "\" cy=\"" + as.getPoint().y
          + "\" rx=\"" + as.getX() + "\" ry=\"" + as.getY()
          + "fill=\"rgb("
          + as.getColor().getRed() + as.getColor().getGreen() + as.getColor().getBlue()
          + "\" visibility=\"visible\"";
    }

    return svg;
  }

  /**
   * Takes a copy of the commands and parses through to properly format SVG.
   *
   * @param cmds copy of the animation commands that a shape has.
   * @return Proper SVG format for Animation commands.
   */
  private String formatCmd(List<AnimationCommand> cmds, String shapeType) {
    String workString = "<animate attributeType=\"xml\" begin=\"";

    for (Animations a : cmds) {
      String transition = "<animate attributeType=\"xml\" begin=\"" + a.getBeginning()
          + "\" dur=\"" + (a.getEnd() - a.getBeginning())
          + "\" attributeName=\"" //+ attributeCmd()
          ;

      switch (a.getType()) {
        case "move":
          return formatMove(transition, (Move) a, shapeType);

        case "colorChange":
          return transition + formatColor(transition, (ColorChange) a);

        case "scale":
          return transition + formatScale(transition, (ScaleChange) a, shapeType);

        default:
      }

      workString = workString + a.getBeginning() + "\" dur=\""
          + (a.getEnd() - a.getBeginning()) + "\" attributeName="
          + transition + "fill=\"freeze\"";
    }

    return workString;
  }

  /**
   * Formats the Command move as SVG animate tag.
   *
   * @param temp The template that all commands have given from formatCmd
   * @param move Takes in a Move class in order to ask for fields
   * @param shape Determines what kind of shape it is to change attributeName.
   * @return The full animate tag for this command.
   */
  private String formatMove(String temp, Move move, String shape) {
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

    if (move.getStartPos() != move.getGoal()) {
      if (move.getStartPos().x != move.getGoal().x) {
        workString = temp
            + "attributeName=\"" + attributeX + "\" "
            + "from=\"" + move.getStartPos().x + "\" to=\"" + move.getGoal().x;
      }
      if (move.getStartPos().y != move.getGoal().y) {
        workString = temp
            + "attributeName=\"" + attributeY + "\" "
            + "from=\"" + move.getStartPos().y + "\" to=\"" + move.getGoal().y;
      }
    }
    return workString;
  }

  /**
   * Formats the Command move as SVG animate tag.
   *
   * @param temp The template that all commands have given from formatCmd
   * @param cChange Takes in a Move class in order to ask for fields
   * @return The full animate tag for this command.
   */
  private String formatColor(String temp, ColorChange cChange) {
    String workString = "";

    if (cChange.getStart() != cChange.getGoal()) {
      if (cChange.getStart().getRed() != cChange.getGoal().getRed()) {
        workString = temp
            + "attributeName=\"fill\""
            + "from=\"" + cChange.getStart().getRed()
            + "\" to=\"" + cChange.getGoal().getRed();
      }
      if (cChange.getStart().getGreen() != cChange.getGoal().getGreen()) {
        workString = temp
            + "from=\"" + cChange.getStart().getGreen()
            + "\" to=\"" + cChange.getGoal().getGreen();
      }
      if (cChange.getStart().getBlue() != cChange.getGoal().getBlue()) {
        workString = temp
            + "from=\"" + cChange.getStart().getBlue()
            + "\" to=\"" + cChange.getGoal().getBlue();
      }
    }
    return workString;
  }

  /**
   * Formats the Command move as SVG animate tag.
   *
   * @param temp The template that all commands have given from formatCmd
   * @param sChange Takes in a Move class in order to ask for fields
   * @param shape Determines what kind of shape it is to change attributeName.
   * @return The full animate tag for this command.
   */
  private String formatScale(String temp, ScaleChange sChange, String shape) {
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

    if (sChange.getStartPoint() != sChange.getGoalPoint()) {
      if (sChange.getStartW() != sChange.getGoalW()) {
        workString = temp
            + "attributeName=\"" + attributeX + "\" "
            + "from=\"" + sChange.getStartW()
            + "\" to=\"" + sChange.getGoalW();
      }
      if (sChange.getStartH() != sChange.getGoalH()) {
        workString = temp
            + "attributeName=\"" + attributeY + "\" "
            + "from=\"" + sChange.getStartH()
            + "\" to=\"" + sChange.getGoalH();
      }
    }
    return workString;
  }
}
