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

  /** public ShapeAdapter(Shapes oldShapes) {
    this.oldShapes = oldShapes;
  }**/

  @Override
  public String printShape(int tempo) {
    return oldShapes.getDescription(tempo);
  }

  @Override
  public IPosition2D getPosition() {
    // do nothing or should it be vvv
    return new Position2D(adapteeCommands.getAnimation().startX,
            adapteeCommands.getAnimation().getStartY());
  }

  @Override
  public void moveBy(double x, double y) {

  }

  @Override
  public void moveTo(IPosition2D position) {
    Move makeMove =
    new Move(getName(), (float) getPosition().getX(), (float) getPosition().getY(),
            (float) position.getX(), (float) position.getY(), getStartTime(), getEndTime());
    MoveCommand move = new MoveCommand(makeMove);
    //move.execute();

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
    //scaleCommand.execute();
  }

  @Override
  public void changeColor(IColors c) {
    ColorChange makeColor;
    makeColor =
            new ColorChange(getName(), (float) getColor().getR(), (float) getColor().getG(),
                    (float) getColor().getB(), (float) c.getR(), (float) c.getG(), (float) c.getB(),
                    getStartTime(), getEndTime());
    ColorCommand color = new ColorCommand(makeColor);

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

  public static Shape convertShapesToShape(Shapes shape) {

    return (Shape) shape;
  }

  public static Shapes convertShapeToShapes(Shape shape) {
    if (shape.getType().equals("rect")) {
      return new Rectangle(shape.getName(), (float) shape.getPosition().getX(),
          (float) shape.getPosition().getY(), (float) shape.getWidth(), (float) shape.getHeight(),
          (float) shape.getColor().getR(),
          (float) shape.getColor().getG(), (float) shape.getColor().getB(), shape.getStartTime(),
          shape.getEndTime());
    } else if (shape.getType().equals("ellipse")) {
      return new Oval(shape.getName(), (float) shape.getPosition().getX(),
          (float) shape.getPosition().getY(), (float) shape.getWidth(), (float) shape.getHeight(),
          (float) shape.getColor().getR(),
          (float) shape.getColor().getG(), (float) shape.getColor().getB(), shape.getStartTime(),
          shape.getEndTime());
    }
    throw new IllegalArgumentException("this is a weird shape, man.");
  }
}
