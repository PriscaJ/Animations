package cs3500.animator.model;

import java.util.List;

import cs3500.animator.provider.misc.Colors;
import cs3500.animator.provider.misc.IColors;
import cs3500.animator.provider.misc.IPosition2D;
import cs3500.animator.provider.misc.Position2D;
import cs3500.animator.provider.model.Command;
import cs3500.animator.provider.model.Shape;
import javafx.scene.transform.Scale;

public class ShapeAdapter implements Shape {
  Shapes oldShapes;
  AnimationCommand adapteeCommands;

  public ShapeAdapter(Shapes oldShapes, AnimationCommand adapteeCommands) {
    this.oldShapes = oldShapes;
    this.adapteeCommands = adapteeCommands;
  }

  public ShapeAdapter(Shapes oldShapes) {
    this.oldShapes = oldShapes;
  }

  @Override
  public String printShape(int tempo) {
    return oldShapes.getDescription(tempo);
  }

  // todo remove the concrete Position2D and Colors
  @Override
  public IPosition2D getPosition() {
    return null;
  }

  @Override
  public void moveBy(double x, double y) {

  }

  @Override
  public void moveTo(IPosition2D position) {
    Move makeMove;

  }

  @Override
  public String getName() {
    return oldShapes.getName();
  }

  @Override
  public void scale(double newWidth, double newHeight) {
    ScaleChange makeScale;
    makeScale =
            new ScaleChange(oldShapes.getName(), oldShapes.getXPosition(), oldShapes.getYPosition(),
            (float) newWidth, (float) newHeight, oldShapes.getAppears(), oldShapes.getDisappears());

    ScaleCommand scaleCommand = new ScaleCommand(makeScale);
  }

  @Override
  public void changeColor(IColors c) {

  }

  @Override
  public String getType() {
    if (oldShapes.isOval()) {
      return "Oval";
    }
    else {
      return "Rectangle";
    }
  }

  @Override
  public IPosition2D getAnchor() {
    return new Position2D(oldShapes.getXPosition(), oldShapes.getYPosition());
  }

  @Override
  public double getWidth() {
    return oldShapes.getWidth();
  }

  @Override
  public double getHeight() {
    return oldShapes.getHeight();
  }

  @Override
  public IColors getColor() {
    return new Colors(oldShapes.getRed(), oldShapes.getGreen(), oldShapes.getBlue());
  }

  @Override
  public List<Command> getCommands() {
    List<AnimationCommand> animationCommands = oldShapes.getCommands();

    return (List) oldShapes.getCommands();
  }

  @Override
  public int getStartTime() {
    return oldShapes.getAppears();
  }

  @Override
  public int getEndTime() {
    return oldShapes.getDisappears();
  }

  @Override
  public void addCommand(Command c) {
    oldShapes.addCommand((AnimationCommand) c);
  }

  @Override
  public void addListCommands(List<Command> commands) {
    // ignore
  }

  @Override
  public Shape clone() {
    // ignore
    return null;
  }

  @Override
  public boolean isVisible() {
    return false;
  }

  @Override
  public void setVisibility(boolean visible) {

  }

  @Override
  public boolean getVisibility() {
    return false;
  }
}
