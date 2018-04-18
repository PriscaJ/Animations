package cs3500.animator.model;

import cs3500.animator.provider.model.Command;
import cs3500.animator.provider.model.Shape;

public class CommandAdapter implements Command {
  AnimationCommand oldCmd;
  Animations oldAnimations;

  /**
   * This is the constructor for an adapter that makes our AnimationCommand and Animation
   * pair compatible with the provider's Command.
   *
   * @param oldCmd the AnimationCommand to be transformed.
   * @param oldAnimations the Animation to be transformed.
   */
  public CommandAdapter(AnimationCommand oldCmd, Animations oldAnimations) {
    this.oldCmd = oldCmd;
    this.oldAnimations = oldAnimations;
  }

  @Override
  public void execute(Shape s, int tick) {
    oldCmd.execute(tick);
  }

  @Override
  public void convertToTicks(int tempo) {

    // ignore
  }

  @Override
  public void setStartTick(double st) {
    // setting is done on the intitialization of an animation
  }

  @Override
  public void setEndTick(double e) {
    // setting is done on the intitialization of an animation
  }

  @Override
  public double delta(double a, double b, int tick) {
    return oldAnimations.calculateChange((float) a, (float) b, tick);
  }

  @Override
  public String printCommand(Shape s, int tempo) {
    return oldAnimations.getDescription(tempo);
  }

  @Override
  public double getStartTime() {
    return oldAnimations.getStart();
  }

  @Override
  public double getEndTime() {
    return oldAnimations.getFinish();
  }

  @Override
  public String getName() {
    return oldAnimations.toString();
  }

  @Override
  public String printAnimation(Shape s, int speedFactor, boolean loop) {
    StringBuilder sb = new StringBuilder();
    StringBuilder sb2 = new StringBuilder();

    sb.append("<animate attributeType=\"xml\" begin=\"");
    Animations a = oldCmd.getAnimation();
    sb.append(a.getFinish() - a.getStart())
        .append("ms\" attributeName=\"");

    switch (a.getType()) {
      case MOVE:
        sb2.append(formatMove((Move) a, s.getType(), sb.toString()));
        break;
      case COLORCHANGE:
        sb2.append(formatColor((ColorChange) a, sb.toString()));
        break;
      case SCALECHANGE:
        sb2.append(formatScale((ScaleChange) a, s.getType(), sb.toString()));
        break;
      default:
    }
    return sb2.toString();
  }

  @Override
  public boolean sameType(Command that) {
    return false;
  }

  @Override
  public boolean overlappingTimes(Command that) {
    return false;
    // this is checked privately in the model.
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

    if (shape.equals("rect")) {
      attributeX = "rx";
      attributeY = "ry";
    } else {
      attributeX = "cx";
      attributeY = "cy";
    }

    if (move.getEndX() != move.getStartX()) {
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
        c.getOldR() * 255, c.getOldG() * 255 * 1, c.getOldB() * 255, c.getNewR() * 255, c.getNewG()
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
    } else if ("ellipse".equals(shape)) {
      attributeX = "rx";
      attributeY = "ry";
    }

    if (sChange.getEndX() != sChange.getStartX()) {
      workString = starter + attributeX + "\" "
          + "from=\"" + sChange.getStart()
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


}
